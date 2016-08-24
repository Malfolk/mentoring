package com.epam.mentoring.module5.loaders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Siarhei_Karytka
 */
public class MyClassloader extends ClassLoader
{
	private String pathToJar; //Path to the jar file

	public MyClassloader(String pathToJar)
	{
		super(MyClassloader.class.getClassLoader()); //calls the parent class loader's constructor
		this.pathToJar = pathToJar;
	}

	public Class findClass(String className)
	{
		byte classByte[];

		JarFile jar = null;
		try
		{
			jar = new JarFile(pathToJar);
		}
		catch (IOException e)
		{
			System.out.println("Jar is not loaded");
			e.printStackTrace();
		}

		ByteArrayOutputStream byteStream = null;
		try
		{
			JarEntry entry = jar.getJarEntry(className.replace(".", "/") + ".class");
			InputStream is = jar.getInputStream(entry);
			byteStream = new ByteArrayOutputStream();
			int nextValue = is.read();
			while (-1 != nextValue)
			{
				byteStream.write(nextValue);
				nextValue = is.read();
			}
		}
		catch (IOException e)
		{
			System.out.println("Error loading class from jar");
			e.printStackTrace();
		}

		classByte = byteStream.toByteArray();
		return  defineClass(className, classByte, 0, classByte.length);
	}
}
