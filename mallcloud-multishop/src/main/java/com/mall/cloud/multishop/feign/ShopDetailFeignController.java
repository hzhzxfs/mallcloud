package com.mall.cloud.multishop.feign;

import com.mall.cloud.api.multishop.bo.EsShopDetailBO;
import com.mall.cloud.api.multishop.feign.ShopDetailFeignClient;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.multishop.model.ShopDetail;
import com.mall.cloud.multishop.service.ShopDetailService;
import com.mall.cloud.api.multishop.vo.ShopDetailVO;
import com.mall.cloud.common.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@RestController
public class ShopDetailFeignController implements ShopDetailFeignClient {

    @Autowired
    private ShopDetailService shopDetailService;


    @Override
    public ServerResponseEntity<String> getShopNameByShopId(Long shopId) {
        ShopDetailVO shopDetail = shopDetailService.getByShopId(shopId);
        if (Objects.isNull(shopDetail)) {
            return ServerResponseEntity.success("");
        }
        return ServerResponseEntity.success(shopDetail.getShopName());
    }

    @Override
    public ServerResponseEntity<EsShopDetailBO> getShopByShopId(Long shopId) {
        ShopDetailVO shopDetail = shopDetailService.getByShopId(shopId);
        if (Objects.isNull(shopDetail)) {
            return ServerResponseEntity.success(new EsShopDetailBO());
        }
        return ServerResponseEntity.success(BeanUtil.map(shopDetail, EsShopDetailBO.class));
    }


    @Override
    public ServerResponseEntity<List<ShopDetailVO>> listByShopIds(List<Long> shopIds) {
        List<ShopDetail> shopDetail = shopDetailService.listByShopIds(shopIds);
        return ServerResponseEntity.success(BeanUtil.mapAsList(shopDetail, ShopDetailVO.class));
    }

    @Override
    public ServerResponseEntity<EsShopDetailBO> shopExtensionData(Long shopId) {
        return ServerResponseEntity.success(shopDetailService.shopExtensionData(shopId));
    }

    @Override
    public ServerResponseEntity<List<ShopDetailVO>> getShopDetailByShopIdAndShopName(List<Long> shopIds, String shopName) {
        return ServerResponseEntity.success(shopDetailService.getShopDetailByShopIdAndShopName(shopIds,shopName));
    }
}
