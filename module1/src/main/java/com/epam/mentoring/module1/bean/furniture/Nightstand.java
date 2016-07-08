package com.epam.mentoring.module1.bean.furniture;

/**
 * @author Siarhei_Karytka
 */
public class Nightstand extends AbstractFurniture
{
	private int side;
	private int top;

	public Nightstand()
	{
		this.leg = 4;
		this.side = 3;
		this.top = 1;
	}

	public int getPartsCount()
	{
		return 0;
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public int getSide()
	{
		return side;
	}

	public void setSide(int side)
	{
		this.side = side;
	}

	public int getTop()
	{
		return top;
	}

	public void setTop(int top)
	{
		this.top = top;
	}
}
