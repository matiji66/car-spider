package com.pateo.spider.download;

import com.pateo.spider.domain.Page;

public interface Downloadable {
	
	Page download(String url);

}
