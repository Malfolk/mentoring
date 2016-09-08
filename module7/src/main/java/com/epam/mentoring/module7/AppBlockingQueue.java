package com.epam.mentoring.module7;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Siarhei_Karytka
 */
public class AppBlockingQueue
{
	static BlockingQueue<File> queue = new ArrayBlockingQueue(10000, true);
	private static AtomicLong filesSize = new AtomicLong();
	private static AtomicLong filesCount = new AtomicLong();
	private static AtomicLong tasksCounter = new AtomicLong();
	private static AtomicBoolean run = new AtomicBoolean(true);

	public static void main(String[] args) throws InterruptedException
	{
		File file = new File("d:\\TempInstall");
		queue.add(file);
		tasksCounter.incrementAndGet();

		runScanner();

		for(int i = 0; i < 10; i++)
		{
			Thread thread = new Thread(new Counter());
			thread.start();
		}

	}

	static class Counter implements Runnable
	{
		public void run()
		{
			while (tasksCounter.longValue() > 0 && run.get())
			{
				try
				{
					File directory = queue.poll(5, TimeUnit.SECONDS);
					if(directory != null)
					{
						for(File file : directory.listFiles())
						{
							if(file.isDirectory())
							{
								tasksCounter.incrementAndGet();
								queue.add(file);
							}
							else
							{
								filesSize.addAndGet(file.length());
								filesCount.addAndGet(1);
								System.out.println("Files found: " + filesCount);
							}
						}
						tasksCounter.decrementAndGet();
					}
				}
				catch (InterruptedException e)
				{
					throw new RuntimeException(e);
				}
			}
		}
	}

	// TODO I scanner is infinite waiting for input and program is not closing
	private static void runScanner() throws InterruptedException
	{
		Thread t = new Thread(new Runnable()
		{
			public void run()
			{
				Scanner scanner = new Scanner(System.in);
				while(tasksCounter.longValue() > 0)
				{
					String input = scanner.next();
					if(input != null) {
						if("c".equals(input))
						{
							run.set(false);
						}
					}
				}
				scanner.close();
			}
		});
		t.start();
	}
}
