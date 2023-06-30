package com.zqz.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: ApplicationStart
 * @Date: Created in 16:41 2023-6-12
 */
@SpringBootApplication
@MapperScan("com.zqz.mall.dao")
@EnableTransactionManagement
public class ApplicationStart {

    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
