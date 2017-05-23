package com.jx372.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class ContextLoaderListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent)  { //어플리케이션이 톰캣에 의해서 생성될때
    	String contextConfigLocation = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");  
    	System.out.println("컨테이너 시작 하였습니다. - " + contextConfigLocation);   //어플리케이션 전체에서 실행할 것들을 여기서 실행해주면 된다.
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent)  {  //어플리케이션이 톰캣에 의해서 제거될때
    }
	
}
