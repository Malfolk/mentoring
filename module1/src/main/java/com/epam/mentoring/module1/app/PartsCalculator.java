package com.epam.mentoring.module1.app;

import java.util.List;

import com.epam.mentoring.module1.bean.furniture.Furniture;

/**
 * @author Siarhei_Karytka
 */
public interface PartsCalculator
{
	public void calculateParts(List<Furniture> furnitureList);
}
