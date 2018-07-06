package com.kobe.spider.repository;

import com.kobe.spider.Page;

public class ConsoleRepository implements Repository{

	@Override
	public void save(Page page) {
		System.out.println(page.getId() + "--" + page.getTitle() + "--");
	}
	
}
