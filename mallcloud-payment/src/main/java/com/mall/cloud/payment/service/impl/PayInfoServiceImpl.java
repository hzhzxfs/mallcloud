package com.mall.cloud.payment.service.impl;

import cn.hutool.core.util.StrUtil;
import com.mall.cloud.api.leaf.feign.SegmentFeignClient;
import com.mall.cloud.api.order.feign.OrderFeignClient;
import com.mall.cloud.api.order.vo.OrderAmountVO;
import com.mall.cloud.common.exception.mallcloudException;
import com.mall.cloud.common.order.bo.PayNotifyBO;
import com.mall.cloud.common.response.ResponseEnum;
import com.mall.cloud.common.response.ServerResponseEntity;
import com.mall.cloud.common.rocketmq.config.RocketMqConstant;
import com.mall.cloud.common.security.AuthUserContext;
import com.mall.cloud.payment.bo.PayInfoBO;
import com.mall.cloud.payment.bo.PayInfoResultBO;
import com.mall.cloud.payment.constant.PayStatus;
import com.mall.cloud.payment.dto.PayInfoDTO;
import com.mall.cloud.payment.mapper.PayInfoMapper;
import com.mall.cloud.payment.model.PayInfo;
import com.mall.cloud.payment.service.PayInfoService;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

//订单支付记录

@Service
public class PayInfoServiceImpl implements PayInfoService {

    @Autowired
    private PayInfoMapper payInfoMapper;

    @Autowired
    private SegmentFeignClient segmentFeignClient;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private RocketMQTemplate orderNotifyTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayInfoBO pay(Long userId, PayInfoDTO payParam) {
        // 支付单号
        ServerResponseEntity<Long> segmentIdResponse = segmentFeignClient.getSegmentId(PayInfo.DISTRIBUTED_ID_KEY);
        if (!segmentIdResponse.isSuccess()) {
            throw new mallcloudException(ResponseEnum.EXCEPTION);
        }
        Long payId = segmentIdResponse.getData();
        List<Long> orderIds = payParam.getOrderIds();

        ServerResponseEntity<OrderAmountVO> ordersAmountAndIfNoCancelResponse = orderFeignClient.getOrdersAmountAndIfNoCancel(orderIds);
        // 如果订单已经关闭了，此时不能够支付了
        if (!ordersAmountAndIfNoCancelResponse.isSuccess()) {
            throw new mallcloudException(ordersAmountAndIfNoCancelResponse.getMsg());
        }
        OrderAmountVO orderAmount = ordersAmountAndIfNoCancelResponse.getData();
        PayInfo payInfo = new PayInfo();
        payInfo.setPayId(payId);
        payInfo.setUserId(userId);
        payInfo.setPayAmount(orderAmount.getPayAmount());
        payInfo.setPayStatus(PayStatus.UNPAY.value());
        payInfo.setSysType(AuthUserContext.get().getSysType());
        payInfo.setVersion(0);
        // 保存多个支付订单号
        payInfo.setOrderIds(StrUtil.join(StrUtil.COMMA, orderIds));
        // 保存预支付信息
        payInfoMapper.save(payInfo);
        PayInfoBO payInfoDto = new PayInfoBO();
        payInfoDto.setBody("商城订单");
        payInfoDto.setPayAmount(orderAmount.getPayAmount());
        payInfoDto.setPayId(payId);
        return payInfoDto;
    }

    @Override
    public PayInfo getByPayId(Long payId) {
        return payInfoMapper.getByPayId(payId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void paySuccess(PayInfoResultBO payInfoResult, List<Long> orderIds) {
        // 标记为支付成功状态
        PayInfo payInfo = new PayInfo();
        payInfo.setPayId(payInfoResult.getPayId());
        payInfo.setBizPayNo(payInfoResult.getBizPayNo());
        payInfo.setCallbackContent(payInfoResult.getCallbackContent());
        payInfo.setCallbackTime(new Date());
        payInfo.setPayStatus(PayStatus.PAYED.value());
        payInfoMapper.update(payInfo);
        // 发送消息，订单支付成功
        SendStatus sendStatus = orderNotifyTemplate.syncSend(RocketMqConstant.ORDER_NOTIFY_TOPIC, new GenericMessage<>(new PayNotifyBO(orderIds))).getSendStatus();
        if (!Objects.equals(sendStatus, SendStatus.SEND_OK)) {
            // 消息发不出去就抛异常，因为订单回调会有多次，几乎不可能每次都无法发送出去，发的出去无所谓因为接口是幂等的
            throw new mallcloudException(ResponseEnum.EXCEPTION);
        }
    }

    @Override
    public Integer getPayStatusByOrderIds(String orderIds) {
        return payInfoMapper.getPayStatusByOrderIds(orderIds);
    }

    @Override
    public Integer isPay(String orderIds, Long userId) {
        return payInfoMapper.isPay(orderIds, userId);
    }
}
