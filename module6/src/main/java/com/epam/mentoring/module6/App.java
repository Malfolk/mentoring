package com.epam.mentoring.module6;

import sun.plugin2.message.Message;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        MessageServiceAdaptor messageServiceAdaptor = new MessageServiceAdaptor(10);

        while(messageServiceAdaptor.hasNext())
        {
            Message message = (Message) messageServiceAdaptor.next();
            System.out.println(message.getID());
        }
    }
}
