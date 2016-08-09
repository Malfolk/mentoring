package com.epam.mentoring.module4;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ExampleOutOfMemoryErrorPermGenSpace
{
    // use jdk 6 :). set VM options -XX:PermSize=10m and -XX:MaxPermSize=10m.
    public static void main( String[] args )
    {
        List<String> stringList = new LinkedList<String>();
        Random random = new Random();
        while (true)
        {
            String s = new String(String.valueOf(random.nextFloat())).intern();
            stringList.add(s);
        }
    }
}
