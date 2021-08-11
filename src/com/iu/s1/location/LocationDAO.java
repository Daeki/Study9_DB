package com.iu.s1.location;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.iu.s1.util.DBConnect;

public class LocationDAO {
	
	private DBConnect dbConnect;
	
	public LocationDAO() {
		dbConnect = new DBConnect();
	}
	
	public LocationDTO getLocation(int employee_id) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		LocationDTO locationDTO=null;
		try {
			con = dbConnect.getConnect();
			
			String sql="SELECT * FROM LOCATIONS WHERE LOCATION_ID="
					+ "(SELECT LOCATION_ID FROM DEPARTMENTS WHERE DEPARTMENT_ID="
					+ "(SELECT DEPARTMENT_ID FROM EMPLOYEES WHERE EMPLOYEE_ID=?))";
			
			st = con.prepareStatement(sql);
			
			st.setInt(1, employee_id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				locationDTO = new LocationDTO();
				locationDTO.setLocation_id(rs.getInt("location_id"));
				locationDTO.setStreet_address(rs.getString("street_address"));
				locationDTO.setPostal_code(rs.getString("postal_code"));
				locationDTO.setCity(rs.getString("city"));
				locationDTO.setState_province(rs.getString("state_province"));
				locationDTO.setCountry_id(rs.getString("country_id"));
			}
			
			
			
		} catch (Exception e) {
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
		
		return locationDTO;		
	}
	
	//getCount
	//location의 주소 갯수를 리턴하고 출력
	public int getCount() {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		int count=0;
		try {
			con = dbConnect.getConnect();
			String sql ="SELECT COUNT(LOCATION_ID) FROM LOCATIONS";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
			
			
		} catch (Exception e) {
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
		
		return count;
	}
	
	//getSearch
	//주소의 일부를 받아서 검색 찾은 것들을 출력
	public ArrayList<LocationDTO> getSearch(String search) {
		
		Connection con=null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<LocationDTO> ar = new ArrayList<LocationDTO>();
		//1. Connection
		try {
			con = dbConnect.getConnect();
			
		//2.SQl문 생성
			//String sql = "SELECT * FROM LOCATIONS WHERE STREET_ADDRESS LIKE '%'||?||'%'";
			String sql = "SELECT * FROM LOCATIONS WHERE STREET_ADDRESS LIKE ?"; //%Vi%
		//3. 미리 전송
			st = con.prepareStatement(sql);
		
		//4. ? 설정
			st.setString(1, "%"+search+"%");
		
		//5. 최종 전송 후 결과 처리
			rs = st.executeQuery();
			
			while(rs.next()) {
				LocationDTO locationDTO = new LocationDTO();
				locationDTO.setLocation_id(rs.getInt("location_id"));
				locationDTO.setStreet_address(rs.getString("street_address"));
				locationDTO.setPostal_code(rs.getString("postal_code"));
				locationDTO.setCity(rs.getString("city"));
				locationDTO.setState_province(rs.getString("state_province"));
				locationDTO.setCountry_id(rs.getString("country_id"));
				ar.add(locationDTO);
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
		
		return ar;
		
	}
	
	
	
	
	//Location Table에서 id와 일치하는 정보를 조회
	public LocationDTO getOne(int location_id) {
		
		//ADD-DTO Branch
		
		Connection con = null;
		PreparedStatement st=null;
		ResultSet rs = null;
		LocationDTO locationDTO=null;
		try {
			con = dbConnect.getConnect();
			
			String sql="SELECT * FROM LOCATIONS WHERE LOCATION_ID=?";
			
			st = con.prepareStatement(sql);
			
			st.setInt(1, location_id);
			
			rs = st.executeQuery();
			if(rs.next()) {
				locationDTO = new LocationDTO();
				locationDTO.setLocation_id(rs.getInt("location_id"));
				locationDTO.setStreet_address(rs.getString("street_address"));
				locationDTO.setPostal_code(rs.getString("postal_code"));
				locationDTO.setCity(rs.getString("city"));
				locationDTO.setState_province(rs.getString("state_province"));
				locationDTO.setCountry_id(rs.getString("country_id"));
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return locationDTO;
	}
	
	
	public ArrayList<LocationDTO> getList() {
		//LOCATIONS Table을 조회해서 출력
		
		//2. Driver를 메모리에 로딩
		Connection con = null;
		PreparedStatement st=null;
		ResultSet rs=null;
		ArrayList<LocationDTO> ar = new ArrayList<LocationDTO>();
		try {
			
		
		//3. DB Connection
			con = dbConnect.getConnect();
			System.out.println("DB 연결성공");
			
		//4. SQL문 생성
			String sql ="SELECT * FROM Locations";
		
		//5. 미리 전송
			st = con.prepareStatement(sql);
			
		//6. 최종 전송 후 결과 처리
			rs = st.executeQuery();
			
			while(rs.next()) {
				LocationDTO locationDTO = new LocationDTO();
				locationDTO.setLocation_id(rs.getInt("location_id"));
				locationDTO.setStreet_address(rs.getString("street_address"));
				locationDTO.setPostal_code(rs.getString("postal_code"));
				locationDTO.setCity(rs.getString("city"));
				locationDTO.setState_province(rs.getString("state_province"));
				locationDTO.setCountry_id(rs.getString("country_id"));
				ar.add(locationDTO);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return ar;
	}

}
