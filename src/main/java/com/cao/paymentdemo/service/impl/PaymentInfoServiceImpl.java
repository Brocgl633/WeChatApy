package com.cao.paymentdemo.service.impl;

import com.cao.paymentdemo.entity.PaymentInfo;
import com.cao.paymentdemo.enums.PayType;
import com.cao.paymentdemo.mapper.PaymentInfoMapper;
import com.cao.paymentdemo.service.PaymentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo> implements PaymentInfoService {

    @Override
    public void createPaymentInfo(String plainText) {
        log.info("记录支付日志");

        Gson gson = new Gson();
        HashMap plainTextMap = gson.fromJson(plainText, HashMap.class);

        // 订单号
        String orderNo = (String) plainTextMap.get("out_trade_no");
        // 交易编号
        String transactionId = (String) plainTextMap.get("transaction_id");
        // 交易类型
        String tradeType = (String) plainTextMap.get("trade_type");
        // 交易状态
        String tradeState = (String) plainTextMap.get("trade_state");
        // 用户支付金额
        Map<String, Object> amount = (Map) plainTextMap.get("amount");
        Integer payerTotal = ((Double) amount.get("payer_total")).intValue();

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderNo(orderNo);
        paymentInfo.setPaymentType(PayType.WXPAY.getType());    // 支付类型
        paymentInfo.setTransactionId(transactionId);
        paymentInfo.setTradeType(tradeType);
        paymentInfo.setTradeState(tradeState);
        paymentInfo.setPayerTotal(payerTotal);
        paymentInfo.setContent(plainText);

        baseMapper.insert(paymentInfo);
    }
}
