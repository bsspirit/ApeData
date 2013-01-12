package org.ape.data.core.storage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class MySqlBaseDao {	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	    
	public List<Map<String, Object>> getList(String sql) {
	    return jdbcTemplate.queryForList(sql);
	}
	
	public Map<String, Object> getObject(String sql) {
	    return jdbcTemplate.queryForMap(sql);
	}
	
	public void insert(String sql) {
		jdbcTemplate.execute(sql);
	}
	
	public void update(String sql) {
		jdbcTemplate.execute(sql);
	}
	
	public void delete(String sql) {
		jdbcTemplate.execute(sql);
	}


}
