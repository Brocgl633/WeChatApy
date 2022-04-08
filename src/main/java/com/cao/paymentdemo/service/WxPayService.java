package com.cao.paymentdemo.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * @Author : CGL
 * @Date : 2022/2/28 16:56
 * @Desc :
 */
public interface WxPayService {

    Map<String, Object> nativePay(Long productId) throws Exception;

    void processOrder(Map<String, Object> bodyMap) throws GeneralSecurityException;

    void cancelOrder(String orderNo) throws Exception;

    String queryOrder(String orderNo) throws Exception;

    void checkOrderStatus(String orderNo) throws Exception;

    void refund(String orderNo, String reason) throws IOException;

    String queryRefund(String refundNo) throws IOException;

    void processRefund(Map<String, Object> bodyMap) throws GeneralSecurityException;

    String queryBill(String billDate, String type) throws IOException;

    String downloadBill(String billDate, String type) throws Exception;

    Map<String, Object> nativePayV2(Long productId, String remoteAddr) throws Exception;
}
