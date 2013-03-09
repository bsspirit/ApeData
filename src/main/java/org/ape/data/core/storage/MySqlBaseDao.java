package org.ape.data.core.storage;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.ape.data.core.io.model.Cell;
import org.ape.data.core.io.model.Row;
import org.ape.data.core.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
	public void batchUpdateLinkset(String sql ,final List<Row> list) {
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            public int getBatchSize() {
                return list.size();
            }
            public void setValues(PreparedStatement ps, int i)throws SQLException {
            	Row row = list.get(i);
            	try {
					mapPreparedStatement(ps,row);
				} catch (ParseException e) {
					e.printStackTrace();
				}
            }
        });
    }


	private void mapPreparedStatement(PreparedStatement ps,Row row) throws SQLException, ParseException{
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Cell c:row.getCells()){
			if(c.getColumn().getColumnType().contains(Constants.MYSQL_SMALLINT)||
					c.getColumn().getColumnType().contains(Constants.MYSQL_INT)){
				ps.setInt(c.getColumn().getId(), Integer.valueOf(c.getContent().toString()));
			}else if(c.getColumn().getColumnType().contains(Constants.MYSQL_BIGINT)||
					c.getColumn().getColumnType().contains(Constants.MYSQL_DECIMAL)){
				ps.setLong(c.getColumn().getId(), Long.valueOf(c.getContent().toString()));
			}else if(c.getColumn().getColumnType().contains(Constants.MYSQL_FLOAT)){
				ps.setFloat(c.getColumn().getId(), Float.valueOf(c.getContent().toString()));
			}else if(c.getColumn().getColumnType().contains(Constants.MYSQL_DOUBLE)){
				ps.setDouble(c.getColumn().getId(), Double.valueOf(c.getContent().toString()));
			}else if(c.getColumn().getColumnType().contains(Constants.MYSQL_TIMESTAMP)){
				ps.setTimestamp(c.getColumn().getId(),new Timestamp(dateformat.parse(c.getContent().toString()).getTime()));
			}else{
				ps.setString(c.getColumn().getId(),c.getContent().toString());
			}
		}		
	}
}
