package com.model.enums;

/**
 * Retailer</br>
 * 13/11/2013
 * 
 * @author Gang.Chen
 *
 */
public enum Retailer {

	/** Broadway */
	BROADWAY("Broadway"),
	/** Suninghk */
	SUNINGHK("SuningHK"),
	/** Fortress */
	FORTRESS("Fortress"),
	/** NULL */
	NULL("");
	
	private Retailer(String name) {
		this.setName(name);
	}

	public static Retailer getRetailer(String name) {
		if (name == null)
			return NULL;
		for (Retailer r : Retailer.values()) {
			if (name.equalsIgnoreCase(r.getName())) {
				return r;
			}
		}
		return NULL;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
