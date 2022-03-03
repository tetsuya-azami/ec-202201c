package com.example.ec_202201c.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserForm {
	
	/** 名前 */
	@NotBlank(message="名前は必須です")
	private String name;
	/** メールアドレス */
	@NotBlank(message="メールアドレスを入力して下さい")
	@Email(message="Eメールの形式が不正です")
	private String email;
	/** パスワード */
	@NotBlank(message="パスワードを入力してください")
	private String password;
	/** 郵便番号 */
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}$",message="郵便番号の形式が不正です")
	private String zipcode;
	/** 住所 */
	@NotBlank(message="住所を入力してください")
	private String address;
	/** 電話番号 */
	@Pattern(regexp="^0[0-9]{1,4}-[0-9]{1,4}-[0-9]{4}$",message="電話番号の形式が不正です")
	private String telephone;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
}
