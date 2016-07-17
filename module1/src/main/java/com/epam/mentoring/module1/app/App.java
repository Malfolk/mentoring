package com.epam.mentoring.module1.app;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.epam.mentoring.module1.bean.furniture.Furniture;
import com.epam.mentoring.module1.bean.furniture.FurnitureFactory;

public class App
{
    public static void main( String[] args )
    {
        List<Furniture> furnitureList = new LinkedList<Furniture>();
        furnitureList.add(FurnitureFactory.getBed());
        furnitureList.add(FurnitureFactory.getChair());
        furnitureList.add(FurnitureFactory.getNightstand());

        PriceCalculator priceCalculator = new Calculator();
        int price = priceCalculator.calculatePrice(furnitureList);
        System.out.println(price + "$");

        PartsCalculator partsCalculator = new Calculator();
        Map<String, Integer> parts = partsCalculator.calculateParts(furnitureList);
        for(Map.Entry entry : parts.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
