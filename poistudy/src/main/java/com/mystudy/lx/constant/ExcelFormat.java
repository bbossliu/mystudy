package com.mystudy.lx.constant;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import javax.management.StringValueExp;

/**
 * @author dalaoban
 * @create 2020-05-11-22:44
 */
public enum  ExcelFormat {
    FORMAT_INTEGER("INTEGER"),
    FORMAT_DOUBLE("DOUBLE"),
    FORMAT_PERCENT("PERCENT"),
    FORMAT_DATE("DATE");

    private String value;

    private ExcelFormat(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    /*private   String value;

    private ExcelFormat(String value){
        this.value=value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    private final ExcelFormat FORMAT_INTEGER=new ExcelFormat("INTEGER");
    */



}
