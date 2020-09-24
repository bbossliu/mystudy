package com.mystudy.lx.util;

import com.mystudy.lx.constant.ExcelFormat;
import com.mystudy.lx.entity.Do.TtlProductInfoDo;
import com.mystudy.lx.entity.ExcelHeaderInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author dalaoban
 * @create 2020-05-11-22:56
 */
public class ExcelUtils {

    private static final Logger LOGGER=LoggerFactory.getLogger(ExcelUtils.class);

    public static final int ROW_ACCESS_WINDOW_SIZE = 100;
    public static final int SHEET_MAX_ROW = 100000;

    private List<TtlProductInfoDo> items;
    private List<ExcelHeaderInfo> excelHeaderInfos;
    private Map<String, ExcelFormat> formatInfo;

    public ExcelUtils(List list, List<ExcelHeaderInfo> excelHeaderInfos) {
        this.items = list;
        this.excelHeaderInfos = excelHeaderInfos;
    }

    public ExcelUtils(List<TtlProductInfoDo> items, List<ExcelHeaderInfo> excelHeaderInfos, Map<String, ExcelFormat> formatInfo) {
        this.items = items;
        this.excelHeaderInfos = excelHeaderInfos;
        this.formatInfo = formatInfo;
    }

    public Workbook getWorkBook(){
       Workbook workbook = new SXSSFWorkbook(ROW_ACCESS_WINDOW_SIZE);
        CellStyle cellStyle = setCellStyle(workbook);
        //记录行号 需要在每次创建表头时 初始化为0
        int pageNum=0;

        //创建sheet对象
        Sheet sheet=null;

        String[][] data = getStringArray();
        if(!CollectionUtils.isEmpty(items)){
            Field[] fields = items.get(0).getClass().getDeclaredFields();
            for(int i=0;i<data.length;i++){
                //如果超过定义的表格中存储导出元素的最大值则重新创建一个sheet对象
                if(i % SHEET_MAX_ROW==0 ){
                    pageNum=0;
                    sheet = createSheet(workbook,i);
                    for (int j=0;j<getHeaderRowNum(excelHeaderInfos);j++){
                        //创建表头
                        sheet.createRow(pageNum++);
                    }
                    createHeadInfo(sheet,cellStyle);
                }
                pageNum++;
                Row row = sheet.createRow(pageNum);
                createContent(row,cellStyle,data,fields,i);
            }
        }
        return workbook;
    }

    /**
     * 创建表格内容
     * @param row
     * @param data
     * @param fields
     * @param i
     */
    private void createContent(Row row,CellStyle style, String[][] data, Field[] fields, int i) {
        List<String> beanProperty = getBeanProperty(fields);
        for(int j=0;j<fields.length;j++){
                Cell cell = row.createCell(j);
                // 如果格式化Map为空，默认为字符串格式
                if (formatInfo == null) {
                    cell.setCellValue(data[i][j]);
                    continue;
                }
                //数据格式转换
                if(formatInfo.containsKey(beanProperty.get(j))){
                    String s = beanProperty.get(j);
                    switch (formatInfo.get(s).getValue()){
                        case "DOUBLE": cell.setCellValue(Double.parseDouble(data[i][j])) ;break;
                        case "INTEGER":cell.setCellValue(Integer.parseInt(data[i][j]));break;
                        case "PERCENT":
                            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
                            cell.setCellValue(Double.parseDouble(data[i][j]));
                            cell.setCellStyle(style);break;
                        case "DATE": style.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-MM-dd HH:mm:ss"));
                                     cell.setCellValue(this.parseDate(data[i][j]));
                                     cell.setCellStyle(style);break;

                    }
                } else {
                    row.createCell(j).setCellValue(data[i][j]);
                }
            }
    }

    // 新建表格
    private static Sheet createSheet(Workbook workbook, int i) {
        Integer sheetNum = i / SHEET_MAX_ROW;
        workbook.createSheet("sheet" + sheetNum);
        //动态指定当前的工作表
        return workbook.getSheetAt(sheetNum);
    }


    /**
     * 创建表头
     * @param sheet
     * @param style
     */
    private void createHeadInfo(Sheet sheet, CellStyle style) {
        for(ExcelHeaderInfo excelHeaderInfo: excelHeaderInfos){
            Integer firstRow = excelHeaderInfo.getFirstRow();
            Integer lastRow = excelHeaderInfo.getLastRow();
            Integer firstCol = excelHeaderInfo.getFirstCol();
            Integer lastCol = excelHeaderInfo.getLastCol();
            String title = excelHeaderInfo.getTitle();

            //只有首行和末行或则首列和末列差大于0才进行表头合并
            if((lastRow-firstRow!=0) || (lastCol-firstCol)!=0){
                sheet.addMergedRegion(new CellRangeAddress(firstRow,lastRow,firstCol,lastCol));
            }

            //在表头firstRow的位置上创建行
            Row row = sheet.getRow(firstRow);

            //在表头firstCol的位置上创建单位格
            Cell cell = row.createCell(firstCol);
            cell.setCellValue(title);
            cell.setCellStyle(style);
            //创建单元格样式

            sheet.setColumnWidth(firstCol, sheet.getColumnWidth(firstCol) * 17 / 12);
        }
    }


    /**
     * 获取标题总行数
     * @param headerInfos
     * @return
     */
    private static Integer getHeaderRowNum(List<ExcelHeaderInfo> headerInfos) {
        Integer maxRowNum = 0;
        for (ExcelHeaderInfo excelHeaderInfo : headerInfos) {
            Integer tmpRowNum = excelHeaderInfo.getLastRow();
            if (tmpRowNum > maxRowNum) {
                maxRowNum = tmpRowNum;
            }
        }
        return maxRowNum + 1;
    }


    /**
     * 将查询出的数据转为二维数组
     * @return
     */
    public String[][] getStringArray(){
        if(!CollectionUtils.isEmpty(items) ){
            int size = items.size();
            String[][] data=new String[size][];
            Field[] fields = items.get(0).getClass().getDeclaredFields();
            List<String> beanProperty = getBeanProperty(fields);
            for(int i=0;i<size;i++){
                data[i] = new String[fields.length];
                for(int j=0;j<beanProperty.size();j++){
                    try {
                        data[i][j]= BeanUtils.getProperty(items.get(i),beanProperty.get(j));
                    } catch (Exception e){
                        LOGGER.error("获取对象属性值失败");
                        e.printStackTrace();
                    }
                }
            }
            return data;
        }
        return null;
    }


    /**
     * 将属性数组转化为存储属性名的List
     * @param fields
     * @return
     */
    public List<String> getBeanProperty(Field[] fields){
        List<String> list = Lists.newArrayList();
        for(Field field: fields){
            // 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
            field.setAccessible(true);
            String[] strs=field.toString().split("\\.");
            String filedName = strs[strs.length-1];
            list.add(filedName);
        }
        return list;
    }


    /**
     * 设置表格总体样式
     * @param workbook
     * @return
     */
    private static CellStyle setCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        return style;
    }

    /**
     * 将字符串转为时间
     * @param strDate
     * @return
     */
    private Date parseDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (Exception e) {
            LOGGER.error("字符串转日期错误");
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 输出响应结果
     * @param response
     * @param fileName
     * @param workbook
     */
    public void sendHttpResponse(HttpServletResponse response, String fileName, Workbook workbook) {
        try {
            fileName += System.currentTimeMillis() + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(Math.ceil(1.1));
    }

}
