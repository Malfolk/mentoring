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

	public Map<String, Integer> calculateParts(List<Furniture> furnitureList)
	{
		Map<String, Integer> parts = new HashMap<String, Integer>();
		for(Furniture furniture : furnitureList)
		{
			for(Map.Entry<AbstractPart, Integer> entry : furniture.getParts().entrySet())
			{
				String name = entry.getKey().getClass().getName();
				if(parts.get(name) == null)
				{
					parts.put(name, entry.getValue());
				}
				else
				{
					int count = parts.get(name) + entry.getValue();
					parts.put(name, count);
				}
			}
		}

		return parts;
	}

	public int calculatePrice(List<Furniture> furnitureList)
	{
		int price = 0;
		for(Furniture furniture : furnitureList)
		{
			for(Map.Entry<AbstractPart, Integer> entry : furniture.getParts().entrySet())
			{
				int itemPrice = catalog.getPrice(entry.getKey());
				price = price + itemPrice * entry.getValue();
			}
		}

		return price;
	}
}
