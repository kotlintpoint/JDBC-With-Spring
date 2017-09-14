package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.bean.Employee;

@Component
public class JdbcDaoImpl extends JdbcDaoSupport{

	//private JdbcTemplate jdbcTemplate;
	
	/*@Autowired
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}
	
	//@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		//this.jdbcTemplate=new JdbcTemplate(dataSource);
	}*/
	
	/*public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}*/
		
	public int countEmployee()
	{
		String sql="select count(*) from employee";
		//jdbcTemplate.setDataSource(getDataSource());
		//return jdbcTemplate.queryForObject(sql, Integer.class);
		return getJdbcTemplate().queryForObject(sql, Integer.class);
		
	}
	
	public String getEmpbyId(int id)
	{
		String sql="select firstName from employee where id=?";
		//return jdbcTemplate.queryForObject(sql, new Object[]{id}, String.class);
		return getJdbcTemplate().queryForObject(sql, new Object[]{id}, String.class);
	}
	
	
	public Employee getEmpById(int id)
	{
		String sql="select * from employee where id=?";
		//return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmpMapper());
		return getJdbcTemplate().queryForObject(sql, new Object[]{id}, new EmpMapper());
	}
	
	public List<Employee> getEmps()
	{
		String sql="select * from employee";
		//return jdbcTemplate.query(sql, new EmpMapper());
		return getJdbcTemplate().query(sql, new EmpMapper());
	}
	
	public int insertEmp(Employee emp)
	{
		String sql="insert into employee (firstName, lastName, mobile) values (?,?,?)";
		//return jdbcTemplate.update(sql, new Object[]{emp.getFirstName(),emp.getLastName(),emp.getMobile()});
		return getJdbcTemplate().update(sql, new Object[]{emp.getFirstName(),emp.getLastName(),emp.getMobile()});
	}
	
	private static class EmpMapper implements RowMapper<Employee>
	{
		@Override
		public Employee mapRow(ResultSet resultSet, int position) throws SQLException {
			// TODO Auto-generated method stub
			Employee employee=new Employee();
			employee.setId(resultSet.getInt(1));
			employee.setFirstName(resultSet.getString(2));
			employee.setLastName(resultSet.getString(3));
			employee.setMobile(resultSet.getLong(4));
			return employee;
		}
		
	}
}
