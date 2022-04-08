package com.cao.paymentdemo.service;

import com.cao.paymentdemo.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cao.paymentdemo.enums.OrderStatus;

import java.util.List;

public interface OrderInfoService extends IService<OrderInfo> {

    // 通过商品id查询订单
    OrderInfo createOrderByProductId(Long productId);

    // 保存二维码地址
    void saveCodeUrl(String orderNo, String codeUrl);

    // 展示订单信息
    List<OrderInfo> listOrderByCreateTimeDesc();

    // 更新订单状态
    void updateStatusByOrderNo(String orderNo, OrderStatus orderStatus);

    String getOrderStatus(String orderNo);

    List<OrderInfo> getNoPayOrderByDuration(int i);

    OrderInfo getOrderByOrderNo(String orderNo);
}
