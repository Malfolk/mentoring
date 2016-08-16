package com.epam.mentoring.module6;

import java.util.LinkedList;
import java.util.List;

import sun.plugin2.message.Message;

/**
 * @author Siarhei_Karytka
 */
public class MessageServiceAdaptor
{
	MessageService messageService;

	private int pageSize;

	public MessageServiceAdaptor(int pageSize)
	{
		this.pageSize = pageSize;
	}

	List<Message> getMessages()
	{
		List<Message> messageList = new LinkedList<Message>();

		int startID = 0;
		boolean hasMoreMessages = true;

		while (hasMoreMessages)
		{
			List<Message> pagedMessageList = messageService.list(startID, pageSize);
			if(pagedMessageList.size() == pageSize)
			{
				startID = pagedMessageList.get(pagedMessageList.size() - 1).getID();
			}
			else
			{
				hasMoreMessages = false;
			}

			messageList.addAll(pagedMessageList);
		}


		return messageList;
	}
}
