package com.task;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Project Listener</br>
 * Normally execute every 1 day
 * 15/11/2013
 * 
 * @author Gang.Chen
 */
public class ContextListener implements ServletContextListener {

	private java.util.Timer timer = null;

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("Timer Destoried");
	}

	public void contextInitialized(ServletContextEvent event) {
		timer = new java.util.Timer(true);
		event.getServletContext().log("Timer Started");
		timer.schedule(new GetDataTask(), 10,  240 * 1000);
		event.getServletContext().log("Add task to schedule");
	}
}