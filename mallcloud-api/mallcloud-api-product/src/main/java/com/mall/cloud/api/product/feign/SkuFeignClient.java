package com.mall.cloud.api.product.feign;

import com.mall.cloud.api.product.vo.SkuVO;
import com.mall.cloud.common.feign.FeignInsideAuthConfig;
import com.mall.cloud.common.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "mallcloud-product",contextId = "sku")
public interface SkuFeignClient {

    /**
     * 通过skuId获取sku信息
     * @param skuId skuId
     * @return sku信息
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/sku/getById")
    ServerResponseEntity<SkuVO> getById(@RequestParam("skuId") Long skuId);

}
