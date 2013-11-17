package com.dao;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

/**
 * Base Dao</br>
 * 16/11/2013
 * 
 * @author Gang.Chen
 *
 */
public class BaseDao {

	private SqlMapClientTemplate sqlMapClientTemplate = null;
	
	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

}
