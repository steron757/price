package com.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.dao.BaseDao;
import com.dao.ProductDao;
import com.model.Product;
import com.model.enums.Subcategory;
import com.util.Constant;

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

	public List<Product> selectProductByType(List<Subcategory> pClass, int start) {
		return this.getSqlMapClientTemplate().queryForList("selectProductByType", 
				pClass, start, Constant.recordsPerPage);
	}

	public List<Product> selectProductBySubtype(String subClass, int start) {
		return this.getSqlMapClientTemplate().queryForList("selectProductBySubtype", 
				subClass, start, Constant.recordsPerPage);
	}

	public Integer selectProductByTypeCount(List<Subcategory> subList) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("selectProductByTypeCount", subList);
	}

	public Integer selectProductBySubtypeCount(String subClass) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("selectProductBySubtypeCount", subClass);
	}

	public List<Product> selectAllProductBySubtype(String subClass) {
		return this.getSqlMapClientTemplate().queryForList("selectAllProductBySubtype", subClass);
	}

	public List<Product> selectProductByRetailer(String retailer, int start) {
		return this.getSqlMapClientTemplate().queryForList("selectProductByRetailer", retailer,
				start, Constant.recordsPerPage);
	}
	
	public Integer selectProductByRetailerCount(String retailer) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("selectProductByRetailerCount", retailer);
	}

	public int selectRetailerProductByTypeCount(List<Subcategory> subList, String retailer) {
		if("1".equals(retailer))
			return (Integer) this.getSqlMapClientTemplate().queryForObject("selectBroadwayProductByTypeCount", subList);
		if("2".equals(retailer))
			return (Integer) this.getSqlMapClientTemplate().queryForObject("selectSuningProductByTypeCount", subList);
		if("3".equals(retailer))
			return (Integer) this.getSqlMapClientTemplate().queryForObject("selectFortressProductByTypeCount", subList);
		return 0;
	}

	public List<Product> selectRetailerProductByType(List<Subcategory> subList, String retailer, int start) {
		if("1".equals(retailer))
			return this.getSqlMapClientTemplate().queryForList("selectBroadwayProductByType", subList, start, Constant.recordsPerPage);
		if("2".equals(retailer))
			return this.getSqlMapClientTemplate().queryForList("selectSuningProductByType", subList, start, Constant.recordsPerPage);
		if("3".equals(retailer))
			return this.getSqlMapClientTemplate().queryForList("selectFortressProductByType", subList, start, Constant.recordsPerPage);
		return new ArrayList<Product>();
	}

	public int selectRetailerProductBySubtypeCount(String subClass, String retailer) {
		List<Subcategory> subList = new ArrayList<Subcategory>();
		subList.add(Subcategory.getSubcategories(subClass));
		if("1".equals(retailer))
			return (Integer) this.getSqlMapClientTemplate().queryForObject("selectBroadwayProductByTypeCount", subList);
		if("2".equals(retailer))
			return (Integer) this.getSqlMapClientTemplate().queryForObject("selectSuningProductByTypeCount", subList);
		if("3".equals(retailer))
			return (Integer) this.getSqlMapClientTemplate().queryForObject("selectFortressProductByTypeCount", subList);
		return 0;
	}

	public List<Product> selectRetailerProductBySubtype(String subClass, String retailer, int start) {
		List<Subcategory> subList = new ArrayList<Subcategory>();
		subList.add(Subcategory.getSubcategories(subClass));
		if("1".equals(retailer))
			return this.getSqlMapClientTemplate().queryForList("selectBroadwayProductByType", subList, start, Constant.recordsPerPage);
		if("2".equals(retailer))
			return this.getSqlMapClientTemplate().queryForList("selectSuningProductByType", subList, start, Constant.recordsPerPage);
		if("3".equals(retailer))
			return this.getSqlMapClientTemplate().queryForList("selectFortressProductByType", subList, start, Constant.recordsPerPage);
		return new ArrayList<Product>();
	}

	public List<Product> selectProductByName(String name, int start) {
		return this.getSqlMapClientTemplate().queryForList("selectProductByName", 
				name, start, Constant.recordsPerPage);
	}

	public Integer selectProductByNameCount(String name) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("selectProductByNameCount", name);
	}


}
