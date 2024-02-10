package com.mall.cloud.multishop.controller.platform;

import com.mall.cloud.api.multishop.vo.ShopDetailVO;
import com.mall.cloud.common.constant.Constant;
import com.mall.cloud.common.database.dto.PageDTO;
import com.mall.cloud.common.database.vo.PageVO;
import com.mall.cloud.common.exception.mallcloudException;
import com.mall.cloud.common.response.ResponseEnum;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.common.security.AuthUserContext;
import com.mall.cloud.multishop.dto.ShopDetailDTO;
import com.mall.cloud.multishop.model.ShopDetail;
import com.mall.cloud.multishop.service.ShopDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.mall.cloud.common.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

//店铺详情

@RestController("platformShopDetailController")
@RequestMapping("/platform/shop_detail")
@Tag(name = "platform-店铺信息")
public class ShopDetailController {

    @Autowired
    private ShopDetailService shopDetailService;



    @GetMapping("/page")
    @Operation(summary = "分页查询" , description = "分页查询")
    public ServerResponseEntity<PageVO<ShopDetailVO>> getShopAuditingPage(PageDTO pageDTO, ShopDetailDTO shopDetailDTO) {
        if (!Objects.equals(Constant.PLATFORM_SHOP_ID, AuthUserContext.get().getTenantId())) {
            throw new mallcloudException(ResponseEnum.UNAUTHORIZED);
        }
        return ServerResponseEntity.success(shopDetailService.page(pageDTO, shopDetailDTO));
    }

    @GetMapping("/info")
    @Operation(summary = "店铺详情" , description = "店铺详情")
    public ServerResponseEntity<ShopDetailVO> getInfo(@RequestParam Long shopId) {
        ShopDetailVO shopDetailVO = shopDetailService.getByShopId(shopId);
        return ServerResponseEntity.success(shopDetailVO);
    }

    /**
     * 新建店铺
     */
    @PostMapping("/create_shop")
    @Operation(summary = "新建店铺" , description = "新建店铺")
    public ServerResponseEntity<Void> createShop(@RequestBody ShopDetailDTO shopDetailDTO) {
        shopDetailService.createShop(shopDetailDTO);
        return ServerResponseEntity.success();
    }

    @PutMapping("/update_shop")
    @Operation(summary = "更新店铺" , description = "更新店铺")
    public ServerResponseEntity<Void> updateShop(@RequestBody ShopDetailDTO shopDetailDTO) {
        shopDetailService.update(BeanUtil.map(shopDetailDTO, ShopDetail.class));
        return ServerResponseEntity.success();
    }
}
