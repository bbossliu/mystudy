package com.mystudy.lx.config;

/**
 * @author dalaoban
 * @create 2020-05-30-20:00
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true,jsr250Enabled = true,securedEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    /**
     * 配置相关拦截规则
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/").hasAuthority("SystemContent")
            .antMatchers("/view/**").hasAuthority("SystemContentView")
            .antMatchers("/insert/**").hasAuthority("SystemContentInsert")
            .antMatchers("/update/**").hasAuthority("SystemContentUpdate")
            .antMatchers("/delete/**").hasAuthority("SystemContentDelete");
    }
}
