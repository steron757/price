package com.action;

import com.util.StringUtil;

public class ProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String type1;
	private String type2;

	@Override
	public String execute() throws Exception {
		if(StringUtil.isNotEmpty(type1)){
			return getProduct1();
		} else if(StringUtil.isNotEmpty(type2)){
			return getProduct2();
		}
		return super.execute();
	}

	private String getProduct1() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	private String getProduct2() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}
}
