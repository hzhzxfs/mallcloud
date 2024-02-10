package com.mall.cloud.common.database.annotations;

import com.mall.cloud.common.database.interceptor.GeneratedKeyInterceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//分布式id标识

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedId {

    String value();
}
