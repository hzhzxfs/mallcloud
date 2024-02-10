package com.mall.cloud.product.service.impl;

import com.mall.cloud.product.mapper.SpuDetailMapper;
import com.mall.cloud.product.model.SpuDetail;
import com.mall.cloud.product.service.SpuDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//商品详情信息

@Service
public class SpuDetailServiceImpl implements SpuDetailService {

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Override
    public void save(SpuDetail spuDetail) {
        spuDetailMapper.save(spuDetail);
    }

    @Override
    public void update(SpuDetail spuDetail) {
        spuDetailMapper.update(spuDetail);
    }

    @Override
    public void deleteById(Long spuId) {
        spuDetailMapper.deleteById(spuId);
    }
}
