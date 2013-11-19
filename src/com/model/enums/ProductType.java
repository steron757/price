package com.model.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Product Type</br>
 * 18/11/2013
 * @author Gang.Chen
 *
 */
public enum ProductType {

	/** Mobile */
	PHONE("phone", "電話"),
	/** Camera */
	PHOTOEQUIP("photoequip", "攝影器材"),
	/** PC and Office */
	PCOFFICE("pcoffice", "電腦辦公"),
	/** Video */
	VIDEO("video", "影音"),
	/** Large appliances */
	LARGEAPPLIANCES("largeappliances", "大家電"),
	/** Small appliances */
	SMALLAPPLIANCES("smallappliances", "小家電"),
	
	
	/** Null */
	NULL("undefined");

	public static ProductType getProductType(String name) {
		if (name == null)
			return NULL;
		for (ProductType r : ProductType.values()) {
			if (name.equalsIgnoreCase(r.getName())) {
				return r;
			}
		}
		return NULL;
	}

	/**
	 * Get subcategories from a parent category
	 * @param type
	 * @return
	 */
	public static List<Subcategory> getSubcategories(ProductType type) {
		List<Subcategory> subcategories = new ArrayList<Subcategory>();
		if (type == NULL)
			return new ArrayList<Subcategory>();
		for (Subcategory r : Subcategory.values()) {
			if (type == r.getParent()) {
				subcategories.add(r);
			}
		}
		return subcategories;
	}

	private ProductType(String name) {
		this.setName(name);
	}
	private ProductType(String name, String description) {
		this.setName(name);
		this.setDescription(description);
	}

	private String name;
	private String description;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static void main(String[] args) {
		List<Subcategory> sublist = ProductType.getSubcategories(ProductType.PHONE);
		for(Subcategory s : sublist){
			System.out.println(s.getName());
		}
	}

}
