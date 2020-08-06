package com.game.model.payment;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "split_info", "customerName", "additionalCharges", "paymentMode", "hash", "status",
		"error_Message", "paymentId", "productInfo", "customerEmail", "customerPhone", "merchantTransactionId",
		"amount", "udf2", "notificationId", "udf1", "udf5", "udf4", "udf3" })
public class PayUResponse {

	@JsonProperty("split_info")
	private String splitInfo;
	@JsonProperty("customerName")
	private String customerName;
	@JsonProperty("additionalCharges")
	private String additionalCharges;
	@JsonProperty("paymentMode")
	private String paymentMode;
	@JsonProperty("hash")
	private String hash;
	@JsonProperty("status")
	private String status;
	@JsonProperty("error_Message")
	private String errorMessage;
	@JsonProperty("paymentId")
	private String paymentId;
	@JsonProperty("productInfo")
	private String productInfo;
	@JsonProperty("customerEmail")
	private String customerEmail;
	@JsonProperty("customerPhone")
	private String customerPhone;
	@JsonProperty("merchantTransactionId")
	private String merchantTransactionId;
	@JsonProperty("amount")
	private String amount;
	@JsonProperty("udf2")
	private String udf2;
	@JsonProperty("notificationId")
	private String notificationId;
	@JsonProperty("udf1")
	private String udf1;
	@JsonProperty("udf5")
	private String udf5;
	@JsonProperty("udf4")
	private String udf4;
	@JsonProperty("udf3")
	private String udf3;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("split_info")
	public String getSplitInfo() {
		return splitInfo;
	}

	@JsonProperty("split_info")
	public void setSplitInfo(String splitInfo) {
		this.splitInfo = splitInfo;
	}

	@JsonProperty("customerName")
	public String getCustomerName() {
		return customerName;
	}

	@JsonProperty("customerName")
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@JsonProperty("additionalCharges")
	public String getAdditionalCharges() {
		return additionalCharges;
	}

	@JsonProperty("additionalCharges")
	public void setAdditionalCharges(String additionalCharges) {
		this.additionalCharges = additionalCharges;
	}

	@JsonProperty("paymentMode")
	public String getPaymentMode() {
		return paymentMode;
	}

	@JsonProperty("paymentMode")
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@JsonProperty("hash")
	public String getHash() {
		return hash;
	}

	@JsonProperty("hash")
	public void setHash(String hash) {
		this.hash = hash;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("error_Message")
	public String getErrorMessage() {
		return errorMessage;
	}

	@JsonProperty("error_Message")
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@JsonProperty("paymentId")
	public String getPaymentId() {
		return paymentId;
	}

	@JsonProperty("paymentId")
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	@JsonProperty("productInfo")
	public String getProductInfo() {
		return productInfo;
	}

	@JsonProperty("productInfo")
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	@JsonProperty("customerEmail")
	public String getCustomerEmail() {
		return customerEmail;
	}

	@JsonProperty("customerEmail")
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	@JsonProperty("customerPhone")
	public String getCustomerPhone() {
		return customerPhone;
	}

	@JsonProperty("customerPhone")
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	@JsonProperty("merchantTransactionId")
	public String getMerchantTransactionId() {
		return merchantTransactionId;
	}

	@JsonProperty("merchantTransactionId")
	public void setMerchantTransactionId(String merchantTransactionId) {
		this.merchantTransactionId = merchantTransactionId;
	}

	@JsonProperty("amount")
	public String getAmount() {
		return amount;
	}

	@JsonProperty("amount")
	public void setAmount(String amount) {
		this.amount = amount;
	}

	@JsonProperty("udf2")
	public String getUdf2() {
		return udf2;
	}

	@JsonProperty("udf2")
	public void setUdf2(String udf2) {
		this.udf2 = udf2;
	}

	@JsonProperty("notificationId")
	public String getNotificationId() {
		return notificationId;
	}

	@JsonProperty("notificationId")
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	@JsonProperty("udf1")
	public String getUdf1() {
		return udf1;
	}

	@JsonProperty("udf1")
	public void setUdf1(String udf1) {
		this.udf1 = udf1;
	}

	@JsonProperty("udf5")
	public String getUdf5() {
		return udf5;
	}

	@JsonProperty("udf5")
	public void setUdf5(String udf5) {
		this.udf5 = udf5;
	}

	@JsonProperty("udf4")
	public String getUdf4() {
		return udf4;
	}

	@JsonProperty("udf4")
	public void setUdf4(String udf4) {
		this.udf4 = udf4;
	}

	@JsonProperty("udf3")
	public String getUdf3() {
		return udf3;
	}

	@JsonProperty("udf3")
	public void setUdf3(String udf3) {
		this.udf3 = udf3;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}