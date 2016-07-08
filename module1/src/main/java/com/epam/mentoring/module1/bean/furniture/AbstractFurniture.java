package com.epam.mentoring.module1.bean.furniture;

/**
 * @author Siarhei_Karytka
 */
public abstract class AbstractFurniture implements Furniture
{
	protected int leg;

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public int getLeg()
	{
		return leg;
	}

	public void setLeg(int leg)
	{
		this.leg = leg;
	}
}
