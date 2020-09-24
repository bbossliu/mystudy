package com.mystudy.lx.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mystudy.lx.interceptor.MyInterceport;
import com.mystudy.lx.interceptor.MyInterceport1;
import org.apache.ibatis.plugin.Plugin;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * @author dalaoban
 * @create 2020-05-24-13:25
 */
@Configuration
@PropertySource("classpath:application.properties")
public class MySpringConfiguration {

    private static final String url="jdbc:mysql://120.79.61.78:3316/study-test?useUnicode=true&characterEncoding=utf-8";
    private static final String driveClassName="com.mysql.jdbc.Driver";
    private static final String userName="root";
    private static final String password="root";

    @Bean(initMethod = "init",destroyMethod = "close",name = "dataSource")
    @Primary
    public DruidDataSource druidDataSource() throws ClassNotFoundException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driveClassName);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Bean(name = "dataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(@Autowired @Qualifier("dataSource") DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }



    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean sessionFactoryBean(@Autowired @Qualifier("dataSource") DataSource dataSource,@Autowired OptimisticLockerInterceptor optimisticLockerInterceptor,@Autowired MyInterceport1 myInterceport1) throws FileNotFoundException {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = resourcePatternResolver.getResource("classpath:mybatis.xml");
        mybatisSqlSessionFactoryBean.setConfigLocation(resource);
        mybatisSqlSessionFactoryBean.setTypeAliasesPackage("com.mystudy.lx.entity");
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setPlugins(optimisticLockerInterceptor);
        mybatisSqlSessionFactoryBean.setPlugins(myInterceport1);
        Properties properties = new Properties();
        properties.setProperty("name","五年内");
        mybatisSqlSessionFactoryBean.setConfigurationProperties(properties);
        return mybatisSqlSessionFactoryBean;
    }

    @Bean
    @Primary
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.mystudy.lx.mapper");
        return mapperScannerConfigurer;
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public MyInterceport1 myInterceport() {
        return new MyInterceport1();
    }

}
