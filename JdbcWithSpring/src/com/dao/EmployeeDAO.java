package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.bean.Employee;

@Component
public class EmployeeDAO {
 
	@Autowired
	private DataSource dataSource;	
	
	private Connection connection;	
	public DataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public int insertEmployee(Employee employee)	{		
		int count=0;
		try {
			connection=dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql="insert into employee(firstName, lastname, mobile) values (?,?,?)";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setLong(3, employee.getMobile());
			count = ps.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}	
	public ArrayList<Employee> getEmployees()
	{
		try {
			connection=dataSource.getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Employee> employees=new ArrayList<>();
		String sql="select * from employee";
		try {
			PreparedStatement ps=connection.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.first())
			{
				do
				{
					Employee employee=new Employee();
					employee.setId(rs.getInt(1));
					employee.setFirstName(rs.getString(2));
					employee.setLastName(rs.getString(3));
					employee.setMobile(rs.getLong(4));
					employees.add(employee);
				}while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;		
	}
	
}
