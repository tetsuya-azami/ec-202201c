package com.example.ec_202201c.service;

import java.util.ArrayList;
import java.util.Collection;
import com.example.ec_202201c.domain.Account;
import com.example.ec_202201c.domain.User;
import com.example.ec_202201c.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * SpringSecurity ログインユーザ検索用Service
 *
 * @author Tetsuya Azami
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("入力されたメールアドレスは登録されていません");
		}

		// 認可権限の付与
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (user.getRole() == 1) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if (user.getRole() == 2) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		// 認可権限を付与したユーザを作成し、返す
		return new Account(user, authorities);
	}
}
