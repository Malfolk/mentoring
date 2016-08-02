package com.epam.mentoring.module4;

public class ExampleOutOfMemoryErrorPermGenSpace
{
    // use jdk 7. set VM options -XX:PermSize=2m and -XX:MaxPermSize=2m.
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
