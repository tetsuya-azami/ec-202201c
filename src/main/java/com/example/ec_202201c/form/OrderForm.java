package com.example.ec_202201c.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class OrderForm {
	/** 宛先名 */
	@NotBlank
	@Size(min = 1, max = 128)
	private String destinationName;

	/** 宛先メールアドレス */
	@NotBlank
	@Size(min = 1, max = 128)
	@Email
	private String destinationEmail;

	/** 宛先郵便番号 */
	@NotBlank
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$")
	private String destinationZipCode;

	@NotBlank
	@Size(min = 1, max = 300)
	private String destinationAddress;

	/** 宛先電話番号 */
	@NotBlank
	@Pattern(regexp = "^0[0-9]{1,4}-[0-9]{1,4}-[0-9]{4}$")
	private String destinationTel;

	/** 配達時間(年月日) */
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String deliveryDate;

	/** 配達時間(時間) */
	@NotBlank
	@Pattern(regexp = "^[0-8]$")
	private String deliveryTime;

	/** 支払い方法 */
	@NotBlank
	@Pattern(regexp = "^[1-2]$")
	private String paymentMethod;

	public OrderForm() {}

	public OrderForm(String destinationName, String destinationEmail, String destinationZipCode,
			String destinationAddress, String destinationTel, String deliveryDate,
			String deliveryTime, String paymentMethod) {
		this.destinationName = destinationName;
		this.destinationEmail = destinationEmail;
		this.destinationZipCode = destinationZipCode;
		this.destinationAddress = destinationAddress;
		this.destinationTel = destinationTel;
		this.deliveryDate = deliveryDate;
		this.deliveryTime = deliveryTime;
		this.paymentMethod = paymentMethod;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipCode() {
		return destinationZipCode;
	}

	public void setDestinationZipCode(String destinationZipCode) {
		this.destinationZipCode = destinationZipCode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}


	@Override
	public String toString() {
		return "OrderForm [deliveryDate=" + deliveryDate + ", deliveryTime=" + deliveryTime
				+ ", destinationAddress=" + destinationAddress + ", destinationEmail="
				+ destinationEmail + ", destinationName=" + destinationName + ", destinationTel="
				+ destinationTel + ", destinationZipCode=" + destinationZipCode + ", paymentMethod="
				+ paymentMethod + "]";
	}

	/**
	 * フォームから受け取ってきた日付、時間をOrderDateのDeliveryTimeフィールドの形式に直す
	 *
	 * @return
	 * @throws ParseException
	 */
	public Date getOrderDeliveryTime() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.JAPANESE);
		Date date = dateFormat.parse(getDeliveryDate());
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.add(Calendar.HOUR_OF_DAY, generateDeliveryTimeByFormCode());
		Date orderDeliveryTime = cl.getTime();
		return orderDeliveryTime;
	}

	/**
	 * radioボックスで選択されたdeliveryTimeのコードを時刻に直す
	 *
	 * @return 配達時間のhour
	 */
	public Integer generateDeliveryTimeByFormCode() {
		// ex.)getDeliveryTime() = 0 の時 10をreturn
		// ex.)getDeliveryTime() = 1 の時 11をreturn
		Integer intDeliveryTime = Integer.parseInt("1" + getDeliveryTime());
		return intDeliveryTime;
	}
}
