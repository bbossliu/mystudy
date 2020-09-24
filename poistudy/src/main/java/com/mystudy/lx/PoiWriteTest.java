package com.mystudy.lx;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dalaoban
 * @create 2020-05-10-20:06
 */
public class PoiWriteTest {


    /**
     * 03版本导出数据测试
     */
    @Test
    public void HssfworkbookTest(){
        //1、准备工作薄 03版单元格最多为65536格单元格（HSSFWorkbook .xls） -- 07版没有限制 XSSFWorkbook（）SXSSFWorkbook .xlsx，
        Workbook workbook = new HSSFWorkbook();

        //2、准备工作单元
        Sheet sheet = workbook.createSheet("大老板");

        //3、创建一行
        for(int rowNum=0;rowNum<65536;rowNum++){
            Row row = sheet.createRow(rowNum);
            for(int colNum=0;colNum<10;colNum++){
                //4、创建一个单元格
                Cell cell = row.createCell(colNum);
                cell.setCellValue(colNum);
            }
        }
        long start = System.currentTimeMillis();
        try {
            FileOutputStream out = new FileOutputStream("E:\\IdeaWorker02\\mystudy\\poistudy.大老板65536.xls");
            ((HSSFWorkbook) workbook).write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("共消耗时间为："+(double)(end-start)/1000+"m");

    }

    /**
     * 07版本导出数据测试
     */
    @Test
    public void XssfworkbookTest(){
        //1、准备工作薄 03版单元格最多为65536格单元格（HSSFWorkbook .xls） -- 07版没有限制 XSSFWorkbook（）SXSSFWorkbook .xlsx，
        Workbook workbook = new SXSSFWorkbook();

        //2、准备工作单元
        Sheet sheet = workbook.createSheet("大老板");

        //3、创建一行
        for(int rowNum=0;rowNum<100;rowNum++){
            Row row = sheet.createRow(rowNum);
            for(int colNum=0;colNum<10;colNum++){
                //4、创建一个单元格
                Cell cell = row.createCell(colNum);
                cell.setCellValue(colNum);
            }
        }
        long start = System.currentTimeMillis();
        try {
            FileOutputStream out = new FileOutputStream("E:\\IdeaWorker02\\mystudy\\poistudy.大老板.xlsx");
            ((SXSSFWorkbook) workbook).write(out);
            ((SXSSFWorkbook) workbook).dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("共消耗时间为："+(double)(end-start)/1000+"m");
    }

}
