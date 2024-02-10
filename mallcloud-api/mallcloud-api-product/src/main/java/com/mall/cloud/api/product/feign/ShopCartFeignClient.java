package com.mall.cloud.api.product.feign;

import com.mall.cloud.common.feign.FeignInsideAuthConfig;
import com.mall.cloud.common.order.vo.ShopCartItemVO;
import com.mall.cloud.common.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "mallcloud-product",contextId = "shopCart")
public interface ShopCartFeignClient {

    /**
     * 获取购物项
     * @return 购物项
     */
    @GetMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/shopCart/getById")
    ServerResponseEntity<List<ShopCartItemVO>> getCheckedShopCartItems();

    /**
     * 通过购物车id删除用户购物车物品
     * @param shopCartItemIds 购物车id
     * @return
     */
    @DeleteMapping("/delete_item")
    ServerResponseEntity<Void> deleteItem(@RequestBody List<Long> shopCartItemIds);
}
