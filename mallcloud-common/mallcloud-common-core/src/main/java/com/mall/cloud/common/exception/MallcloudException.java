package com.mall.cloud.common.exception;

import com.mall.cloud.common.response.ResponseEnum;


public class mallcloudException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Object object;

	private ResponseEnum responseEnum;

	public mallcloudException(String msg) {
		super(msg);
	}

	public mallcloudException(String msg, Object object) {
		super(msg);
		this.object = object;
	}

	public mallcloudException(String msg, Throwable cause) {
		super(msg, cause);
	}


	public mallcloudException(ResponseEnum responseEnum) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
	}

	public mallcloudException(ResponseEnum responseEnum, Object object) {
		super(responseEnum.getMsg());
		this.responseEnum = responseEnum;
		this.object = object;
	}


	public Object getObject() {
		return object;
	}

	public ResponseEnum getResponseEnum() {
		return responseEnum;
	}

}
