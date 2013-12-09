package com.dao.impl;

import java.util.List;

import com.dao.BaseDao;
import com.dao.ProductDao;
import com.model.Product;
import com.model.enums.Subcategory;

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
	 * @see com.dao.impl.ProductDao#selectAllProduct()
	 */
	public List<Product> selectAllProduct(){
		return this.getSqlMapClientTemplate().queryForList("selectAllProduct");
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

	/** (non-Javadoc)
	 * @see com.dao.impl.ProductDao#insertHotProduct(java.lang.String)
	 */
	public boolean insertHotProduct(Product product) {
		Object obj = this.getSqlMapClientTemplate().insert("insertHotProduct", product);
		return obj != null;
	}

	public List<Product> selectProductByType(List<Subcategory> pClass) {
		return this.getSqlMapClientTemplate().queryForList("selectProductByType", pClass);
	}

	public List<Product> selectProductBySubtype(String subClass) {
		return this.getSqlMapClientTemplate().queryForList("selectProductBySubtype", subClass);
	}
	
	
}
