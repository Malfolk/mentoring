package com.epam.mentoring.module1.app;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.epam.mentoring.module1.bean.furniture.parts.Leg;
import com.epam.mentoring.module1.bean.furniture.parts.Side;
import com.epam.mentoring.module1.bean.furniture.parts.Top;

/**
 * @author Siarhei_Karytka
 */
public class PartsPriceCatalog
{
	private Map<String, Integer> prices = new HashMap<String, Integer>();

	{
		prices.put(Leg.class.getName(), 10);
		prices.put(Side.class.getName(), 25);
		prices.put(Top.class.getName(), 20);
	}

	//////////////////////////////////////////////
	//
	// Getters & Setters
	//
	//////////////////////////////////////////////

	public Map<String, Integer> getPrices()
	{
		return prices;
	}

	public void setPrices(Map<String, Integer> prices)
	{
		this.prices = prices;
	}
}
