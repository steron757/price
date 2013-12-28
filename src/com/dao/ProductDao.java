package com.dao;

import java.util.List;

import com.model.Product;
import com.model.enums.Subcategory;

public interface ProductDao {

	public abstract List<Product> selectAllProduct();

	public abstract boolean insertProduct(Product product);

	public abstract boolean deleteProduct(String id);

	public abstract boolean insertHotProduct(Product p);

	/**
	 * iBatis paging method.
	 * 
	 * @param subList
	 * @param the number records begin with, which starts with 0. 
	 * @return
	 */
	public abstract List<Product> selectProductByType(List<Subcategory> subList, int start);

	public abstract Integer selectProductByTypeCount(List<Subcategory> subList);

	/**
	 * iBatis paging method.
	 * 
	 * @param subList
	 * @param the number records begin with, which starts with 0. 
	 * @return
	 */
	public abstract List<Product> selectProductBySubtype(String subClass, int start);
	
	public abstract Integer selectProductBySubtypeCount(String subClass);
	
	public abstract List<Product> selectAllProductBySubtype(String subClass);
}