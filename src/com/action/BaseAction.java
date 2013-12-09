package com.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware,
SessionAware, ApplicationAware{

	private static final long serialVersionUID = 1L;

	public HttpServletRequest request;
	public Map<String, Object> session;
	public Map<String, Object> application;
	

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
	}
}
