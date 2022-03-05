package com.example.ec_202201c.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserForm {

	/** 名前 */
	@NotBlank
	private String name;

	/** メールアドレス */
	@NotBlank
	@Email
	private String email;

	/** パスワード */
	@NotBlank
	private String password;

	/** 郵便番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$")
	private String zipcode;

	/** 住所 */
	@NotBlank
	private String address;

	/** 電話番号 */
	@Pattern(regexp = "^0[0-9]{1,4}-[0-9]{1,4}-[0-9]{4}$")
	private String telephone;

	public UserForm() {}

	public UserForm(@NotBlank String name, @NotBlank @Email String email, @NotBlank String password,
			@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$") String zipcode, @NotBlank String address,
			@Pattern(regexp = "^0[0-9]{1,4}-[0-9]{1,4}-[0-9]{4}$") String telephone) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.zipcode = zipcode;
		this.address = address;
		this.telephone = telephone;
	}

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

	@Override
	public String toString() {
		return "UserForm [address=" + address + ", email=" + email + ", name=" + name
				+ ", password=" + password + ", telephone=" + telephone + ", zipcode=" + zipcode
				+ "]";
	}
}
