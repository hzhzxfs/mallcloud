package com.mall.cloud.multishop.controller.app;

import com.mall.cloud.api.multishop.vo.ShopDetailVO;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.common.security.AuthUserContext;
import com.mall.cloud.multishop.dto.ShopDetailDTO;
import com.mall.cloud.multishop.service.ShopDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Objects;


@RequestMapping(value = "/my_shop_detail")
@RestController("appMyShopDetailController")
@Tag(name = "app-我的店铺详情信息")
public class MyShopDetailController {

    @Autowired
    private ShopDetailService shopDetailService;

    @PostMapping("/create")
    @Operation(summary = "创建店铺" , description = "创建店铺")
    public ServerResponseEntity<Void> create(@Valid @RequestBody ShopDetailDTO shopDetailDTO) {
        shopDetailService.createShop(shopDetailDTO);
        return ServerResponseEntity.success();
    }

    @GetMapping
    @Operation(summary = "获取我的店铺" , description = "获取我的店铺")
    public ServerResponseEntity<ShopDetailVO> get() {
        Long shopId = AuthUserContext.get().getTenantId();
        if (Objects.isNull(shopId)) {
            return ServerResponseEntity.success(null);
        }
        return ServerResponseEntity.success(shopDetailService.getByShopId(shopId));
    }
}
