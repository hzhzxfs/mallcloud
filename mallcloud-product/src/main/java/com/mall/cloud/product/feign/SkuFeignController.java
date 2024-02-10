package com.mall.cloud.product.feign;

import com.mall.cloud.api.product.feign.SkuFeignClient;
import com.mall.cloud.api.product.vo.SkuVO;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.product.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SkuFeignController implements SkuFeignClient {

    @Autowired
    private SkuService skuService;


    @Override
    public ServerResponseEntity<SkuVO> getById(Long skuId) {
        return ServerResponseEntity.success(skuService.getSkuBySkuId(skuId));
    }
}
