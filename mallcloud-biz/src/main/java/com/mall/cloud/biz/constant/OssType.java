package com.mall.cloud.biz.constant;

/**
 * 文件上传存储类型
 */
public enum OssType {

	/**
	 * 阿里云oss
	 */
	ALI(0),

	/**
	 * minio
	 */
	MINIO(1),
;

	private final Integer value;

	public Integer value() {
		return value;
	}

	OssType(Integer value) {
		this.value = value;
	}

}
