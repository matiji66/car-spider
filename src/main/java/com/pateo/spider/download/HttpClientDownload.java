package com.pateo.spider.download;

import com.pateo.spider.domain.Page;
import com.pateo.spider.utils.PageUtils;

public class HttpClientDownload implements Downloadable {

	@Override
	public Page download(String url) {
		Page page = new Page();
		String content = PageUtils.getContent(url);
		page.setContent(content);
		page.setUrl(url);
		return page;
	}

}
