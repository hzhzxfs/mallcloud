package com.mall.cloud.product.service;

import com.mall.cloud.api.product.vo.CategoryVO;
import com.mall.cloud.product.dto.CategoryDTO;
import com.mall.cloud.product.model.Category;

import java.util.List;

/**
 * 分类信息
 *
 * 
 * 
 */
public interface CategoryAndSpuService {
	/**
	 * 分类的启用和禁用
	 * @param categoryDTO
	 * @return
	 */
    Boolean categoryEnableOrDisable(CategoryDTO categoryDTO);

}
