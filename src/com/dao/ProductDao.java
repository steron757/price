package com.dao;

import java.util.List;

import com.model.Product;
import com.model.enums.Subcategory;

public interface ProductDao {

	public abstract List<Product> selectAllProduct();

	public abstract boolean insertProduct(Product product);

	public abstract boolean deleteProduct(String id);

	public abstract boolean insertHotProduct(Product p);

	public abstract List<Product> selectProductByType(List<Subcategory> subList);
	
	public abstract List<Product> selectProductBySubtype(String subClass);
}