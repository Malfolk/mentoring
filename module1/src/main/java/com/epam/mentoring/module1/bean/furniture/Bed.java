package com.epam.mentoring.module1.bean.furniture;

/**
 * @author Siarhei_Karytka
 */
public class Bed extends AbstractFurniture
{
	private int side;

	public Bed()
	{
		this.leg = 4;
		this.side = 4;
	}

	public int getPartsCount()
	{

		return this.leg + this.side;
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
}
