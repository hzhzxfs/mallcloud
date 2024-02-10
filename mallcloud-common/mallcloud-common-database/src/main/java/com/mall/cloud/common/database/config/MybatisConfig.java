package com.mall.cloud.common.database.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({ "com.mall.cloud.**.mapper" })
public class MybatisConfig {


}
