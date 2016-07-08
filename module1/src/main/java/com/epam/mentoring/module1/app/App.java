package com.epam.mentoring.module1.app;

import java.util.LinkedList;
import java.util.List;

import com.epam.mentoring.module1.bean.furniture.AbstractFurniture;
import com.epam.mentoring.module1.bean.furniture.Bed;
import com.epam.mentoring.module1.bean.furniture.Chair;
import com.epam.mentoring.module1.bean.furniture.Nightstand;

public class App
{
    public static void main( String[] args )
    {
        List<AbstractFurniture> furnitureList = new LinkedList<AbstractFurniture>();
        furnitureList.add(new Bed());
        furnitureList.add(new Nightstand());
        furnitureList.add(new Chair());

        PartsCalculator partsCalculator = new PartsCalculator();

        int partsCount = partsCalculator.calculateParts(furnitureList);
        System.out.print("Required parts count: " + partsCount);
    }
}
