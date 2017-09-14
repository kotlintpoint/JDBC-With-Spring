import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bean.Employee;
import com.dao.EmployeeDAO;
import com.dao.EmployeeJdbcDaoTemplate;
import com.dao.JdbcDaoImpl;

public class EmployeeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		EmployeeJdbcDaoTemplate employeeJdbcDaoTemplate=context.getBean(EmployeeJdbcDaoTemplate.class);
		Employee employee=employeeJdbcDaoTemplate.getEmployeeById(1);
		System.out.println(employee);
		
		
		int count=employeeJdbcDaoTemplate.countEmployee();
		System.out.println("Count = "+count);		
		
		/*EmployeeDAO dao=context.getBean(EmployeeDAO.class);
		
		Employee employee=new Employee();
		employee.setFirstName("test");
		employee.setLastName("testing");
		employee.setMobile(1231231);
		
		
		int count=dao.insertEmployee(employee);
		if(count>0)
			System.out.println("Employee inserted Success");
		else
			System.out.println("Insertion fail");*/
		
		//JdbcDaoImpl jdbcImpl=context.getBean("jdbcDaoImpl",JdbcDaoImpl.class);
		//ArrayList<Employee> emps=dao.getEmployees();
		//System.out.println(emps);
		
		/*System.out.println("Count = "+jdbcImpl.countEmployee());
		System.out.println("Name = "+jdbcImpl.getEmpbyId(1));
		System.out.println("Emp = "+jdbcImpl.getEmpById(1));
		System.out.println(jdbcImpl.getEmps());*/
	}

}
