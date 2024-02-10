package com.mall.cloud.order.service;

import com.mall.cloud.order.model.OrderPayInfo;

//订单支付记录

public interface OrderPayInfoService {

	/**
	 * 保存订单支付记录
	 * @param orderPayInfo 订单支付记录
	 */
	void save(OrderPayInfo orderPayInfo);

	/**
	 * 更新订单支付记录
	 * @param orderPayInfo 订单支付记录
	 */
	void update(OrderPayInfo orderPayInfo);

	/**
	 * 根据订单支付记录id删除订单支付记录
	 * @param payId
	 */
	void deleteById(Long payId);
}
