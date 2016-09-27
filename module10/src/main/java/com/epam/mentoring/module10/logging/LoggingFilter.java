package com.epam.mentoring.module10.logging;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

/**
 * @author Siarhei_Karytka
 */
public class LoggingFilter extends AbstractRequestLoggingFilter
{

	private static final Logger log = LogManager.getLogger(LoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		long id = System.currentTimeMillis();
		log.debug(id + ": http request " + request.getRequestURI());
		super.doFilterInternal(request, response, filterChain);
	}

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {

	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {

	}
}
