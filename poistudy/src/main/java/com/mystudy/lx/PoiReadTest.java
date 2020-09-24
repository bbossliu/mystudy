package com.mystudy.lx;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * @author dalaoban
 * @create 2020-05-10-20:56
 */
public class PoiReadTest {
    private final static String PATH="E:\\IdeaWorker02\\mystudy\\poistudy";
    @Test
    public void poiRead(){
        try {

            FileInputStream inputStream = new FileInputStream(PoiReadTest.PATH+".大老板.xlsx");

            Workbook workbook = WorkbookFactory.create(inputStream);
//            HSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
//            workbook.setForceFormulaRecalculation(true);
            if(workbook instanceof HSSFWorkbook){
                System.out.println("HSSFWorkbook");
            }
            if(workbook instanceof XSSFWorkbook){
                System.out.println("XSSFWorkbook");
            }
            if(workbook instanceof SXSSFWorkbook){
                System.out.println("SXSSFWorkbook");
            }
            int numberOfSheets = workbook.getNumberOfSheets();
            for(int sheetNum=0;sheetNum<numberOfSheets;sheetNum++){
                Sheet sheetAt = workbook.getSheetAt(sheetNum);
                int rows = sheetAt.getPhysicalNumberOfRows();
                for(int rowNum=0;rowNum<rows;rowNum++){
                    Row row = sheetAt.getRow(rowNum);
                    if(row!=null){
                        int cells = row.getPhysicalNumberOfCells();
                        for(int colNum=0;colNum<cells;colNum++){
                            Cell cell = row.getCell(colNum);
                            CellType cellType = cell.getCellType();

                                switch (cellType){
                                    case  NUMERIC:
                                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                                            Date dateCellValue = cell.getDateCellValue();
                                            System.out.println(dateCellValue);
                                        }
//                                        System.out.println(cell.getNumericCellValue() + "|");
                                        break;
                                    case  STRING:
//                                        System.out.println(cell.getStringCellValue() + "|");
                                        break;
                                    case  BOOLEAN:
//                                        System.out.println(cell.getBooleanCellValue() + "|");
                                        break;
                                    case  FORMULA:
                                        String cellFormula = cell.getCellFormula();
                                        System.out.println("=="+cellFormula);
                                        System.out.println(cell.getNumericCellValue());
                                        break;
                                    default:
                                        System.out.println("=----");;break;
                                }
                            }
                        }
                    }
                }
            }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println(Integer.highestOneBit(17));
    }
}
