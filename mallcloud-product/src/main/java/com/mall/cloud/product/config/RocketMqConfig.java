package com.mall.cloud.product.config;

import com.mall.cloud.common.rocketmq.config.RocketMqAdapter;
import com.mall.cloud.common.rocketmq.config.RocketMqConstant;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@RefreshScope
@Configuration
public class RocketMqConfig {

    @Autowired
    private RocketMqAdapter rocketMqAdapter;

    @Lazy
    @Bean(destroyMethod = "destroy")
    public RocketMQTemplate stockMqTemplate() {
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.STOCK_UNLOCK_TOPIC);
    }

}
