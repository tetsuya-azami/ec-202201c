package com.example.ec_202201c.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
	private Integer id;
	private Integer itemId;
	private Integer orderId;
	private Integer quantity;
	private Character Size;
	private Item item;
	private List<Item> orderToppingList;

	// 後で実装
	public int getSubTotal() {
		return 0;
	}
}
