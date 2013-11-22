package com.model.enums;

/**
 * Subcategories</br>
 * Refer from ProductType</br>
 * 19/11/2013
 * @author gang.chen
 * 
 */
public enum Subcategory {

	/** Landline */
	LANDLINE("landline", ProductType.PHONE, "固定電話"),
	/** Mobile phone */
	MOBILE("mobile", ProductType.PHONE, "移動電話"),
	/** Camera */
	CAMERA("camera", ProductType.PHOTOEQUIP, "數碼影像"),
	
	/** Fridge */
	FRIDGE("fridge", ProductType.LARGEAPPLIANCES, "雪櫃"),
	/** Air Condition */
	AIRCONDITIONER("airconditioner", ProductType.LARGEAPPLIANCES ,"冷氣機"),
	/** Washing machine */
	WASHINGMACHINE("washer", ProductType.LARGEAPPLIANCES, "洗衣機"),
	/** Kitchen appliance */
	KITCHEN("kitchen", ProductType.LARGEAPPLIANCES, "大型電器"),
	
	/** Household appliance */
	HOUSEHOLD("household", ProductType.SMALLAPPLIANCES, "家居電器"),
	/** Smart kitchen appliance, Suning only*/
	SMARTKITCHEN("smartkitchen", ProductType.SMALLAPPLIANCES, "小型電器"),
	/** Personal Care product, Fortress classifies it to Super type. */
	PERSONALCARE("personalcare", ProductType.SMALLAPPLIANCES, "個人護理"),
	
	/** Personal Computer */
	PC("pc", ProductType.PCOFFICE, "電腦"),
	/** Personal Computer Fitting */
	PCFITTING("pcfitting", ProductType.PCOFFICE, "電腦配件"),
	/** Office Product */
	OFFICE("office", ProductType.PCOFFICE, "文儀用品"),
	/** Office Fitting */
	OFFICEFITTING("officefitting", ProductType.PCOFFICE, "文儀配件"),
	
	/** Personal audio */
	PERSONAUDIO("personaudio", ProductType.VIDEO, "個人影音"),
	/** Household Audio */
	HOUSEHOLDAUDIO("householdaudio", ProductType.VIDEO, "家庭影音"),
	/** Television */
	TV("tv", ProductType.VIDEO, "電視"),
	/** DVD */
//	DVD("dvd", ProductType.VIDEO),
	/** Home Audio*/
//	HOMEAUDIO("homeaudio", ProductType.VIDEO),
	
	/** Null */
	NULL("", ProductType.NULL);

	public static Subcategory getSubcategories(String name) {
		if (name == null)
			return NULL;
		for (Subcategory r : Subcategory.values()) {
			if (name.equalsIgnoreCase(r.getName())) {
				return r;
			}
		}
		return NULL;
	}

	private Subcategory(String name, ProductType parent) {
		this.setName(name);
		this.setParent(parent);
	}
	
	private Subcategory(String name, ProductType parent, String description) {
		this.setName(name);
		this.setParent(parent);
		this.setDescription(description);
	}

	private String name;
	private ProductType parent;
	private String description;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductType getParent() {
		return parent;
	}

	public void setParent(ProductType parent) {
		this.parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static void main(String[] args) {
		System.out.println(ProductType.getProductType("camera1"));
	}

}
