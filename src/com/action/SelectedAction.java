package com.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.model.Product;
import com.util.Constant;
import com.util.ProductUtil;

public class SelectedAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String model;
	private String type;
	private String brand;

	public String find() {
		try {
			brand = new String(brand.getBytes("ISO8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Product> pList = getProductDao().selectAllProductBySubtype(type);
		List<Product> similar = new ArrayList<Product>();
		for (Product p : pList) {
			if (ProductUtil.transBrand(brand).equalsIgnoreCase(ProductUtil.transBrand(p.getBrand()))) {
				if (compare(model, p.getModel())) {
					similar.add(p);
				}
			}
		}
		// Remove duplicate records
		for (int i = 0; i < similar.size(); i++) {
			if(similar.size() == 1) break;
			for (int j = i; j < similar.size(); j++) {
				if (similar.get(i).getModel().equals(similar.get(j).getModel())) {
					if (similar.get(i).getRetailer().equals(similar.get(j).getRetailer())) {
						similar.remove(j);
					}
				}
			}
		}
		request.setAttribute("similar", similar);
		return SUCCESS;
	}

	public static boolean compare(String s1, String s2) {
		String[] str1 = s1.split("\\(|\\)|/| ");
		String[] str2 = s2.split("\\(|\\)|/| ");
		for (String t : str1) {
			if (t.length() <= 2 || "".equals(t.trim()))
				continue;
			for (String r : str2) {
				if (r.length() <= 2 || "".equals(r.trim()))
					continue;
				if (t.equalsIgnoreCase(r)) {
					return true;
				}
			}
		}
		return false;
	}

	public String search() {
		String name = request.getParameter("n");
		String p = request.getParameter("page");
		int page = p == null ? 1 : Integer.parseInt(p);
		List<Product> pList = getProductDao().selectProductByName(name, page);
		int count = getProductDao().selectProductByNameCount(name);
		request.setAttribute("pList", pList);
		request.setAttribute("sname", name);
		request.setAttribute("currentPage", page);
		request.setAttribute("pListCount", count);
		request.setAttribute("pageCount", count % Constant.recordsPerPage == 0 ? 
				(count / Constant.recordsPerPage):(count / Constant.recordsPerPage + 1));
		return SUCCESS;
	}
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
