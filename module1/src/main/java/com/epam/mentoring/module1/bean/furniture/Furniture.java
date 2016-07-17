package com.epam.mentoring.module1.bean.furniture;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.epam.mentoring.module1.bean.furniture.parts.AbstractPart;

/**
 * @author Siarhei_Karytka
 */
public class Furniture
{
	Map<AbstractPart, Integer> parts = new HashMap<AbstractPart, Integer>();

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public Map<AbstractPart, Integer> getParts()
	{
		return parts;
	}

	public void setParts(Map<AbstractPart, Integer> parts)
	{
		this.parts = parts;
	}
}
