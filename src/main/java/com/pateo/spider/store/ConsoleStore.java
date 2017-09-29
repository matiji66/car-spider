package com.pateo.spider.store;

import com.pateo.spider.domain.Page;

public class ConsoleStore implements Storeable {

	@Override
	public void store(Page page) {
		System.out.println(page.getUrl()+"---"+page.getValues().get("price"));
	}

}
