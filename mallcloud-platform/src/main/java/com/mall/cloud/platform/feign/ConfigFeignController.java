package com.mall.cloud.platform.feign;

import com.mall.cloud.api.platform.feign.ConfigFeignClient;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.platform.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigFeignController implements ConfigFeignClient {

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public ServerResponseEntity<String> getConfig(String key) {
        return ServerResponseEntity.success(sysConfigService.getValue(key));
    }
}
