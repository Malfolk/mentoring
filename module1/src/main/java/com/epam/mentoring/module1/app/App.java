package com.epam.mentoring.module1.app;

import java.util.LinkedList;
import java.util.List;

import com.epam.mentoring.module1.bean.furniture.Furniture;
import com.epam.mentoring.module1.bean.furniture.FurnitureFactory;

public class App
{
    public static void main( String[] args )
    {
        List<Furniture> furnitureList = new LinkedList<Furniture>();
        furnitureList.add(FurnitureFactory.getBed());

        PriceCalculator priceCalculator = new Calculator();
        priceCalculator.calculatePrice(furnitureList);

        PartsCalculator partsCalculator = new Calculator();
        partsCalculator.calculateParts(furnitureList);
    }
}
