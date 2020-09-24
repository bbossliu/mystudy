package com.mystudy.test;

import java.io.File;

/**
 * @author dalaoban
 * @create 2020-09-12-22:17
 */
public class Test {

    public static  void scanProjectByPath(String path){
        File file =new File(path);
        //递归解析项目所有文件
        scanFile(file);
    }

    public static void scanFile(File file){
        //递归解析项目
        if (file.isDirectory()){
            for (File file1 : file.listFiles()) {
                scanFile(file1);
            }
        }else{
            //如果不是文件夹
            //D://project//com//TestContrller.class
            //D://project//com//controller//TestController.class
            //com.controller.TestController
            String filePath =  file.getPath();

            String projectPath = Test.class.getResource("/").getPath();

//            String classPath  =  filePath.replace(new File(projectPath).getPath()+"\\","");
////            classPath = classPath.replaceAll("\\\\",".");
////            String className = classPath.substring(0,classPath.lastIndexOf("."));
////
////            System.out.println(className);

            filePath = filePath.replace(new File(projectPath).getPath() + "\\", "");

            String className = filePath.replaceAll("\\\\", ".").substring(0,filePath.lastIndexOf("."));

            System.out.println(className);





        }
    }

    public static void main(String[] args) {
        String projectPath = Test.class.getResource("/").getPath();
        System.out.println(new File(projectPath).getPath()+"\\");
        projectPath = projectPath.replaceAll("%20","");
        scanProjectByPath(projectPath+"\\"+"com");
    }
}
