package com.epam.mentoring.module5.loaders;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Siarhei_Karytka
 */
public class MyClassloader extends ClassLoader
{
	private String jarFile = "jar/test.jar"; //Path to the jar file

	public MyClassloader() {
		super(MyClassloader.class.getClassLoader()); //calls the parent class loader's constructor
	}

	public Class loadClass(String className) throws ClassNotFoundException {
		return findClass(className);
	}

	public Class findClass(String className) {
		byte classByte[];
		Class result = null;

		try {
			return findSystemClass(className);
		} catch (Exception e) {
		}

		try {
			JarFile jar = new JarFile(jarFile);
			JarEntry entry = jar.getJarEntry(className + ".class");
			InputStream is = jar.getInputStream(entry);
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			int nextValue = is.read();
			while (-1 != nextValue) {
				byteStream.write(nextValue);
				nextValue = is.read();
			}

			classByte = byteStream.toByteArray();
			result = defineClass(className, classByte, 0, classByte.length, null);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}
