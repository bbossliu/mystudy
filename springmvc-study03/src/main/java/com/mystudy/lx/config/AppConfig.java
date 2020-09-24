package com.mystudy.lx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author dalaoban
 * @create 2020-09-14-9:47
 */
@Configuration
@ComponentScan("com")
@EnableWebMvc
public class AppConfig extends WebMvcConfigurationSupport {


}
