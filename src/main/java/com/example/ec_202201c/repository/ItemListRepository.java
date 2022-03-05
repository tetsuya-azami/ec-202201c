package com.example.ec_202201c.repository;

import java.util.List;
import com.example.ec_202201c.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ItemListRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	private final RowMapper<Item> ITEM_ROWMAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		return item;
	};

	public List<Item> findAll() {
		String sql = "SELECT id, name, price_m, price_l, image_path FROM items ORDER BY price_m";
		List<Item> itemList = template.query(sql, ITEM_ROWMAPPER);
		return itemList;
	}

	// あいまい検索を行う
	public List<Item> findByLikeName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name, price_m, price_l, image_path ");
		sql.append("FROM items ");
		sql.append("WHERE name like :name ");
		sql.append("ORDER BY price_m");
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", '%' + name + '%');
		return template.query(sql.toString(), param, ITEM_ROWMAPPER);
	}
}
