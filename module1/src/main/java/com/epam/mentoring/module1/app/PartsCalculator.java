package com.epam.mentoring.module1.app;

import java.util.List;
import java.util.Map;

import com.epam.mentoring.module1.bean.furniture.Furniture;

/**
 * @author Siarhei_Karytka
 */
public interface PartsCalculator
{
	public Map<String, Integer> calculateParts(List<Furniture> furnitureList);
}
