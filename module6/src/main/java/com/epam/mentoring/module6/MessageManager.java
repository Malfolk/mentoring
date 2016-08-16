package com.epam.mentoring.module6;

import java.util.List;

import sun.plugin2.message.Message;

/**
 * @author Siarhei_Karytka
 */
public class MessageManager
{
	MessageServiceAdaptor adaptor = new MessageServiceAdaptor(100);

	List<Message> getMessages()
	{
		return adaptor.getMessages();
	}
}
