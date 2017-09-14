package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.bean.Employee;

@Component
public class EmployeeJdbcDaoTemplate {

	private JdbcTemplate jdbcTemplate = new JdbcTemplate();	
	
	
	/*public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}*/
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate=new JdbcTemplate(dataSource);
	}

	public int countEmployee() {
		String sql = "select count(*) from employee";
		//jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	
	

	public String getEmployeeName(int empId) {
		String sql = "select firstname from employee where id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { empId }, String.class);
	}

	public Employee getEmployeeById(int empId) {
		String sql = "select * from employee where id=?";
		//jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate.queryForObject(sql, new Object[] { empId }, new EmployeeMapper());
	}

	public int insertEmployee(Employee employee)
	{
		String sql="insert into Employee(firstName, lastName, mobile) values (?,?,?)";
		return jdbcTemplate.update(sql, new Object[]{employee.getFirstName(),employee.getLastName(),employee.getMobile()});		
	}

	public void createTriangleTable(){
		String sql="create table triangle(id integer, name varchar(50))";
		jdbcTemplate.execute(sql);
	}

	
	/*private SimpleJdbcTemplate namedParameterJdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
	}
	public int insertCircle(Employee employee){
		String sql="insert into Employee(firstName, lastName, mobile) values (:firstName,:lastName,:mobile)";
		SqlParameterSource namedParameters=new MapSqlParameterSource("id",employee.getId())
	              					.addValue("firstName",employee.getFirstName())
	              					.addValue("lastName", employee.getLastName())
	              					.addValue("mobile", employee.getMobile());
		return namedParameterJdbcTemplate.update(sql,namedParameters);
	}*/

	
	public List<Employee> getAllEmployees()
	{
		String sql="select * from Employee";
		return jdbcTemplate.query(sql, new EmployeeMapper());
	}


	private static final class EmployeeMapper implements RowMapper<Employee> {

		@Override
		public Employee mapRow(ResultSet rs, int position) throws SQLException {
			// TODO Auto-generated method stub
			Employee employee=new Employee();
			employee.setId(rs.getInt("id"));
			employee.setFirstName(rs.getString("firstName"));
			employee.setLastName(rs.getString("lastName"));
			employee.setMobile(rs.getLong("mobile"));
			return employee;
		}		
	}

}
