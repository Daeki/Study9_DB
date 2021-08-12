package com.iu.s1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.iu.s1.location.LocationController;
import com.iu.s1.location.LocationDAO;
import com.iu.s1.location.LocationDTO;
import com.iu.s1.location.LocationView;

public class Study9Main {

	public static void main(String[] args) {
		LocationController lc = new LocationController();
		lc.start();
		
//		LocationDAO locationDAO = new LocationDAO();
//		LocationView locationView = new LocationView();
//		
//		ArrayList<LocationDTO> ar = locationDAO.getList();
//		
//		locationView.view(ar);
		
		
		
//		LocationDTO locationDTO = new LocationDTO();
//		locationDTO.setLocation_id(1000);
//		
//		locationDTO = locationDAO.getOne(locationDTO);
//		
//		if(locationDTO != null) {
//		
//			System.out.println(locationDTO.getLocation_id());
//			System.out.println(locationDTO.getStreet_address());
//			System.out.println(locationDTO.getCity());
//		}else {
//			System.out.println("조회 실패");
//		}

		

	}

}
