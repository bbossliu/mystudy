package com.mystudy.lx.servelet;

import com.mystudy.lx.annotation.MyController;
import com.mystudy.lx.annotation.MyRequestMapping;
import com.mystudy.lx.annotation.MyResponseBody;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dalaoban
 * @create 2020-09-12-21:56
 */
public class LXDispatcherServlet extends HttpServlet {

    private static String  COMPENT_SCAN_ELEMENT_PACKAGE_NAME= "package";

    private static String COMPENT_SCAN_ELEMENT_NAME = "compentScan";

    private static String XML_PATH_LOCAL= "xmlPathLocal";

    private  static String prefix = "";
    private  static String suffix = "";

    private static String projectPath = LXDispatcherServlet.class.getResource("/").getPath();

    private  static Map<String, Method> methodMap = new HashMap<>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        projectPath = projectPath.replaceAll("%20"," ");
        String initParameter = config.getInitParameter(XML_PATH_LOCAL);
        //解析xml文件 file:xml 文件对象
        File file = new File(projectPath + "//" + initParameter);

        Document document = getDocument(file);
        Element rootElement = document.getRootElement();

        Element element = rootElement.element(COMPENT_SCAN_ELEMENT_NAME);
        String packageValue = element.attribute(COMPENT_SCAN_ELEMENT_PACKAGE_NAME).getValue();

        Element view = rootElement.element("view");
        prefix = view.attribute("prefix").getValue();
        suffix = view.attribute("suffix").getValue();



        try {
            scanProjectByPath(projectPath+"\\"+packageValue);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public  void scanProjectByPath(String path) throws ClassNotFoundException {
        File file =new File(path);
        //递归解析项目所有文件
        scanFile(file);
    }

    public void scanFile(File file) throws ClassNotFoundException {
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
            System.out.println(filePath);

            filePath = filePath.replace(new File(projectPath).getPath() + "\\", "");

            String className = filePath.replaceAll("\\\\", ".").substring(0,filePath.lastIndexOf("."));

            Class<?> aClass = Class.forName(className);

            if(aClass.isAnnotationPresent(MyController.class)){
                MyRequestMapping myRequestMapping = aClass.getAnnotation(MyRequestMapping.class);
                String classRequestMappingUrl = myRequestMapping.value();

                for (Method method : aClass.getMethods()) {
                    //这里与spring合成的知识点有关  以下为判断这个方法是不是合成的。
                    if(!method.isSynthetic()){
                        MyRequestMapping myRequestMapping1 = method.getAnnotation(MyRequestMapping.class);
                        if(myRequestMapping1 != null){
                            String methodRequsetMappingUrl = myRequestMapping1.value();
                            System.out.println("类:"+aClass.getName()+"的"+method.getName()+"方法被映射到了"+classRequestMappingUrl+methodRequsetMappingUrl+"上面");
                            methodMap.put(classRequestMappingUrl+methodRequsetMappingUrl,method);
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param file
     * @return
     */
    public Document getDocument(File file){
        try {
            SAXReader saxReader = new SAXReader();

            Document document = saxReader.read(file);

            return document;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 执行的时候做的事情:
     * 拿到请求URI去map里面get
     * 给参数赋值并调用方法
     * 拿到方法返回值做视图跳转和消息返回
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        Method method = methodMap.get(requestURI);
        if(method != null){
            Parameter[] parameters = method.getParameters();
            Object[] obj = new Object[parameters.length];

            /**
             * 封装查询参数
             */
            for (int i = 0; i < obj.length; i++) {
                //获取方法参数的名字  如果是jdk1.8以前需要配置parameters
                String name = parameters[i].getName();
                //获取参数类型
                Class<?> type = parameters[i].getType();

                if(type.equals(String.class)){
                    obj[i] = req.getParameter(name);
                }else if(type.equals(HttpServletRequest.class)){
                    obj[i] = req;
                }else if(type.equals(HttpServletResponse.class)){
                    obj[i] = resp;
                }else {
                    try {
                        //如果是对象类型
                        Object o = type.newInstance();

                        for (Field field : type.getDeclaredFields()) {
                            field.setAccessible(true);
                            String name1 = field.getName();
                            String parameter = req.getParameter(name1);
                            field.set(o,parameter);
                        }

                        obj[i] = o;

                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }


            try{
                Object o = method.getDeclaringClass().newInstance();
                Object invoke = method.invoke(o, obj);

                // 判断返回值是否是Void
                if (!method.getReturnType().equals(Void.class)){
                    MyResponseBody annotation = method.getAnnotation(MyResponseBody.class);
                    if (annotation!=null){
                        //提供接口来做这个事情
                        resp.getWriter().write(String.valueOf(invoke));
                    }else {
                        // /page/index.html   page/index.html
                        req.getRequestDispatcher(prefix+String.valueOf(invoke)+suffix).forward(req,resp);
                    }

                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
