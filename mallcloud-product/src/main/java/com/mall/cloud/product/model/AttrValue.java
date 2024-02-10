package com.mall.cloud.product.model;

import java.io.Serializable;

import com.mall.cloud.common.model.BaseModel;
/**
 * 属性值信息
 *
 * 
 * 
 */
public class AttrValue extends BaseModel implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    private Long attrValueId;

    /**
     * 属性ID
     */
    private Long attrId;

    /**
     * 属性值
     */
    private String value;

	public Long getAttrValueId() {
		return attrValueId;
	}

	public void setAttrValueId(Long attrValueId) {
		this.attrValueId = attrValueId;
	}

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "AttrValue{" +
				"attrValueId=" + attrValueId +
				",createTime=" + createTime +
				",updateTime=" + updateTime +
				",attrId=" + attrId +
				",value=" + value +
				'}';
	}
}
