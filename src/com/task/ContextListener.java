package com.task;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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