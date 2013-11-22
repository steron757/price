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
	LANDLINE("landline", ProductType.PHONE, "�̶��Ԓ"),
	/** Mobile phone */
	MOBILE("mobile", ProductType.PHONE, "�Ƅ��Ԓ"),
	/** Camera */
	CAMERA("camera", ProductType.PHOTOEQUIP, "���aӰ��"),
	
	/** Fridge */
	FRIDGE("fridge", ProductType.LARGEAPPLIANCES, "ѩ��"),
	/** Air Condition */
	AIRCONDITIONER("airconditioner", ProductType.LARGEAPPLIANCES ,"���C"),
	/** Washing machine */
	WASHINGMACHINE("washer", ProductType.LARGEAPPLIANCES, "ϴ�C"),
	/** Kitchen appliance */
	KITCHEN("kitchen", ProductType.LARGEAPPLIANCES, "�������"),
	
	/** Household appliance */
	HOUSEHOLD("household", ProductType.SMALLAPPLIANCES, "�Ҿ����"),
	/** Smart kitchen appliance, Suning only*/
	SMARTKITCHEN("smartkitchen", ProductType.SMALLAPPLIANCES, "С�����"),
	/** Personal Care product, Fortress classifies it to Super type. */
	PERSONALCARE("personalcare", ProductType.SMALLAPPLIANCES, "�����o��"),
	
	/** Personal Computer */
	PC("pc", ProductType.PCOFFICE, "��X"),
	/** Personal Computer Fitting */
	PCFITTING("pcfitting", ProductType.PCOFFICE, "��X���"),
	/** Office Product */
	OFFICE("office", ProductType.PCOFFICE, "�ăx��Ʒ"),
	/** Office Fitting */
	OFFICEFITTING("officefitting", ProductType.PCOFFICE, "�ăx���"),
	
	/** Personal audio */
	PERSONAUDIO("personaudio", ProductType.VIDEO, "����Ӱ��"),
	/** Household Audio */
	HOUSEHOLDAUDIO("householdaudio", ProductType.VIDEO, "��ͥӰ��"),
	/** Television */
	TV("tv", ProductType.VIDEO, "�ҕ"),
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
