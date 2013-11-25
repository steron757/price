package com.dao;

import java.util.List;

import com.model.Product;

public interface ProductDao {

	public abstract List<Product> selectProduct();

	public abstract boolean insertProduct(Product product);

	public abstract boolean deleteProduct(String id);

	public abstract boolean insertHotProduct(Product p);

}