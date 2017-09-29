package cn.crxy.spider_11;

import java.util.List;

import org.junit.Test;

import com.pateo.spider.Spider;
import com.pateo.spider.domain.Page;
import com.pateo.spider.download.HttpClientDownload;
import com.pateo.spider.process.JdProcess;
import com.pateo.spider.store.ConsoleStore;

public class SpiderTest {
	
	@Test
	public void test() throws Exception {
		Spider spider = new Spider();
		spider.setDownloadable(new HttpClientDownload());
		spider.setProcessable(new JdProcess());
		spider.setStoreable(new ConsoleStore());
		String url = "http://list.jd.com/list.html?cat=9987%2C653%2C655&go=0";
		Page page = spider.download(url);
		spider.process(page);
		List<String> urls = page.getUrls();
		for (String urlstr : urls) {
			
		}
		spider.store(page);
	}
	
}
