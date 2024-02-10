package com.mall.cloud.order.mapper;

import com.mall.cloud.order.model.OrderPayInfo;
import org.apache.ibatis.annotations.Param;

//订单支付记录

public interface OrderPayInfoMapper {
	/**
	 * 保存订单支付记录
	 * @param orderPayInfo 订单支付记录
	 */
	void save(@Param("orderPayInfo") OrderPayInfo orderPayInfo);

	/**
	 * 更新订单支付记录
	 * @param orderPayInfo 订单支付记录
	 */
	void update(@Param("orderPayInfo") OrderPayInfo orderPayInfo);

	/**
	 * 根据订单支付记录id删除订单支付记录
	 * @param payId
	 */
	void deleteById(@Param("payId") Long payId);
}
