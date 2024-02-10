package com.mall.cloud.api.feign;

import com.mall.cloud.api.vo.EsPageVO;
import com.mall.cloud.api.vo.search.EsOrderVO;
import com.mall.cloud.common.dto.OrderSearchDTO;
import com.mall.cloud.common.feign.FeignInsideAuthConfig;
import com.mall.cloud.common.response.ServerResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 订单搜索
 */
@FeignClient(value = "mallcloud-search",contextId = "searchOrder")
public interface SearchOrderFeignClient {


    /**
     * 订单搜索
     * @param orderSearch 订单搜索参数
     * @return 订单列表
     */
    @PutMapping(value = FeignInsideAuthConfig.FEIGN_INSIDE_URL_PREFIX + "/insider/searchOrder/getOrderPage")
    ServerResponseEntity<EsPageVO<EsOrderVO>> getOrderPage(@RequestBody OrderSearchDTO orderSearch);

}
