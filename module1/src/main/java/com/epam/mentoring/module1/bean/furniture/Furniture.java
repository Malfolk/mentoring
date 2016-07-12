package com.epam.mentoring.module1.bean.furniture;

import java.util.LinkedList;
import java.util.List;

import com.epam.mentoring.module1.bean.furniture.parts.AbstractPart;

/**
 * @author Siarhei_Karytka
 */
public class Furniture
{
	List<AbstractPart> parts = new LinkedList<AbstractPart>();

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public List<AbstractPart> getParts()
	{
		return parts;
	}

	public void setParts(List<AbstractPart> parts)
	{
		this.parts = parts;
	}
}
