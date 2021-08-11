package com.iu.s1;

import java.sql.Connection;
import java.sql.SQLException;

import com.iu.s1.department.Depart_EmpDTO;
import com.iu.s1.department.DepartmentDAO;
import com.iu.s1.department.DepartmentDTO;
import com.iu.s1.employee.Emp_DepartDTO;
import com.iu.s1.employee.EmployeeDAO;
import com.iu.s1.employee.EmployeeDTO;
import com.iu.s1.location.LocationController;
import com.iu.s1.location.LocationDAO;

public class Study9Main {

	public static void main(String[] args) {
		
		DepartmentDAO dao = new DepartmentDAO();
		DepartmentDTO departmentDTO = new DepartmentDTO();
		departmentDTO.setDepartment_id(80);
		
		Depart_EmpDTO dto = dao.getJoin(departmentDTO);
		
		System.out.println(dto.getDepartment_name());
		
		for(int i=0;i<dto.getAr().size();i++) {
			System.out.println("=============================");
			System.out.println(dto.getAr().get(i).getLast_name());
			System.out.println(dto.getAr().get(i).getSalary());
			System.out.println(dto.getAr().get(i).getHire_date());
			System.out.println("=============================");
		}
		

//		EmployeeDAO employeeDAO = new EmployeeDAO();
//		EmployeeDTO employeeDTO = new EmployeeDTO();
//		employeeDTO.setEmployee_id(101);
//		Emp_DepartDTO dto = employeeDAO.getJoin(employeeDTO);
//		
//		System.out.println(dto.getLast_name());
//		System.out.println(dto.getSalary());
//		System.out.println(dto.getHire_date());
//		System.out.println(dto.getDepartmentDTO().getDepartment_name());
		

	}

}
