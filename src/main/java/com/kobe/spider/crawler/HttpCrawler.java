package com.kobe.spider.crawler;

import com.kobe.spider.Page;
import com.kobe.spider.utility.HttpUtility;

public class HttpCrawler implements Crawler{
	@Override
	public Page download(String url) {
		Page page = new Page();
		page.setUrl(url);
		page.setContent(HttpUtility.getPageContent(url));
		return page;
	}
	
}
