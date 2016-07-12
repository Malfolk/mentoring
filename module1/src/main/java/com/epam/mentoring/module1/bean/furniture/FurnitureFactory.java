package com.epam.mentoring.module1.bean.furniture;

import java.util.List;

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
		List<AbstractPart> parts = furniture.getParts();
		addPartsToContainer(parts, new Leg(), 4);
		addPartsToContainer(parts, new Side(), 4);
		addPartsToContainer(parts, new Top(), 1);

		return furniture;
	}

	public static Furniture getChair()
	{
		Furniture furniture = new Furniture();
		List<AbstractPart> parts = furniture.getParts();
		addPartsToContainer(parts, new Leg(), 4);
		addPartsToContainer(parts, new Side(), 1);
		addPartsToContainer(parts, new Top(), 1);

		return furniture;
	}

	public static Furniture getNightstand()
	{
		Furniture furniture = new Furniture();
		List<AbstractPart> parts = furniture.getParts();
		addPartsToContainer(parts, new Leg(), 4);
		addPartsToContainer(parts, new Side(), 4);
		addPartsToContainer(parts, new Top(), 1);

		return furniture;
	}

	private static void addPartsToContainer(List<AbstractPart> parts, AbstractPart part, int count)
	{
		for(int i = 0; i < count; i++)
		{
			parts.add(part);
		}
	}
}
