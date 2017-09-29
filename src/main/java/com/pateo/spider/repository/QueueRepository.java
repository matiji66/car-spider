package com.pateo.spider.repository;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.apache.commons.lang.StringUtils;

public class QueueRepository implements Repository{
	private Queue<String> lowqueue = new ConcurrentLinkedDeque<String>();
	private Queue<String> hightqueue = new ConcurrentLinkedDeque<String>();
	
	@Override
	public String poll() {
		String url = hightqueue.poll();
		if(StringUtils.isEmpty(url)){
			url = lowqueue.poll();
		}
		return url;
	}

	@Override
	public void add(String urlstr) {
		this.lowqueue.add(urlstr);
	}

	@Override
	public void addHight(String urlstr) {
		this.hightqueue.add(urlstr);
	}
	
	
	
	

}
