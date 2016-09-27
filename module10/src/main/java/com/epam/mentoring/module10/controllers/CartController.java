package com.epam.mentoring.module10.controllers;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/cart/")
public class CartController
{
	@RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
	public String showCart(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		List<Long> cart = (List<Long>) request.getSession().getAttribute("cartList");
		if(cart == null)
		{
			cart = new ArrayList<Long>();
			request.getSession().setAttribute("cartList", cart);
		}
		cart.add(id);

		List<Book> booksInCart = new ArrayList<Book>();
		for(long bookID : cart)
		{
			Book book = BookStorage.getBookByID(bookID);
			booksInCart.add(book);
		}

		model.addAttribute("booksInCart", booksInCart);
		return "cart";
	}
}
