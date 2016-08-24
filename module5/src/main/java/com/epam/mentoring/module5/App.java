package com.epam.mentoring.module5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.epam.mentoring.module5.loaders.MyClassloader;
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
		MyClassloader myClassloader = new MyClassloader(pathToJar);

		System.out.print("Enter full class name:");
		String fullClassName = br.readLine();
		Class clazz = myClassloader.loadClass(fullClassName);
		Object o = clazz.newInstance();

		logger.trace("-----" + clazz.getName() + " loaded");


	}
}
