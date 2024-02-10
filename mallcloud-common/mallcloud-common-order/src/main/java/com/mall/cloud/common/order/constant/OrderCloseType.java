package com.mall.cloud.common.order.constant;

//订单关闭

public enum OrderCloseType {

    /**
     * 超时未支付
     */
    OVERTIME(1),

    /**
     * 买家取消
     */
    BUYER(4),

    /**
     * 已通过货到付款交易
     */
    DELIVERY(15),
    ;

    private Integer code;

    public Integer value() {
        return code;
    }

    OrderCloseType(Integer code) {
        this.code = code;
    }
}
