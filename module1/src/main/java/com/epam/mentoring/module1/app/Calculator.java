package com.epam.mentoring.module1.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.epam.mentoring.module1.bean.furniture.Furniture;
import com.epam.mentoring.module1.bean.furniture.parts.AbstractPart;

/**
 * @author Siarhei_Karytka
 */
public class Calculator implements PartsCalculator, PriceCalculator
{
	PartsPriceCatalog catalog = new PartsPriceCatalog();

	public void calculateParts(List<Furniture> furnitureList)
	{
		Map<String, Integer> parts = new HashMap<String, Integer>();
		for(Furniture furniture : furnitureList)
		{
			for(AbstractPart part : furniture.getParts())
			{
				String name = part.getClass().getName();
				if(parts.get(name) == null)
				{
					parts.put(name, 1);
				}
				else
				{
					int count = parts.get(name);
					count++;
					parts.put(name, count);
				}
			}
		}

		for(Map.Entry entry : parts.entrySet())
		{
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	public int calculatePrice(List<Furniture> furnitureList)
	{
		int price = 0;
		for(Furniture furniture : furnitureList)
		{
			for(AbstractPart part : furniture.getParts())
			{
				Map<String, Integer> prices = catalog.getPrices();
				price = price + prices.get(part.getClass().getName());
			}
		}
		System.out.println(price + "$");
		return price;
	}
}
