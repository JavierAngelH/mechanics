package com.mechanicshop.service;

import java.util.List;

public interface SearchService {
	String findFilter(String text);
	List<String> getClients();
	boolean validatePassword(String password);
	void updatePassword(String password);
	
	
}
