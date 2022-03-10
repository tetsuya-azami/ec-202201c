package com.example.ec_202201c.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // 設定用のクラス
@EnableWebSecurity // Spring Securityのウェブ用の機能を利用する
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	/** 静的リソースに対してセキュリティの設定を無効にする。 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/img_noodle/**", "/js/**", "/fonts/**");
	}

	/** 認可の設定やログイン/ログアウトに関する設定をするメソッド */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/cart/list", "/cart/delete", "/order/history", "/item/save",
						"/order/confirm", "/order/finishing", "/order/finished")
				.authenticated().and().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll();

		// ログインに関する設定
		http.formLogin().loginPage("/").loginProcessingUrl("/login").failureUrl("/?error=true")
				.defaultSuccessUrl("/item/list", false).usernameParameter("email")
				.passwordParameter("password");

		// ログアウトに関する設定
		http.logout().logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true);
	}

	/** ユーザ認証に使用するServiceクラス、エンコーダーを指定 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	/**
	 * パスワードのエンコーダーを指定
	 *
	 * @return Bcryptパスワードエンコーダーのインスタンス
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
