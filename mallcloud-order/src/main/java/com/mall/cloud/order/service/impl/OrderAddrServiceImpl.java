package com.mall.cloud.order.service.impl;

import com.mall.cloud.common.database.dto.PageDTO;
import com.mall.cloud.common.database.util.PageUtil;
import com.mall.cloud.common.database.vo.PageVO;
import com.mall.cloud.order.mapper.OrderAddrMapper;
import com.mall.cloud.order.model.OrderAddr;
import com.mall.cloud.order.service.OrderAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//用户订单配送地址

@Service
public class OrderAddrServiceImpl implements OrderAddrService {

    @Autowired
    private OrderAddrMapper orderAddrMapper;

    @Override
    public PageVO<OrderAddr> page(PageDTO pageDTO) {
        return PageUtil.doPage(pageDTO, () -> orderAddrMapper.list());
    }

    @Override
    public OrderAddr getByOrderAddrId(Long orderAddrId) {
        return orderAddrMapper.getByOrderAddrId(orderAddrId);
    }

    @Override
    public void save(OrderAddr orderAddr) {
        orderAddrMapper.save(orderAddr);
    }

    @Override
    public void update(OrderAddr orderAddr) {
        orderAddrMapper.update(orderAddr);
    }

    @Override
    public void deleteById(Long orderAddrId) {
        orderAddrMapper.deleteById(orderAddrId);
    }
}
