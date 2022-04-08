package com.cao.paymentdemo.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author : CGL
 * @Date : 2022/2/27 10:28
 * @Desc :
 */

@Configuration
@MapperScan("com.cao.paymentdemo.mapper")
@EnableTransactionManagement    // 启动事务管理
public class MyBatisPlusConfig {
}
