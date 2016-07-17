package com.epam.mentoring.module1.bean.furniture.parts;

/**
 * @author Siarhei_Karytka
 */
public class Side extends AbstractPart
{
	private int width = 40;
	private int height = 30;

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}
}
