package com.epam.mentoring.module1.app;

import java.util.List;

import com.epam.mentoring.module1.bean.furniture.AbstractFurniture;

/**
 * @author Siarhei_Karytka
 */
public class PartsCalculator
{
	public int calculateParts(List<AbstractFurniture> furnitureList)
	{
		int sum = 0;
		for(AbstractFurniture furniture : furnitureList)
		{
			sum = sum + furniture.getPartsCount();
		}

		return sum;
	}
}
