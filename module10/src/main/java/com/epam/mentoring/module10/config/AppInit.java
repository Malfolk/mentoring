package com.epam.mentoring.module10.config;

import javax.servlet.Filter;

import com.epam.mentoring.module10.logging.LoggingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Siarhei_Karytka
 */
public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer
{
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{
				WebConfig.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		LoggingFilter loggingFilter = new LoggingFilter();
		return new Filter[]{loggingFilter};
	}
}
