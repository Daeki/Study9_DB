package com.iu.s1.location;

public class LocationController {
	private LocationDAO locationDAO;
	private LocationView locationView;
	
	public LocationController() {
		locationDAO = new LocationDAO();
		locationView = new LocationView();
	}
	
	
	public void start() {
		
		//DB에서 Location Table의 모든 정보 조회
		//locationDAO.getList();
		//locationView.view();
		locationDAO.getOne(0);
		locationView.view();
		
		
	}

}
