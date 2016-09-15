package com.mechanicshop.service;

import java.util.List;

import com.vaadin.data.Item;

public interface SearchService {
	String findFilter(String text);
	List<String> getClients();
	boolean validatePassword(String password);
	void updatePassword(String password);
	String getMedia1();
	String getMedia2();
	void updateMedia1(String text);
	void updateMedia2(String text);
	void insertCar(Object[] args, String status);
	void editCar(Object[] args, String tableName, Integer no);
	void insertIntoUnknownTable(Item item);
}
