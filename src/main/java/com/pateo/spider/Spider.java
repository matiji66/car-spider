package com.pateo.spider;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pateo.spider.domain.Page;
import com.pateo.spider.download.Downloadable;
import com.pateo.spider.download.HttpClientDownload;
import com.pateo.spider.process.JdProcess;
import com.pateo.spider.process.Processable;
import com.pateo.spider.repository.QueueRepository;
import com.pateo.spider.repository.RedisRepository;
import com.pateo.spider.repository.Repository;
import com.pateo.spider.store.ConsoleStore;
import com.pateo.spider.store.Storeable;
import com.pateo.spider.utils.Config;
import com.pateo.spider.utils.SleepUtils;

public class Spider {
	private Downloadable downloadable = new HttpClientDownload();
	private Processable processable;
	private Storeable storeable = new ConsoleStore();
	private Repository repository = new QueueRepository();
	Logger logger = LoggerFactory.getLogger(Spider.class);
	ExecutorService threadPool = Executors.newFixedThreadPool(Config.nThread);
	
	
	
	public void start() {
		check();
		logger.info("开始启动爬虫...");
		while(true){
			final String url = repository.poll();
			if(StringUtils.isNotBlank(url)){
				threadPool.execute(new Runnable() {
					public void run() {
						Page page = Spider.this.download(url);
						Spider.this.process(page);
						List<String> urls = page.getUrls();
						for (String nextUrl : urls) {
							if (nextUrl.startsWith("http://item.jd.com/")) {
								repository.add(nextUrl);
							} else {
								repository.addHight(nextUrl);
							}
						}
						if (url.startsWith("http://item.jd.com/")) {
							Spider.this.store(page);
						} else {
							System.out.println("解析列表页面：" + url);
						}
						SleepUtils.sleep(Config.million_1);
					}
				});
			}else{
				logger.info("暂时没有url，歇一会。");
				SleepUtils.sleep(Config.million_5);
			}
		}
	}

	private void check() {
		if(processable==null){
			String message = "没有设置默认解析类";
			logger.error(message);
			throw new RuntimeException(message);
		}
		logger.info("====================================================");
		logger.info("downloadable的实现类是:{}",downloadable.getClass().getName());
		logger.info("processable的实现类是:{}",processable.getClass().getName());
		logger.info("storeable的实现类是:{}",storeable.getClass().getName());
		logger.info("repository的实现类是:{}",repository.getClass().getName());
		logger.info("====================================================");
	}

	/**
	 * 下载功能
	 */
	public Page download(String url) {
		Page page = this.downloadable.download(url);
		return page;
	}
	
	/**
	 * 解析功能
	 * @param page 
	 */
	public void process(Page page) {
		this.processable.process(page);
	}
	
	/**
	 * 存储功能
	 * @param page 
	 */
	public void store(Page page) {
		this.storeable.store(page);
	}

	public void setDownloadable(Downloadable downloadable) {
		this.downloadable = downloadable;
	}

	public void setProcessable(Processable processable) {
		this.processable = processable;
	}

	public void setStoreable(Storeable storeable) {
		this.storeable = storeable;
	}
	
	public void setRepository(Repository repository) {
		this.repository = repository;
	}

	public void setSeedUrl(String url){
		this.repository.add(url);
	}
	
	
	public static void main(String[] args) {
		Spider spider = new Spider();
		String url = "http://list.jd.com/list.html?cat=9987%2C653%2C655&go=0";
		spider.setProcessable(new JdProcess());
		spider.setSeedUrl(url);
		spider.start();
	}
	

}
