package com.example.ec_202201c.repository;

import java.util.ArrayList;
import java.util.List;
import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.domain.OrderItem;
import com.example.ec_202201c.domain.OrderTopping;
import com.example.ec_202201c.domain.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/** @author Tetsuya Azami */
@Repository
public class OrderConfirmRepository {
	private final ResultSetExtractor<Order> ORDER_CONDIRM_ROW_MAPPER = (rs) -> {
		Order order = new Order();
		// orderドメインのフィールドであるorderItemListを作ってorderにセット
		List<OrderItem> orderItemList = new ArrayList<>();
		order.setOrderItemList(orderItemList);
		OrderItem orderItem = null;
		List<OrderTopping> orderToppingList = null;

		int preOrderItemId = -1;
		while (rs.next()) {
			int orderItemId = rs.getInt("oi_id");
			if (orderItemId != preOrderItemId) {
				orderItem = new OrderItem();
				// orderItemドメインのフィールドであるorderToppingListを作ってorderItemにセット
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);

				// 価格、画像パスを取得するためのItemドメインを作ってorderItemにセット
				Item item = new Item();
				orderItem.setItem(item);

				// orderItem数量、サイズ
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				orderItem.setSize(rs.getString("oi_size").charAt(0));

				// itemの名前、imageのパス、価格をitemオブジェクトに入れる
				item.setName(rs.getString("i_name"));
				item.setImagePath(rs.getString("i_image_path"));
				if (orderItem.getSize() == 'M') {
					item.setPriceM(rs.getInt("i_price"));
				} else if (orderItem.getSize() == 'L') {
					item.setPriceL(rs.getInt("i_price"));
				}

				order.setTotalPrice(rs.getInt("o_total_price"));
				orderItemList.add(orderItem);
				preOrderItemId = orderItemId;
			}

			OrderTopping orderTopping = new OrderTopping();
			// orderToppingドメインのフィールドであるToppingを作ってToppingにセット
			Topping topping = new Topping();
			orderTopping.setTopping(topping);

			// toppingの名前、価格をtoppingオブジェクトに入れる
			topping.setName(rs.getString("t_name"));
			if ('M' == orderItem.getSize()) {
				topping.setPriceM(rs.getInt("t_price"));
			} else if ('L' == orderItem.getSize()) {
				topping.setPriceL(rs.getInt("t_price"));
			}
			orderToppingList.add(orderTopping);
		}
		return order;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	public Order findShoppingCartByUserId(Integer userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("oi.id oi_id, ");
		sql.append("i.name i_name, ");
		sql.append("i.image_path i_image_path, ");
		sql.append("oi.size oi_size, ");
		sql.append("CASE oi.size WHEN 'L' THEN i.price_l ");
		sql.append("WHEN 'M' THEN i.price_m ");
		sql.append("END i_price, ");
		sql.append("oi.quantity oi_quantity, ");
		sql.append("t.name t_name , ");
		sql.append("CASE oi.size WHEN 'L' THEN t.price_l ");
		sql.append("WHEN 'M' THEN t.price_m ");
		sql.append("END t_price, ");
		sql.append("o.total_price o_total_price ");
		sql.append("FROM orders o ");
		sql.append("INNER JOIN order_items oi ");
		sql.append("ON o.id = oi.order_id ");
		sql.append("LEFT OUTER JOIN order_toppings ot ");
		sql.append("ON oi.id = ot.order_item_id ");
		sql.append("LEFT OUTER JOIN items i ");
		sql.append("ON oi.item_id = i.id ");
		sql.append("LEFT OUTER JOIN toppings t ");
		sql.append("ON ot.topping_id = t.id ");
		sql.append("WHERE o.user_id = :userId AND o.status = 0 ");
		sql.append("ORDER BY oi_id;");

		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		Order order = template.query(sql.toString(), param, ORDER_CONDIRM_ROW_MAPPER);
		return order;
	}

	public void finishingOrder(Order order) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE orders SET ");
		if (order.getPaymentMethod() == 1) {
			sql.append("status = 1, ");
		} else if (order.getPaymentMethod() == 2) {
			sql.append("status = 2, ");
		}
		sql.append("order_date = CURRENT_DATE, ");
		sql.append("destination_name = :destinationName, ");
		sql.append("destination_email = :destinationEmail, ");
		sql.append("destination_zipcode = :destinationZipCode, ");
		sql.append("destination_address = :destinationAddress, ");
		sql.append("destination_tel = :destinationTel, ");
		sql.append("delivery_time = :deliveryTime, ");
		sql.append("payment_method = :paymentMethod ");
		sql.append("WHERE user_id = :userId AND status = 0;");

		SqlParameterSource param =
				new MapSqlParameterSource().addValue("destinationName", order.getDestinationName())
						.addValue("destinationEmail", order.getDestinationEmail())
						.addValue("destinationZipCode", order.getDestinationZipCode())
						.addValue("destinationAddress", order.getDestinationAddress())
						.addValue("destinationTel", order.getDestinationTel())
						.addValue("deliveryTime", order.getDeliveryTime())
						.addValue("paymentMethod", order.getPaymentMethod())
						.addValue("userId", order.getUser().getId());

		template.update(sql.toString(), param);
	}

}
