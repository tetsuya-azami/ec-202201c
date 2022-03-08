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
	private String priceM;

	@NotBlank
	@Pattern(regexp = "^[0-9]{1,5}$")
	private String priceL;

	public ItemInsertForm() {}

	public ItemInsertForm(@NotBlank @Size(min = 1, max = 128) String name, MultipartFile uploadFile,
			@NotBlank @Size(min = 1, max = 300) String description,
			@NotBlank @Pattern(regexp = "^[0-9]{1,5}$") String priceM,
			@NotBlank @Pattern(regexp = "^[0-9]{1,5}$") String priceL) {
		this.name = name;
		this.uploadFile = uploadFile;
		this.description = description;
		this.priceM = priceM;
		this.priceL = priceL;
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

	public String getPriceM() {
		return priceM;
	}

	public void setPriceM(String priceM) {
		this.priceM = priceM;
	}

	public String getPriceL() {
		return priceL;
	}

	public void setPriceL(String priceL) {
		this.priceL = priceL;
	}

	@Override
	public String toString() {
		return "ItemInsertForm [description=" + description + ", name=" + name + ", priceL="
				+ priceL + ", priceM=" + priceM + ", uploadFile=" + uploadFile + "]";
	}
}
