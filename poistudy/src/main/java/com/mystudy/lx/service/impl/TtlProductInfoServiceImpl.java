package com.mystudy.lx.service.impl;

import com.mystudy.lx.constant.ExcelFormat;
import com.mystudy.lx.entity.Do.TtlProductInfoDo;
import com.mystudy.lx.entity.ExcelHeaderInfo;
import com.mystudy.lx.mapper.TtlProductInfoMapper;
import com.mystudy.lx.service.ITtlProductInfoService;
import com.mystudy.lx.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author dalaoban
 * @create 2020-05-11-22:55
 */
@Service
public class TtlProductInfoServiceImpl implements ITtlProductInfoService {

    @Autowired
    TtlProductInfoMapper ttlProductInfoMapper;

    //每个线程导出的最大条数
    private static final Integer THREAD_MAX_ROW = 20000;

    /**
     * 导出数据
     * @param response
     * @param fileName
     */
    @Override
    public void export(HttpServletResponse response, String fileName) {
        //1、调用ExcelUtils的构造方法对成员变量赋值 查询数据 调用导出数据方法获取工作薄 导出
        HashMap<String,Object> map=new HashMap<>(2);
        map.put("offset",0);
        map.put("limit",10);
        List<TtlProductInfoDo> list = getTtlProductInfoDos(map);
        ExcelUtils excelUtils = new ExcelUtils(multiThreadListProduct(), getHeaderInfo(), getFormatInfo());
        //2、调用导出数据方法进行导出
        excelUtils.sendHttpResponse(response,fileName,excelUtils.getWorkBook());
    }

    /**
     * 根据条件查询列表数据
     * @param map
     * @return
     */
    @Override
    public List<TtlProductInfoDo> getTtlProductInfoDos(Map<String, Object> map){
        return ttlProductInfoMapper.listProduct(map);
    }


    // 获取表头信息
    private List<ExcelHeaderInfo> getHeaderInfo() {
        return Arrays.asList(
                new ExcelHeaderInfo(1, 1, 0, 0, "id"),
                new ExcelHeaderInfo(1, 1, 1, 1, "商品名称"),

                new ExcelHeaderInfo(0, 0, 2, 3, "分类"),
                new ExcelHeaderInfo(1, 1, 2, 2, "类型ID"),
                new ExcelHeaderInfo(1, 1, 3, 3, "分类名称"),

                new ExcelHeaderInfo(0, 0, 4, 5, "品牌"),
                new ExcelHeaderInfo(1, 1, 4, 4, "品牌ID"),
                new ExcelHeaderInfo(1, 1, 5, 5, "品牌名称"),

                new ExcelHeaderInfo(0, 0, 6, 7, "商店"),
                new ExcelHeaderInfo(1, 1, 6, 6, "商店ID"),
                new ExcelHeaderInfo(1, 1, 7, 7, "商店名称"),

                new ExcelHeaderInfo(1, 1, 8, 8, "价格"),
                new ExcelHeaderInfo(1, 1, 9, 9, "库存"),
                new ExcelHeaderInfo(1, 1, 10, 10, "销量"),
                new ExcelHeaderInfo(1, 1, 11, 11, "插入时间"),
                new ExcelHeaderInfo(1, 1, 12, 12, "更新时间"),
                new ExcelHeaderInfo(1, 1, 13, 13, "记录是否已经删除")
        );
    }


    /**
     * 格式化数据
     * @return
     */
    private Map<String, ExcelFormat> getFormatInfo() {
        Map<String, ExcelFormat> format = new HashMap<>();
        format.put("id", ExcelFormat.FORMAT_INTEGER);
        format.put("categoryId", ExcelFormat.FORMAT_INTEGER);
        format.put("branchId", ExcelFormat.FORMAT_INTEGER);
        format.put("shopId", ExcelFormat.FORMAT_INTEGER);
        format.put("price", ExcelFormat.FORMAT_DOUBLE);
        format.put("stock", ExcelFormat.FORMAT_INTEGER);
        format.put("salesNum", ExcelFormat.FORMAT_INTEGER);
        format.put("isDel", ExcelFormat.FORMAT_INTEGER);
        return format;
    }


    public List<TtlProductInfoDo> multiThreadListProduct(){
        List<FutureTask<List<TtlProductInfoDo>>> tasks=new ArrayList<>();
        List<TtlProductInfoDo> productInfoDos = new ArrayList<>();
        int totalNum = 500000;
        //循环次数
        int loopNum= new Double(Math.ceil(THREAD_MAX_ROW / totalNum)).intValue();

        long start = System.currentTimeMillis();
        executeTask(tasks, loopNum, totalNum);

        for (FutureTask<List<TtlProductInfoDo>> task : tasks) {
            try {
                productInfoDos.addAll(task.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return productInfoDos;
    }



    private void executeTask(List<FutureTask<List<TtlProductInfoDo>>> tasks, int loopNum, int totalNum) {

        Map<String, Object> map = new HashMap<>(2);
        for(int i=0;i<loopNum;i++){
            map.put("offset", i * THREAD_MAX_ROW);
            if (i == loopNum - 1) {
                map.put("limit", totalNum - THREAD_MAX_ROW * i);
            } else {
                map.put("limit", THREAD_MAX_ROW);
            }
            FutureTask<List<TtlProductInfoDo>> task = new FutureTask<>(new ListThread(map));
            new Thread(task).start();
            tasks.add(task);;
            //i*totalNum
        }
    }

    public class ListThread implements Callable<List<TtlProductInfoDo>>{
        Map<String, Object> map;

        public ListThread( Map<String, Object> map) {
            this.map=map;
        }

        @Override
        public List<TtlProductInfoDo> call() throws Exception {
            return getTtlProductInfoDos(map);
        }
    }

    public static void main(String[] args) {
        Executors.newCachedThreadPool().execute(()->{
            System.out.println("hello");
        });

    }
}
