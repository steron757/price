package com.action;

import java.util.ArrayList;
import java.util.List;

import com.dao.ProductDao;
import com.model.Product;
import com.model.enums.ProductType;
import com.model.enums.Retailer;
import com.model.enums.Subcategory;
import com.util.Constant;
import com.util.StringUtil;

public class ProductAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String type1;
	private String type2;

	/** which page want to go */
	private String page;
	
	private ProductDao productDao;
	
	@Override
	public String execute() throws Exception {
		String r = request.getParameter("r");	//retailer
		String t = request.getParameter("t");	//the case when query both retailer and classes
		if(StringUtil.isNotEmpty(r)){
			return ret();
		}
		List<Subcategory> classlist = new ArrayList<Subcategory>();
		request.setAttribute("currentPage", page);
		request.setAttribute("type1", type1);
		request.setAttribute("type2", type2);
		if(StringUtil.isNotEmpty(type1)){			//Choose parent node
			ProductType p = ProductType.getProductType(type1);
			classlist = ProductType.getSubcategories(p);
			request.setAttribute("type", ProductType.getProductType(type1).getDescription());	//Set the parent class
			for(Subcategory s : classlist){ 
				if(s == Subcategory.NULL)break;
				request.setAttribute("classlist", classlist);
			}
			return getProduct1(type1, t);
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
			return getProduct2(type2, t);
		}
		return super.execute();
	}

	/**
	 * Parent menu
	 * @param parentClass
	 * @param retailer
	 * @return
	 */
	private String getProduct1(String parentClass, String retailer) {
		List<Subcategory> subList = ProductType.getSubcategories(ProductType.getProductType(parentClass));
		int recordBegin = 0;
		if(StringUtil.isNotEmpty(page)){
			recordBegin = (Integer.parseInt(page) - 1) * Constant.recordsPerPage;
		}
		int count = 0;
		List<Product> pList = null;
		if(StringUtil.isNotEmpty(retailer)) {
			count = productDao.selectRetailerProductByTypeCount(subList, retailer);
			pList = productDao.selectRetailerProductByType(subList, retailer, recordBegin);
		} else {
			count = productDao.selectProductByTypeCount(subList);
			pList = productDao.selectProductByType(subList, recordBegin);
		}
		request.setAttribute("pList", pList);
		request.setAttribute("pListCount", count);
		request.setAttribute("pageCount", count % Constant.recordsPerPage == 0 ? 
				(count / Constant.recordsPerPage):(count / Constant.recordsPerPage + 1));
		return SUCCESS;
	}

	/**
	 * Sub menu
	 * @param subClass
	 * @param retailer
	 * @return
	 */
	private String getProduct2(String subClass, String retailer) {
		int recordBegin = 0;
		if(StringUtil.isNotEmpty(page)){
			recordBegin = (Integer.parseInt(page) - 1) * Constant.recordsPerPage;
		}
		int count = 0;
		List<Product> pList = null;
		if(StringUtil.isNotEmpty(retailer)) {
			pList = productDao.selectRetailerProductBySubtype(subClass, retailer, recordBegin);
			count = productDao.selectRetailerProductBySubtypeCount(subClass, retailer);
		} else {
			pList = productDao.selectProductBySubtype(subClass, recordBegin);
			count = productDao.selectProductBySubtypeCount(subClass);
		}
		request.setAttribute("pList", pList);
		request.setAttribute("pListCount", count);
		request.setAttribute("pageCount", count % Constant.recordsPerPage == 0 ? 
				(count / Constant.recordsPerPage) : (count / Constant.recordsPerPage + 1));
		return SUCCESS;
	}

	/**
	 * Get products by retailers
	 * @return
	 */
	public String ret(){
		String r = request.getParameter("r");
		String re = "";
		if("1".equals(r)){
			re = Retailer.BROADWAY.toString();
		}
		if("2".equals(r)){
			re = Retailer.SUNINGHK.toString();
		}
		if("3".equals(r)){
			re = Retailer.FORTRESS.toString();
		}
		int recordBegin = 0;
		if(StringUtil.isNotEmpty(page)){
			recordBegin = (Integer.parseInt(page) - 1) * Constant.recordsPerPage;
		}
		List<Product> pList = productDao.selectProductByRetailer(re, recordBegin);
		int count = productDao.selectProductByRetailerCount(re);
		request.setAttribute("r", r);
		request.setAttribute("currentPage", page);
		request.setAttribute("pList", pList);
		request.setAttribute("pListCount", count );
		request.setAttribute("pageCount", count % Constant.recordsPerPage == 0 ? 
				(count / Constant.recordsPerPage) : (count / Constant.recordsPerPage + 1));
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
