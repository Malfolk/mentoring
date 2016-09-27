package com.epam.mentoring.module10.data;

/**
 * @author Siarhei_Karytka
 */
public class Book
{
	private long id;
	private int price;
	private String title;
	private String description;

	public Book(long id, int price, String title, String description)
	{
		this.id = id;
		this.price = price;
		this.title = title;
		this.description = description;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getPrice()
	{
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
}
