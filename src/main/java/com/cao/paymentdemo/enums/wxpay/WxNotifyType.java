package com.cao.paymentdemo.enums.wxpay;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 该类是将所有native支付出现的通知接口均枚举出来
@AllArgsConstructor
@Getter
public enum WxNotifyType {

	/**
	 * 支付通知
	 */
	NATIVE_NOTIFY("/api/wx-pay/native/notify"),

	/**
	 * 支付通知
	 */
	NATIVE_NOTIFY_V2("/api/wx-pay-v2/native/notify"),


	/**
	 * 退款结果通知
	 */
	REFUND_NOTIFY("/api/wx-pay/refunds/notify");

	/**
	 * 类型
	 */
	private final String type;
}
