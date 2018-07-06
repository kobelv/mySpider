package com.kobe.spider;

import com.kobe.spider.cache.RedisCache;
import com.kobe.spider.constants.Constants;
import com.kobe.spider.crawler.HttpCrawler;
import com.kobe.spider.parser.JDParser;
import com.kobe.spider.repository.ConsoleRepository;

public class SpiderServer {

	public void start(){
		SpiderBootStrap bootStrap = new SpiderBootStrap();
		bootStrap
		.setCrawler(new HttpCrawler())
		.addParser(Constants.JD_DOMAIN_NAME, new JDParser())
		.addUrlLevel(Constants.JD_DOMAIN_NAME+Constants.REDIS_KEY_SUFFIX_HIGHER, "https://list.jd.com")
		.addUrlLevel(Constants.JD_DOMAIN_NAME+Constants.REDIS_KEY_SUFFIX_LOWER, "https://item.jd.com")
		.setDomainUrl(Constants.JD_DOMAIN_NAME)
		.setSeedUrls("https://list.jd.com/list.html?cat=9987,653,655&page=1")
		.setRepository(new ConsoleRepository())
		.setCache(new RedisCache())
		.start();
		
		
	}
	public static void main(String[] args) {
		new SpiderServer().start();
	}

}
