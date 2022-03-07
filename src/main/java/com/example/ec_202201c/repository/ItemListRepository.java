package com.example.ec_202201c.repository;

import java.util.List;
import com.example.ec_202201c.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

	public Page<Item> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name, price_m, price_l, image_path FROM items ORDER BY price_m ");
		sql.append("LIMIT " + pageable.getPageSize() + " ");
		sql.append("OFFSET " + pageable.getOffset() + " ");

		// itemの総数を取得
		String ItemCountSql = "SELECT count(*) FROM items";
		int itemCount =
				template.queryForObject(ItemCountSql, new MapSqlParameterSource(), Integer.class);

		List<Item> itemList = template.query(sql.toString(), ITEM_ROWMAPPER);
		return new PageImpl<>(itemList, pageable, itemCount);
	}

	// あいまい検索を行う
	public Page<Item> findByLikeName(String name, Pageable pageable) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id, name, price_m, price_l, image_path ");
		sql.append("FROM items ");
		sql.append("WHERE name like :name ");
		sql.append("ORDER BY price_m");
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", '%' + name + '%');

		// あいまい検索の検索結果件数を取得
		String countSql = "SELECT count(*) FROM items WHERE name like :name";
		int itemCount = template.queryForObject(countSql.toString(), param, Integer.class);

		List<Item> itemList = template.query(sql.toString(), param, ITEM_ROWMAPPER);
		return new PageImpl<>(itemList, pageable, itemCount);
	}
}
