package org.ape.data.core.storage;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MySqlBaseDao {	
    
    private static final Logger log = Logger.getLogger(MySqlBaseDao.class);
	
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
