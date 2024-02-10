package com.mall.cloud.product.service;

import com.mall.cloud.common.database.dto.PageDTO;
import com.mall.cloud.common.database.vo.PageVO;
import com.mall.cloud.product.model.SpuExtension;
import com.mall.cloud.product.vo.SpuExtensionVO;


public interface SpuExtensionService {

	/**
	 * 分页获取列表
	 * @param pageDTO 分页参数
	 * @return 列表分页数据
	 */
	PageVO<SpuExtensionVO> page(PageDTO pageDTO);

	/**
	 * 根据id获取
	 *
	 * @param spuExtendId id
	 * @return
	 */
	SpuExtensionVO getBySpuExtendId(Long spuExtendId);

	/**
	 * 保存
	 * @param spuExtension 商品扩展信息
	 */
	void save(SpuExtension spuExtension);

	/**
	 * 更新库存
	 * @param spuId 商品id
	 * @param count 商品数量
	 */
	void updateStock(Long spuId, Integer count);

	/**
	 * 根据id删除
	 * @param spuId
	 */
	void deleteById(Long spuId);

	/**
	 * 获取spu扩展信息
	 * @param spuId
	 * @return
	 */
    SpuExtension getBySpuId(Long spuId);
}
