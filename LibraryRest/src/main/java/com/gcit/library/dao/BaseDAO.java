package com.gcit.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDAO {

	@Autowired
	public JdbcTemplate template;
	
	private Integer pageNo;
	
	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	protected String addLimit(String query) {
		Integer index = 0;
		if (getPageNo() != null) {
			index = (getPageNo() - 1) * 10;
		}
		if (getPageNo() != null)
			query = query + " LIMIT " + index + ", " + 10;
		return query;
	}
}
