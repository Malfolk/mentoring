package com.epam.mentoring.module5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 */
public class App
{

	public static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter full path to .jar file:");

		String pathToJar = br.readLine();
		JarFile jarFile = new JarFile(pathToJar);
		Enumeration<JarEntry> entries = jarFile.entries();

		URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
		URLClassLoader classLoader = URLClassLoader.newInstance(urls);

		while (entries.hasMoreElements())
		{
			JarEntry jarEntry = entries.nextElement();
			if (jarEntry.isDirectory() || !jarEntry.getName().endsWith(".class"))
			{
				continue;
			}
			// -6 because of .class
			String className = jarEntry.getName().substring(0, jarEntry.getName().length() - 6);
			className = className.replace('/', '.');
			Class c = classLoader.loadClass(className);
			c.newInstance();
			logger.trace(className + "loaded");
		}

		logger.trace("start App");
		System.out.println("Hello World!");
		logger.trace("exit App");
	}
}
