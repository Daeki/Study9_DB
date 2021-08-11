package com.iu.s1.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.iu.s1.department.DepartmentDTO;
import com.iu.s1.util.DBConnect;

public class EmployeeDAO {
	
	private DBConnect dbConnect;
	
	public EmployeeDAO() {
		dbConnect = new DBConnect();
	}
	
	//getJoin
	public Emp_DepartDTO getJoin(EmployeeDTO employeeDTO) {
		Connection con=null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Emp_DepartDTO dto = null;
		try {
			con = dbConnect.getConnect();
			
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT E.Last_name, E.Salary, E.Hire_date, D.Department_name");
			sb.append(" FROM EMPLOYEES E INNER JOIN DEPARTMENTS D");
			sb.append(" ON (E.DEPARTMENT_ID = D.DEPARTMENT_ID)");
			sb.append(" WHERE E.EMPLOYEE_ID=?");
			
			st = con.prepareStatement(sb.toString());
			
			st.setInt(1, employeeDTO.getEmployee_id());
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				dto = new Emp_DepartDTO();
				dto.setDepartmentDTO(new DepartmentDTO());
				
				dto.setLast_name(rs.getString("last_name"));
				dto.setHire_date(rs.getDate("hire_date"));
				dto.setSalary(rs.getInt("salary"));
				dto.getDepartmentDTO().setDepartment_name(rs.getString("department_name"));	
				
				//dto.setEmployee_id(rs.getInt("employee_id"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	
	
	public void getAvg2() {
		//Map도 활용 가능
		HashMap<String, Object> obj = new HashMap<String, Object>();
		obj.put("id", 20);
		obj.put("avg", 1000.12);
		
	}

	public ArrayList<Double> getAvg() throws Exception {
		ArrayList<Double> ar = new ArrayList<Double>();
		ResultSet rs=null;
		
		String str= "SELECT avg(salary) from employees group by department_id";
		
		while(rs.next()) {
			
			double avg = rs.getDouble(1);
			ar.add(avg);
		}
		
		
		return ar;
	}
	
	
	public ArrayList<EmployeeDTO> getList() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<EmployeeDTO> ar = new ArrayList<EmployeeDTO>();
		try {
			//1. DB연결 Connection
			con = dbConnect.getConnect();
			
			//2.SQM문 작성
			String sql = "SELECT * FROM EMPLOYEES";
			
			//3.미리 보내기
			st = con.prepareStatement(sql);
			
			//4. ? 설정
			
			//5. 최종 전송 후 결과처리
			rs = st.executeQuery();
			
			while(rs.next()) {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				employeeDTO.setEmployee_id(rs.getInt("Employee_id"));
				employeeDTO.setFirst_name(rs.getString("First_name"));
				employeeDTO.setLast_name(rs.getString("Last_name"));
				employeeDTO.setEmail(rs.getString("Email"));
				employeeDTO.setPhone_number(rs.getString("Phone_number"));
				employeeDTO.setHire_date(rs.getDate("Hire_date"));
				employeeDTO.setJob_id(rs.getString("Job_id"));
				employeeDTO.setSalary(rs.getInt("Salary"));
				employeeDTO.setCommission_pct(rs.getDouble("Commission_pct"));
				employeeDTO.setManager_id(rs.getInt("Manager_id"));
				employeeDTO.setDepartment_id(rs.getInt("Department_id"));

			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//6. 자원 해제
			try {
				rs.close();
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return ar;
		
	}
	
	//2. 사원번호를 입력해서 한 사원 정보 출력
		public EmployeeDTO getOne(int num) {
			Connection con = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			EmployeeDTO employeeDTO=null;
			try {
				con = dbConnect.getConnect();
				
				String sql = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = ?";
				st = con.prepareStatement(sql);
				st.setInt(1, num);
				rs = st.executeQuery();
				
				if(rs.next()){
				employeeDTO = new EmployeeDTO();
				employeeDTO.setEmployee_id(rs.getInt("employee_id"));
				employeeDTO.setFirst_name(rs.getString("first_name"));
				employeeDTO.setLast_name(rs.getString("last_name"));
				employeeDTO.setEmail(rs.getString("email"));
				employeeDTO.setPhone_number(rs.getString("phone_number"));
				employeeDTO.setHire_date(rs.getDate("hire_date"));
				employeeDTO.setJob_id(rs.getString("job_id"));
				employeeDTO.setSalary(rs.getInt("salary"));
				employeeDTO.setCommission_pct(rs.getDouble("commission_pct"));
				employeeDTO.setManager_id(rs.getInt("manager_id"));
				employeeDTO.setDepartment_id(rs.getInt("department_id"));
				
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return employeeDTO;
		}// getOne() 종료
		
		
		
		
		
		//3. Last_name검색 정보 출력
		public ArrayList<EmployeeDTO> getLastName(String str) {
			Connection con = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			ArrayList<EmployeeDTO> ar = new ArrayList<EmployeeDTO>();
		
			try {
				con = dbConnect.getConnect();
				
				String sql = "SELECT * FROM EMPLOYEES WHERE LAST_NAME LIKE ? ";
				st = con.prepareStatement(sql);
				st.setString(1, "%"+str+"%");
				
				rs = st.executeQuery();
				
				while(rs.next()) {
					EmployeeDTO employeeDTO = new EmployeeDTO();
					employeeDTO.setEmployee_id(rs.getInt("employee_id"));
					employeeDTO.setFirst_name(rs.getString("first_name"));
					employeeDTO.setLast_name(rs.getString("last_name"));
					employeeDTO.setEmail(rs.getString("email"));
					employeeDTO.setPhone_number(rs.getString("phone_number"));
					employeeDTO.setHire_date(rs.getDate("hire_date"));
					employeeDTO.setJob_id(rs.getString("job_id"));
					employeeDTO.setSalary(rs.getInt("salary"));
					employeeDTO.setCommission_pct(rs.getDouble("commission_pct"));
					employeeDTO.setManager_id(rs.getInt("manager_id"));
					employeeDTO.setDepartment_id(rs.getInt("department_id"));
					
					ar.add(employeeDTO);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return ar;
		} //getLastName() 종료
		
		
		
		
		
		//4. First_name검색 정보 출력
		public ArrayList<EmployeeDTO> getFirstName(String str) {
				Connection con = null;
				PreparedStatement st = null;
				ResultSet rs = null;
				ArrayList<EmployeeDTO> ar = new ArrayList<EmployeeDTO>();
			
				try {
					con = dbConnect.getConnect();
					
					String sql = "SELECT * FROM EMPLOYEES WHERE FIRST_NAME LIKE ? ";
					st = con.prepareStatement(sql);
					st.setString(1, "%"+str+"%");
					
					rs = st.executeQuery();
					
					while(rs.next()) {
						EmployeeDTO employeeDTO = new EmployeeDTO();
						employeeDTO.setEmployee_id(rs.getInt("employee_id"));
						employeeDTO.setFirst_name(rs.getString("first_name"));
						employeeDTO.setLast_name(rs.getString("last_name"));
						employeeDTO.setEmail(rs.getString("email"));
						employeeDTO.setPhone_number(rs.getString("phone_number"));
						employeeDTO.setHire_date(rs.getDate("hire_date"));
						employeeDTO.setJob_id(rs.getString("job_id"));
						employeeDTO.setSalary(rs.getInt("salary"));
						employeeDTO.setCommission_pct(rs.getDouble("commission_pct"));
						employeeDTO.setManager_id(rs.getInt("manager_id"));
						employeeDTO.setDepartment_id(rs.getInt("department_id"));
						
						ar.add(employeeDTO);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						rs.close();
						st.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				return ar;
			} //getLastName() 종료
		
		
		
		
		
		
		//5. 전체 평균 급여의 정보를 출력
		public double getSalaryAvg() {
			Connection con = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			double avg = 0.0;
			
			try {
				con = dbConnect.getConnect();
				
				String sql = "SELECT AVG(SALARY) FROM EMPLOYEES";
				
				st = con.prepareStatement(sql);
				rs = st.executeQuery();
				
				rs.next();
				avg = rs.getDouble(1);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					st.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return avg;
			
		}//getSalaryAvg() 종료
		
		
		
		
		
		
		//6. 부서별 평균 급여의 정보 출력
//		public ArrayList<EmployeeDTO> getSalaryAvgDepartment() {
//			Connection con = null;
//			PreparedStatement st = null;
//			ResultSet rs = null;
//			ArrayList<EmployeeDTO> ar = new ArrayList<EmployeeDTO>();
//		
//				try {
//					con = dbConnect.getConnect();
//					
//					String sql = "SELECT AVG(SALARY), DEPARTMENT_ID FROM EMPLOYEES GROUP BY DEPARTMENT_ID";
//					
//					st = con.prepareStatement(sql);
//					rs = st.executeQuery();
//					
//					while(rs.next()) {
//						EmployeeDTO employeeDTO = new EmployeeDTO();
//						employeeDTO.setSalaryAvg(rs.getDouble("avg(salary)"));
//						employeeDTO.setDepartment_id(rs.getInt("department_id"));
//						
//						
//						ar.add(employeeDTO);
//					}
//					
//					
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}finally {
//					try {
//						rs.close();
//						st.close();
//						con.close();
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				return ar;
//		}//getSalaryAvgDepartment() 종료

}
