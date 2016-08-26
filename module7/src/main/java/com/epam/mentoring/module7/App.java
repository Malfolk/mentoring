package com.epam.mentoring.module7;

import java.io.File;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    static long fileSize;
    static long filesCount;
    static long dirCount;
    static boolean run = true;

    public static void main( String[] args ) throws InterruptedException
    {
        runScanner();
        runCounter();

        File file = new File("d:\\TempInstall");

        long startTimeThread = System.currentTimeMillis();
        calculateFilesInDirThread(file);
        run = false;

        long endTimeThread = System.currentTimeMillis();
        System.out.println();
        System.out.println("Time Running(ms): " + (endTimeThread - startTimeThread));
        System.out.println("Directory total size(bytes): " + fileSize);
        System.out.println("Files: " + filesCount);
        System.out.println("Directories: " + dirCount);

        System.exit(0);
    }

    private static void calculateFilesInDirThread(File inputFile) throws InterruptedException
    {
        if(inputFile.isDirectory())
        {
            dirCount = dirCount + 1;
            for(final File file : inputFile.listFiles())
            {
                Thread t = new Thread(new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            calculateFilesInDirThread(file);
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
                t.join();
            }
        }
        else
        {
            filesCount = filesCount + 1;
            fileSize = fileSize + inputFile.length();
        }
    }

    private static void runCounter()
    {
        Thread t = new Thread(new Runnable()
        {
            public void run()
            {
                while (run)
                {
                    System.out.println("Running: " + filesCount + " files found...");
                    try
                    {
                        Thread.currentThread().sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    private static void runScanner() throws InterruptedException
    {
        Thread t = new Thread(new Runnable()
        {
            public void run()
            {
                Scanner scanner = new Scanner(System.in);
                while(run)
                {
                    if(scanner.hasNextLine())
                    {
                        String input = scanner.nextLine();
                        if(input != null) {
                            if("c".equals(input))
                            {
                                System.exit(0);
                            }
                        }
                    }

                }
                scanner.close();
            }
        });
        t.start();
    }
}
