package com.epam.mentoring.module10.controllers;

import javax.servlet.http.HttpServletRequest;

import com.epam.mentoring.module10.data.Book;
import com.epam.mentoring.module10.data.BookStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Siarhei_Karytka
 */
@Controller
@RequestMapping("/info")
public class BookInfoController
{
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String showInfo(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		Book book = BookStorage.getBookByID(id);
		model.addAttribute("book", book);
		return "bookInfo";
	}
}
