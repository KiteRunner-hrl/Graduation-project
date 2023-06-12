package com.kbd.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;

@ComponentScan("com.kbd.service")
@MapperScan("com.kbd.mapper")
@Configuration
@EnableTransactionManagement
public class RootConfiguration {
  @Bean
  public DataSource dataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/kbd");
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUsername("kbd");
    dataSource.setPassword("95959340");
    return dataSource;
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource){
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    return bean;
  }

  @Bean
  public TransactionManager transactionManager(@Autowired DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public CharacterEncodingFilter encodingFilter() {
    CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("utf-8");
    filter.setForceEncoding(true);
    return filter;
  }
}
