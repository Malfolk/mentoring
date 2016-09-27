package com.epam.mentoring.module10.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Siarhei_Karytka
 */
public class BookStorage
{
	private static List<Book> bookList = new ArrayList<Book>();

	static {
		bookList.add(new Book(1, 25, "Spring in Action", "Book about spring"));
		bookList.add(new Book(2, 30, "Crime and Punishment", "Detective book"));
		bookList.add(new Book(3, 8, "Bukvar", "You first book."));
	}

	public static List<Book> getBookList()
	{
		return bookList;
	}

	public static void setBookList(List<Book> bookList)
	{
		BookStorage.bookList = bookList;
	}

	public static Book getBookByID(long id)
	{
		for(Book book : bookList)
		{
			if(book.getId() == id)
			{
				return book;
			}
		}
		return null;
	}
}
