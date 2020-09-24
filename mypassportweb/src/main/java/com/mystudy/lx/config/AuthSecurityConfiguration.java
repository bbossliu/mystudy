package com.mystudy.lx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author dalaoban
 * @create 2020-05-30-18:40
 */
@Configuration
@EnableAuthorizationServer
public class AuthSecurityConfiguration extends AuthorizationServerConfigurerAdapter {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTokenStore jdbcTokenStore(){
        // 基于 JDBC 实现，令牌保存到数据
        return new JdbcTokenStore(this.dataSource());
    }

    @Bean
    public ClientDetailsService jdbcClientDetails(){
        // 基于 JDBC 实现，需要事先在数据库配置客户端信息
        return new JdbcClientDetailsService(this.dataSource());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 读取客户端配置
        clients.withClientDetails(jdbcClientDetails());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 设置令牌
        endpoints.tokenStore(jdbcTokenStore());
    }
}
