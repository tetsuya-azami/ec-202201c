package com.example.ec_202201c.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class ItemInsertForm {
	@NotBlank
	@Size(min = 1, max = 128)
	private String name;
	private MultipartFile uploadFile;

	@NotBlank
	@Size(min = 1, max = 300)
	private String description;

	@NotBlank
	@Pattern(regexp = "^[0-9]{1,5}$")
	private String price_m;

	@NotBlank
	@Pattern(regexp = "^[0-9]{1,5}$")
	private String price_l;

	public ItemInsertForm() {}

	public ItemInsertForm(String name, MultipartFile uploadFile, String description, String price_m,
			String price_l) {
		this.name = name;
		this.uploadFile = uploadFile;
		this.description = description;
		this.price_m = price_m;
		this.price_l = price_l;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice_m() {
		return price_m;
	}

	public void setPrice_m(String price_m) {
		this.price_m = price_m;
	}

	public String getPrice_l() {
		return price_l;
	}

	public void setPrice_l(String price_l) {
		this.price_l = price_l;
	}

	@Override
	public String toString() {
		return "itemInsertForm [description=" + description + ", name=" + name + ", price_l="
				+ price_l + ", price_m=" + price_m + ", uploadFile=" + uploadFile + "]";
	}
}
