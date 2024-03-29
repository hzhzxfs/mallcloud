package com.mall.cloud.product.model;

import com.mall.cloud.common.model.BaseModel;

import java.io.Serializable;

//商品详情信息

public class SpuDetail extends BaseModel implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 商品id
	 */
	private Long spuId;

	/**
	 * 商品详情
	 */
	private String detail;

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "SpuDetail{" +
				"spuId=" + spuId +
				",createTime=" + createTime +
				",updateTime=" + updateTime +
				",detail=" + detail +
				'}';
	}
}
