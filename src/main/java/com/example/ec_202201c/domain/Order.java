package com.example.ec_202201c.domain;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private Integer id;
	private Integer userId;
	private Integer status;
	private Integer totalPrice;
	private Date orderDate;
	private String destinationName;
	private String destinationEmail;
	private String destinationZipCode;
	private String destinationAddress;
	private String destinationTel;
	private Date deliveryTime;
	private Integer paymentMethod;
	private User user;
	private List<OrderItem> orderItemList;

	public int getTax(Integer price) {
		return (int) (price * 0.1);
	}

	// 後で実装
	public int getCalcTotalPrice() {
		return 0;
	}
}
