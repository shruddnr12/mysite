package com.jx372.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//@WebFilter("/*")
public class EncodingFilter implements Filter {
	private String encoding;
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("encoding filter initalize.....");
		encoding = fConfig.getInitParameter("encoding");
		if(encoding == null){	// Charset default 처리
			encoding = "UTF-8";
		}
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//request에 대한 처리
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
		//response에 대한 처리
	}

	public void destroy() {
	}


}
