package com.action;

import java.util.ArrayList;
import java.util.List;

import com.dao.ProductDao;
import com.model.Product;
import com.model.enums.ProductType;
import com.model.enums.Subcategory;
import com.util.StringUtil;

public class ProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String type1;
	private String type2;

	private ProductDao productDao;
	
	@Override
	public String execute() throws Exception {
		List<Subcategory> classlist = new ArrayList<Subcategory>();
		if(StringUtil.isNotEmpty(type1)){			//Choose parent node
			ProductType p = ProductType.getProductType(type1);
			classlist = ProductType.getSubcategories(p);
			request.setAttribute("type", ProductType.getProductType(type1).getDescription());	//Set the parent class
			for(Subcategory s : classlist){ 
				if(s == Subcategory.NULL)break;
				request.setAttribute("classlist", classlist);
			}
			return getProduct1(type1);
		} else if(StringUtil.isNotEmpty(type2)){		//Choose child node
			Subcategory p = Subcategory.getSubcategories(type2);
			classlist = ProductType.getSubcategories(p.getParent());
			//Get parent class
			request.setAttribute("type", Subcategory.getSubcategories(type2).getParent().getDescription());	
			request.setAttribute("subtype", Subcategory.getSubcategories(type2).getDescription());
			for(Subcategory s : classlist){ 
				if(s == Subcategory.NULL)break;
				request.setAttribute("classlist", classlist);	//Child classes list
			}
			return getProduct2(type2);
		}
		return super.execute();
	}

	private String getProduct1(String parentClass) {
		List<Subcategory> subList = ProductType.getSubcategories(ProductType.getProductType(parentClass));
		List<Product> pList = productDao.selectProductByType(subList);
		request.setAttribute("pList", pList);
		return SUCCESS;
	}

	private String getProduct2(String subClass) {
		List<Product> pList = productDao.selectProductBySubtype(subClass);
		request.setAttribute("pList", pList);
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

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
}
