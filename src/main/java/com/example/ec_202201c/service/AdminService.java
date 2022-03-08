package com.example.ec_202201c.service;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;

	/**
	 * 商品名の重複チェック
	 *
	 * @param name 商品名
	 * @return 商品1件もしくはnull
	 * @author Tetsuya Azami
	 */
	public Item findItemByName(String name) {
		return adminRepository.findItemByName(name);
	}

}
