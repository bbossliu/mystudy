package com.mystudy.lx.controller;

import com.mystudy.lx.service.ITtlProductInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dalaoban
 * @create 2020-05-17-18:42
 */
@Controller
@RequestMapping("/poi")
public class TtlProductInfoController {

    private  static final Logger logger= LoggerFactory.getLogger(TtlProductInfoController.class);



    @Autowired
    ITtlProductInfoService productInfoService;

    @GetMapping("export")
    public void export(HttpServletResponse response, @RequestParam("sheetName") String sheetName){
        try {
            productInfoService.export(response,sheetName);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("poi 导出发生异常！异常信息为[{}]",e.getMessage());
        }

    }
}
