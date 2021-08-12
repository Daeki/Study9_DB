package com.iu.s1.location;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.iu.s1.util.DBConnect;

public class LocationDAO {
	
	private DBConnect dbConnect;
	
	public LocationDAO() {
		dbConnect = new DBConnect();
	}
	
	
	//List
	public ArrayList<LocationDTO> getList() {
		
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<LocationDTO> ar = new ArrayList<LocationDTO>();
		try {
			con = dbConnect.getConnect();
			
			String sql ="SELECT * FROM LOCATIONS ORDER BY LOCATION_ID ASC";
			
			st = con.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				LocationDTO locationDTO = new LocationDTO();
				locationDTO.setLocation_id(rs.getInt("location_id"));
				locationDTO.setStreet_address(rs.getString("street_address"));
				locationDTO.setPostal_code(rs.getString("postal_code"));
				locationDTO.setCity(rs.getString("city"));
				locationDTO.setState_province(rs.getString("state_province"));
				locationDTO.setCountry_id(rs.getString("Country_id"));
				ar.add(locationDTO);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnect.disConnect(rs, st, con);
		}
		
		return ar;
		
	}
	
	
	//One
	public LocationDTO getOne(LocationDTO locationDTO) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		LocationDTO result=null;
		try {
			con = dbConnect.getConnect();
			System.out.println(con);
			
			String sql = "SELECT * FROM LOCATIONS WHERE LOCATION_ID=?";
			
			st = con.prepareStatement(sql);
			
			st.setInt(1, locationDTO.getLocation_id());
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				result = new LocationDTO();
				System.out.println(rs.getString("street_address"));
				result.setLocation_id(rs.getInt("location_id"));
				result.setStreet_address(rs.getString("street_address"));
				result.setPostal_code(rs.getString("postal_code"));
				result.setCity(rs.getString("city"));
				result.setState_province(rs.getString("state_province"));
				result.setCountry_id(rs.getString("Country_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbConnect.disConnect(rs, st, con);
		}
		
		return result;
		
	}
	
	
}
