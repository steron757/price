package com.dao.impl;

import java.util.List;

import com.dao.BaseDao;
import com.dao.ProductDao;
import com.model.Product;

/**
 * Product Dao
 * 16/11/2013
 * 
 * @author Gang.Chen
 *
 */
@SuppressWarnings("unchecked")
public class ProductDaoImpl extends BaseDao implements ProductDao {
	
	/** (non-Javadoc)
	 * @see com.dao.impl.ProductDao#selectProduct()
	 */
	public List<Product> selectProduct(){
		return this.getSqlMapClientTemplate().queryForList("selectProduct");
	}
	
	/** (non-Javadoc)
	 * @see com.dao.impl.ProductDao#insertProduct(com.model.Product)
	 */
	public boolean insertProduct(Product product){
		Object obj = this.getSqlMapClientTemplate().insert("insertProduct", product);
		return obj != null;
	}
	
	/** (non-Javadoc)
	 * @see com.dao.impl.ProductDao#deleteProduct(java.lang.String)
	 */
	public boolean deleteProduct(String id){
		return this.getSqlMapClientTemplate().delete("deleteProduct", id) > 0;
	}
}
