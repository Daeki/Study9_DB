package com.iu.s1.location;

import java.util.ArrayList;

public class LocationController {
	private LocationDAO locationDAO;
	private LocationView locationView;
	
	public LocationController() {
		locationDAO = new LocationDAO();
		locationView = new LocationView();
	}
	
	
	public void start() {
		
		//DB에서 Location Table의 모든 정보 조회
		//ArrayList<LocationDTO> ar = locationDAO.getSearch("Vi");
		//locationView.view(ar);
		int count = locationDAO.getCount();
		locationView.view(count);
		
		
		//locationView.view();
		//LocationDTO locationDTO= locationDAO.getOne(1000);
		//locationView.view(locationDTO);
		
		
	}

}
