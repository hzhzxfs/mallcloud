package com.mall.cloud.api.product.vo;

import com.mall.cloud.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 属性与属性分组关联信息VO
 */
public class AttrCategoryVO extends BaseVO{
    private static final long serialVersionUID = 1L;

    @Schema(description = "属性与分类关联id" )
    private Long attrCategoryId;

    @Schema(description = "分类id" )
    private Long categoryId;

    @Schema(description = "属性id" )
    private Long attrId;

	public Long getAttrCategoryId() {
		return attrCategoryId;
	}

	public void setAttrCategoryId(Long attrCategoryId) {
		this.attrCategoryId = attrCategoryId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getAttrId() {
		return attrId;
	}

	public void setAttrId(Long attrId) {
		this.attrId = attrId;
	}

	@Override
	public String toString() {
		return "AttrCategoryVO{" +
				"attrCategoryId=" + attrCategoryId +
				",createTime=" + createTime +
				",updateTime=" + updateTime +
				",categoryId=" + categoryId +
				",attrId=" + attrId +
				'}';
	}
}
