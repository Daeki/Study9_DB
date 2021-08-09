package com.iu.s1;

import java.sql.Connection;
import java.sql.SQLException;

import com.iu.s1.location.LocationDAO;

public class Study9Main {

	public static void main(String[] args) {
		LocationDAO locationDAO = new LocationDAO();
		locationDAO.getList();

		

	}

}
