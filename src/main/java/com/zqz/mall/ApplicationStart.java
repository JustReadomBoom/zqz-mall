package com.zqz.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: ZQZ
 * @Description:
 * @ClassName: ApplicationStart
 * @Date: Created in 16:41 2023-6-12
 */
@SpringBootApplication
@MapperScan("com.zqz.mall.dao")
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }
}
