package com.kobe.spider.crawler;

import com.kobe.spider.Page;

public interface Crawler {
	public Page download(String url);
}
