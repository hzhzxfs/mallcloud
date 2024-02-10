package com.mall.cloud.search.feign;


import com.mall.cloud.api.dto.EsPageDTO;
import com.mall.cloud.api.feign.SearchOrderFeignClient;
import com.mall.cloud.api.vo.EsPageVO;
import com.mall.cloud.api.vo.search.EsOrderVO;
import com.mall.cloud.common.dto.OrderSearchDTO;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.search.manager.OrderSearchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

//商品搜索feign连接

@RestController
public class SearchOrderFeignController implements SearchOrderFeignClient {

    @Autowired
    private OrderSearchManager orderSearchManager;


    @Override
    public ServerResponseEntity<EsPageVO<EsOrderVO>> getOrderPage(OrderSearchDTO orderSearch) {
        EsPageDTO pageDTO = new EsPageDTO();
        pageDTO.setPageNum(orderSearch.getPageNum());
        pageDTO.setPageSize(orderSearch.getPageSize());
        return ServerResponseEntity.success(orderSearchManager.pageSearchResult(pageDTO, orderSearch));
    }
}
