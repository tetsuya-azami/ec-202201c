package com.example.ec_202201c.common;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String MaxUploadSizeExceeded(MaxUploadSizeExceededException e,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("filesizeExceedException", messageSource
				.getMessage("filesizeExceedException", new String[] {}, Locale.getDefault()));
		return "redirect:/admin/item/toInsert";
	}

}
