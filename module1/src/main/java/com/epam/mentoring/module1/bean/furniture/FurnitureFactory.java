package com.epam.mentoring.module1.bean.furniture;

import java.util.List;
import java.util.Map;

import com.epam.mentoring.module1.bean.furniture.parts.AbstractPart;
import com.epam.mentoring.module1.bean.furniture.parts.Leg;
import com.epam.mentoring.module1.bean.furniture.parts.Side;
import com.epam.mentoring.module1.bean.furniture.parts.Top;

/**
 * @author Siarhei_Karytka
 */
public class FurnitureFactory
{
	public static Furniture getBed()
	{
		Furniture furniture = new Furniture();
		Map<AbstractPart, Integer> parts = furniture.getParts();
		parts.put(new Leg(), 4);
		parts.put(new Side(), 4);
		parts.put(new Top(), 1);

		return furniture;
	}

	public static Furniture getChair()
	{
		Furniture furniture = new Furniture();
		Map<AbstractPart, Integer>  parts = furniture.getParts();
		parts.put(new Leg(), 4);
		parts.put(new Side(), 1);
		parts.put(new Top(), 1);

		return furniture;
	}

	public static Furniture getNightstand()
	{
		Furniture furniture = new Furniture();
		Map<AbstractPart, Integer>  parts = furniture.getParts();
		parts.put(new Leg(), 4);
		parts.put(new Side(), 4);
		parts.put(new Top(), 1);

		return furniture;
	}
}
