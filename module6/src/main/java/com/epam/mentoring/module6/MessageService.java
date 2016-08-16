package com.epam.mentoring.module6;

import java.util.List;

import sun.plugin2.message.Message;

/**
 * @author Siarhei_Karytka
 */
public interface MessageService
{
	/*
	Pagination supports next parameters:

	1. SizeID - page size

	2. StartID - message ID that points to the start of the page that should be returned

	- Pass 0 to get the first page;

	- Pass "ID of the last message on the current page"  to get the next page.
	 */
	List<Message> list(int startID, int size);
}
