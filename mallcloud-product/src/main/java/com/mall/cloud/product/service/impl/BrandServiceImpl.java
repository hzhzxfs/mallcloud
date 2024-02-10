package com.mall.cloud.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.mall.cloud.common.cache.constant.CacheNames;
import com.mall.cloud.common.cache.util.RedisUtil;
import com.mall.cloud.common.constant.StatusEnum;
import com.mall.cloud.common.database.dto.PageDTO;
import com.mall.cloud.common.database.util.PageUtil;
import com.mall.cloud.common.database.vo.PageVO;
import com.mall.cloud.common.exception.mallcloudException;
import com.mall.cloud.product.dto.BrandDTO;
import com.mall.cloud.product.model.Brand;
import com.mall.cloud.product.mapper.BrandMapper;
import com.mall.cloud.product.service.BrandService;
import com.mall.cloud.product.service.CategoryBrandService;
import com.mall.cloud.api.product.vo.BrandVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 品牌信息
 *
 * 
 * 
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryBrandService categoryBrandService;

    @Override
    public PageVO<BrandVO> page(PageDTO pageDTO, BrandDTO brandDTO) {
        return PageUtil.doPage(pageDTO, () -> brandMapper.list(brandDTO));
    }

    @Override
    public BrandVO getByBrandId(Long brandId) {
        return brandMapper.getByBrandId(brandId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Brand brand, List<Long> categoryIds) {
        brand.setFirstLetter(brand.getFirstLetter().toUpperCase());
        brand.setStatus(StatusEnum.ENABLE.value());
        brandMapper.save(brand);
        categoryBrandService.saveByCategoryIds(brand.getBrandId(), categoryIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Brand brand, List<Long> categoryIds) {
        brandMapper.update(brand);
        categoryBrandService.updateByCategoryIds(brand.getBrandId(), categoryIds);
    }

    @Override
    public void deleteById(Long brandId) {
        if (getUseNum(brandId) > 0){
            throw new mallcloudException("有部分商品在使用该品牌，不能进行删除操作");
        }
        brandMapper.deleteById(brandId);
        categoryBrandService.deleteByBrandId(brandId);
    }

    @Override
    public List<BrandVO> getBrandByCategoryId(Long categoryId) {
        return brandMapper.getBrandByCategoryId(categoryId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = CacheNames.BRAND_TOP)
    public void updateBrandStatus(BrandDTO brandDTO) {
        BrandVO dbBrand = getByBrandId(brandDTO.getBrandId());
        if (Objects.isNull(dbBrand) || Objects.equals(dbBrand.getStatus(), brandDTO.getStatus())) {
            return;
        }
        brandMapper.updateBrandStatus(brandDTO);

    }

    @Override
    @Cacheable(cacheNames = CacheNames.BRAND_LIST_BY_CATEGORY, key = "#categoryId")
    public List<BrandVO> listByCategory(Long categoryId) {
        return brandMapper.listByCategory(categoryId);
    }

    @Override
    @Cacheable(cacheNames = CacheNames.BRAND_TOP)
    public List<BrandVO> topBrandList() {
        return brandMapper.topBrandList();
    }

    @Override
    @CacheEvict(cacheNames = CacheNames.BRAND_TOP)
    public void removeCache(List<Long> categoryIds) {
        if (CollUtil.isEmpty(categoryIds)) {
            return;
        }
        Set categoryIdSet = new HashSet<>(categoryIds);
        RedisUtil.deleteBatch(CacheNames.BRAND_LIST_BY_CATEGORY, new ArrayList<>(categoryIdSet));
    }

    private int getUseNum(Long brandId) {
        return brandMapper.getUseNum(brandId);
    }
}
