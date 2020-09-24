package com.mystudy.lx.annotation;

/**
 * @author dalaoban
 * @create 2020-05-12-22:55
 * Excle导入表头设置注解
 */
public @interface Excel {
    //标题的首行坐标
    String firstRow() default "";

    //标题的末行坐标
    String lastRow() default "";

    //标题的首列坐标
    String firstCol() default "";

    //标题的尾列坐标
    String lastCol() default "";

    //标题
    String title() default "";

}
