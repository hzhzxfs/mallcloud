package com.mall.cloud.auth.constant;

/**
 * 统一账户信息状态
 */
public enum AuthAccountStatusEnum {

	/**
	 * 启用
	 */
	ENABLE(1),

	/**
	 * 禁用
	 */
	DISABLE(0),

	/**
	 * 删除
	 */
	DELETE(-1)
	;

	private final Integer value;

	public Integer value() {
		return value;
	}

	AuthAccountStatusEnum(Integer value) {
		this.value = value;
	}

}
