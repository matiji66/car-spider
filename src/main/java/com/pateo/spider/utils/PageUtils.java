package com.pateo.spider.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageUtils {
	static Logger logger = LoggerFactory.getLogger(PageUtils.class);
	
	/**
	 * 获取页面内容
	 * @param url
	 * @return
	 */
	public static String getContent(String url){
		HttpClientBuilder builder = HttpClients.custom();
		CloseableHttpClient client = builder.build();
		String content = null;
		HttpGet request = new HttpGet(url);
		try {
			long start_time = System.currentTimeMillis();
			CloseableHttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity);
			logger.info("页面下载成功,url:{},消耗时间:{}",url,System.currentTimeMillis()-start_time);
		} catch (ClientProtocolException e) {
			logger.error("页面下载失败,url:{},ClientProtocolException异常",url);
		} catch (IOException e) {
			logger.error("页面下载失败,url:{},IOException异常",url);
		}
		return content;
	}

}
