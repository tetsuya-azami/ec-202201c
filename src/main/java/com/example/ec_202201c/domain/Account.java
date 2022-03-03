package com.example.ec_202201c.domain;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class Account extends User {

	private static final long serialVersionUID = 1L;

	private final com.example.ec_202201c.domain.User user;

	/**
	 * 認可用のロールを付与したユーザを設定
	 *
	 * @param user ユーザ情報
	 * @param authorities 権限リスト
	 */
	public Account(com.example.ec_202201c.domain.User user,
			Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		this.user = user;
	}

	/**
	 * ユーザ情報を返す
	 *
	 * @return ユーザ情報
	 */
	public com.example.ec_202201c.domain.User getUser() {
		return user;
	}
}
