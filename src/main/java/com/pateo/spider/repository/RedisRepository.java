package com.pateo.spider.repository;

import org.apache.commons.lang.StringUtils;

import com.pateo.spider.utils.RedisUtils;

public class RedisRepository implements Repository {
	RedisUtils redisUtils = new RedisUtils();

	@Override
	public String poll() {
		String url = redisUtils.poll(RedisUtils.heightkey);
		if(StringUtils.isEmpty(url)){
			url = redisUtils.poll(RedisUtils.lowkey);
		}
		return url;
	}

	@Override
	public void add(String urlstr) {
		redisUtils.add(RedisUtils.lowkey, urlstr);
	}

	@Override
	public void addHight(String urlstr) {
		redisUtils.add(RedisUtils.heightkey, urlstr);
	}

}
