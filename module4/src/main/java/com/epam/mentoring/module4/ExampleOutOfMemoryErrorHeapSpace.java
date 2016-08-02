package com.epam.mentoring.module4;

import java.util.ArrayList;
import java.util.List;


public class ExampleOutOfMemoryErrorHeapSpace
{
    // use jdk 7. set VM options -Xmx2m and -XX:-UseGCOverheadLimit to prevent GC error
    public static void main( String[] args )
    {
        List<List> lists = new ArrayList<List>();
        while (true)
        {
            lists.add(new ArrayList());
        }
    }
}
