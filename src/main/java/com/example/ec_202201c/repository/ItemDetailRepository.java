package com.example.ec_202201c.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.domain.OrderItem;
import com.example.ec_202201c.domain.OrderTopping;
import com.example.ec_202201c.domain.Topping;

@Repository
public class ItemDetailRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = new BeanPropertyRowMapper<>(Topping.class);
	
//	private static final RowMapper<OrderItem> OREDERITEM_ROW_MAPPER = (rs, i) ->{
//		OrderItem orderItem = new OrderItem();
//		orderItem.setId(rs.getInt("id"));
//		
//		return orderItem;
//	};

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

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Item load(Integer id) {
		String sql = "SELECT id, name, description, price_m, price_l, image_path FROM items WHERE id =:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return item;
	}

	public List<Topping> findAll() {
		String sql = "SELECT id, name, price_m, price_l FROM toppings";

		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);

		return toppingList;
	}

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
		sql.append("WHERE o.user_id = :userId AND o.status = 0;");

		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		Order order = template.query(sql.toString(), param, ORDER_CONDIRM_ROW_MAPPER);
		return order;
	}

	public void ordersUpdate(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		String updateSql = "UPDATE orders SET total_price = :totalPrice";
		template.update(updateSql, param);
	}

	public void ordersInsert(Order order) {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(order.getUserId());
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String insertSql = "INSERT INTO orders( user_id,  status,  total_price)"
				+ "                      VALUES(:userId, 0, :totalPrice)";
		template.update(insertSql, param);
	}

	public void OrderItemInsert(OrderItem orderItem) {
		String sql = "INSERT INTO order_items( item_id, order_id, quantity,  size)"
				+ " values                   (:itemId, :orderId, :quantity, :size)";

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		template.update(sql, param);
	}

	public void OrderToppingInsert(OrderTopping orderTopping) {
		String sql = "INSERT INTO order_toppings( topping_id,  order_item_id)"
				+ " values                      (:toppingId,  :orderItemId)";

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderTopping);
		template.update(sql, param);
	}
	
	public int findByItemId() {
		String sql = "SELECT MAX(id) id from order_items";
		
		return template.queryForObject(sql,new MapSqlParameterSource(), Integer.class);
	}
	
	public int findByOrderId() {
		String sql = "SELECT MAX(id) id from orders";
		
		return template.queryForObject(sql,new MapSqlParameterSource(), Integer.class);
	}
}
