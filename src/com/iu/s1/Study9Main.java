package com.iu.s1;

import java.sql.Connection;
import java.sql.SQLException;

import com.iu.s1.employee.Emp_DepartDTO;
import com.iu.s1.employee.EmployeeDAO;
import com.iu.s1.employee.EmployeeDTO;
import com.iu.s1.location.LocationController;
import com.iu.s1.location.LocationDAO;

public class Study9Main {

	public static void main(String[] args) {

		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setEmployee_id(101);
		Emp_DepartDTO dto = employeeDAO.getJoin(employeeDTO);
		
		System.out.println(dto.getLast_name());
		System.out.println(dto.getSalary());
		System.out.println(dto.getHire_date());
		System.out.println(dto.getDepartmentDTO().getDepartment_name());
		

	}

}
