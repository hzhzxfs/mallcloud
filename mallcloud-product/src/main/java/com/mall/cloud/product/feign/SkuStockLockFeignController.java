package com.mall.cloud.product.feign;

import com.mall.cloud.api.product.dto.SkuStockLockDTO;
import com.mall.cloud.api.product.feign.SkuStockLockFeignClient;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.product.service.*;
import com.mall.cloud.product.service.SkuStockLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SkuStockLockFeignController implements SkuStockLockFeignClient {


    @Autowired
    private SkuStockLockService skuStockLockService;

    @Override
    public ServerResponseEntity<Void> lock(List<SkuStockLockDTO> skuStockLocksParam) {
        return skuStockLockService.lock(skuStockLocksParam);
    }
}
