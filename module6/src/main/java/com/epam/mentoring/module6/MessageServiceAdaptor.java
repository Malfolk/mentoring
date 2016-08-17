package com.epam.mentoring.module6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sun.plugin2.message.Message;

/**
 * @author Siarhei_Karytka
 */
public class MessageServiceAdaptor implements Iterator
{
	MessageService messageService;

	private int pageSize;
	private int index;
	private int messageID;
	private List<Message> messageList = new ArrayList<Message>();

	public MessageServiceAdaptor(int pageSize) throws Exception
	{
		if(pageSize <= 0)
		{
			throw new Exception("Page size should be a positive value greater than 0");
		}
		this.pageSize = pageSize;
	}

	public boolean hasNext()
	{
		if(messageList.isEmpty())
		{
			fillMessageList();
		}
		boolean result = false;
		if(index < messageList.size())
		{
			result = true;
		}

		return result;
	}

	public Object next()
	{
		if(hasNext())
		{
			Message message = messageList.get(index);
			if(index == (pageSize - 1)) // last element in current collection
			{
				messageID = message.getID();
				fillMessageList();
			}
			else
			{
				index++;
			}

			return message;
		}
		else
		{
			return null;
		}
	}

	private void fillMessageList()
	{
		messageList.clear();
		messageList.addAll(messageService.list(messageID, pageSize));
		index = 0;
	}

	// just to prevent jdk 5 compilation error
	public void remove()
	{

	}
}
