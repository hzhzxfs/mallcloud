package com.mall.cloud.api.vo.search;

import io.swagger.v3.oas.annotations.media.Schema;

public class AttrValueSearchVO {

    @Schema(description = "规格值id" )
    private Long attrValueId;

    @Schema(description = "规格值名称" )
    private String attrValueName;

    public Long getAttrValueId() {
        return attrValueId;
    }

    public void setAttrValueId(Long attrValueId) {
        this.attrValueId = attrValueId;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public void setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
    }

    @Override
    public String toString() {
        return "AttrValueVO{" +
                "attrValueId=" + attrValueId +
                ", attrValueName='" + attrValueName + '\'' +
                '}';
    }
}
