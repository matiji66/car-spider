package com.pateo.spider.repository;

public interface Repository {

	String poll();

	void add(String urlstr);
	
	
	void addHight(String urlstr);
	

}
