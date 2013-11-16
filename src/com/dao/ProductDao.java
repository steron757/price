package com.dao;

import java.util.List;

import com.model.Product;

@SuppressWarnings("unchecked")
public class ProductDao extends BaseDao {
	
	public List<Product> selectProduct(){
		return this.getSqlMapClientTemplate().queryForList("selectProduct");
	}
	
	public boolean insertProduct(Product product){
		Object obj = this.getSqlMapClientTemplate().insert("insertProduct", product);
		return obj != null;
	}
	
	public boolean deleteProduct(String id){
		return this.getSqlMapClientTemplate().delete("deleteProduct", id) > 0;
	}
}
