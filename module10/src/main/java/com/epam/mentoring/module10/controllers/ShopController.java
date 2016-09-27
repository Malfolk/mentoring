package com.epam.mentoring.module10.controllers;

import javax.servlet.http.HttpServletRequest;

import com.epam.mentoring.module10.data.BookStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Siarhei_Karytka
 */
@Controller
@RequestMapping("/")
public class ShopController
{
	@RequestMapping(method = RequestMethod.GET)
	public String showShop(Model model, HttpServletRequest request) {
		model.addAttribute("books", BookStorage.getBookList());
		return "shop";
	}
}
