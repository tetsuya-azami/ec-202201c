package com.example.ec_202201c.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Locale;
import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.exception.IllegalExtensionException;
import com.example.ec_202201c.form.ItemInsertForm;
import com.example.ec_202201c.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class AdminInsertController {
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AdminService adminService;

	@ModelAttribute
	public ItemInsertForm setUpItemInsertForm() {
		return new ItemInsertForm();
	}

	/**
	 * 商品登録ページを表示
	 *
	 * @return 商品登録ページ
	 */
	@RequestMapping("/item/toInsert")
	public String itemToInsert() {
		return "/admin/item_insert";
	}

	/**
	 * 商品登録
	 *
	 * @param itemInsertForm 商品登録情報
	 * @param result バリデーションエラー情報
	 * @param model リクエストスコープ
	 * @param redirectAttributes リダイレクト情報
	 * @return 商品一覧ページ
	 * @author Tetsuya Azami
	 */
	@RequestMapping("/item/insert")
	public String itemInsert(@Validated ItemInsertForm itemInsertForm, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		// 登録商品名の重複チェック
		Item item = adminService.findItemByName(itemInsertForm.getName());
		if (item != null) {
			result.rejectValue("name", "dupulicateItemNameError");
		}

		// 画像の保存処理
		String saveImageName = null;
		try {
			saveImageName = saveImage(itemInsertForm);
		} catch (IllegalExtensionException e) {
			e.printStackTrace();
			// 拡張子が.jpeg,.png以外の場合はエラーを返す
			result.rejectValue("uploadFile", "illegalExtensionError");
		} catch (IOException e) {
			e.printStackTrace();
			// 画像処理の途中でIOに関するエラーが発生した場合
			result.rejectValue("uploadFile", "uploadError");
		}

		if (result.hasErrors()) {
			return itemToInsert();
		}

		// itemに必要なフィールドをセットしてinsert
		Item saveItem = new Item();
		BeanUtils.copyProperties(itemInsertForm, saveItem);
		saveItem.setPriceM(Integer.parseInt(itemInsertForm.getPriceM()));
		saveItem.setPriceL(Integer.parseInt(itemInsertForm.getPriceL()));
		saveItem.setImagePath(saveImageName);
		adminService.insertItem(saveItem);

		redirectAttributes.addFlashAttribute("insertSuccess",
				messageSource.getMessage("insertSuccess", new String[] {}, Locale.getDefault()));
		return "redirect:/item/list";
	}

	/**
	 * 保存する画像の名前を作って返す
	 *
	 * @param itemInsertForm フォーム情報
	 * @return 画像名
	 * @throws IllegalExtensionException 拡張子が.jpegまたは.pngでない場合の例外オブジェクト
	 * @author Tetsuya Azami
	 * @throws IOException
	 */
	public String saveImage(ItemInsertForm itemInsertForm)
			throws IllegalExtensionException, IOException {
		String productName = itemInsertForm.getName();
		// IllegalExtensionExceptionが発生している場合は呼び出し元に例外を伝播させる
		String saveImageName = null;
		if (extractExtensionName(itemInsertForm) instanceof String) {
			saveImageName = productName + extractExtensionName(itemInsertForm);
		}

		// 画像保存先のパスオブジェクトを作成
		Path imagePath = Paths.get("src/main/resources/static/img_noodle/" + saveImageName);
		try (OutputStream os = Files.newOutputStream(imagePath, StandardOpenOption.CREATE)) {
			byte[] bytes = itemInsertForm.getUploadFile().getBytes();
			os.write(bytes);
			os.flush();
			return saveImageName;
		}
	}

	/**
	 * 拡張子が.jpegまたは.pngであることを確かめて拡張子名を返す
	 *
	 * @param itemInsertForm フォーム情報
	 * @return 拡張子名
	 * @throws IllegalExtensionException 不正な拡張子である旨のexception
	 * @author Tetsuya Azami
	 */
	public String extractExtensionName(ItemInsertForm itemInsertForm)
			throws IllegalExtensionException {
		String originalImageName = itemInsertForm.getUploadFile().getOriginalFilename();
		int lastDotIndex = originalImageName.lastIndexOf(".");
		if (lastDotIndex > 0) {
			String extension = originalImageName.substring(lastDotIndex).toLowerCase();
			if (".jpg".equals(extension) || ".jpeg".equals(extension) || ".png".equals(extension)) {
				return extension;
			}
		}
		throw new IllegalExtensionException("");
	}

}
