package com.mystudy.lx.service;

import com.mystudy.lx.entity.Do.TtlProductInfoDo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author dalaoban
 * @create 2020-05-11-22:55
 */
public interface ITtlProductInfoService {

    /**
     * 查询需要导出的数据
     * @param map
     * @return
     */
    List<TtlProductInfoDo> getTtlProductInfoDos(Map<String, Object> map);

    /**
     * 导出数据
     * @param response
     * @param fileName
     */
    void export(HttpServletResponse response, String fileName);

}
