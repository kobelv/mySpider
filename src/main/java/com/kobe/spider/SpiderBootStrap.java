package com.kobe.spider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kobe.spider.cache.Cache;
import com.kobe.spider.constants.Constants;
import com.kobe.spider.crawler.Crawler;
import com.kobe.spider.parser.Parser;
import com.kobe.spider.repository.Repository;
import com.kobe.spider.utility.JedisUtility;
import com.kobe.spider.utility.Utility;
import redis.clients.jedis.Jedis;


public class SpiderBootStrap {
	 private Logger logger = LoggerFactory.getLogger(SpiderBootStrap.class);
	   
	private Crawler crawler;
	private Map<String, Parser> parsers = new HashMap<>();
	private Repository repository;
	private List<String> seedUrls = new ArrayList<>();
	private Cache cache;
	private Map<String, String> urlLevels = new HashMap<>();
    
	public SpiderBootStrap setCrawler(Crawler crawler){
		this.crawler = crawler;
		return this;
	}
	
	public SpiderBootStrap setDomainUrl(String...dormainUrl){
		Jedis jedis = JedisUtility.getJedis();
        jedis.del(Constants.REDIS_KEY_DOMAINURLS);
        for(String tempStr : dormainUrl){
        	jedis.sadd(Constants.REDIS_KEY_DOMAINURLS, tempStr);
		}
        JedisUtility.returnJedis(jedis);
		return this;
	}
	
	public SpiderBootStrap addParser(String domain, Parser parser){
		parsers.put(domain, parser);
		return this;
	}
	
	public SpiderBootStrap addUrlLevel(String id, String url){
		urlLevels.put(id, url);
		return this;
	}
	
	public SpiderBootStrap setRepository(Repository repository){
		this.repository = repository;
		return this;
	}
	public SpiderBootStrap setCache(Cache cache){
		this.cache = cache;
		return this;
	}
	
	public SpiderBootStrap setSeedUrls(String... url){
		for(String tmpStr : url){
			this.seedUrls.add(tmpStr);
		}
		
		return this;
	}
	
	public void start(){
		registerZooKeeper();
		processInParallel();
	}

	private void processInParallel() {
		ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(Constants.THREAD_POOL_SIZE);
		for(int i=0; i<Constants.THREAD_POOL_SIZE; i++){
			executor.execute(new Runnable(){
				@Override
				public void run() {
					process();
				}

				private void process() {
					String url = cache.getNextAvailableUrl();
					if(null != url){
						Page page = crawler.download(url);
						String domain = Utility.getTopDomain(url);
						if(page.getContent() != null){
							parsers.get(domain).parse(page);
							for(String urlInPage : page.getUrls()){
								String higherUrl = urlLevels.get(domain+Constants.REDIS_KEY_SUFFIX_HIGHER);
                                String lowerUrl = urlLevels.get(domain+Constants.REDIS_KEY_SUFFIX_LOWER);
                                if (urlInPage.startsWith(higherUrl)) {
                                    cache.saveUrlLevelInfo(urlInPage, Constants.REDIS_KEY_SUFFIX_HIGHER);
                                } else if (urlInPage.startsWith(lowerUrl)) {
                                	cache.saveUrlLevelInfo(urlInPage, Constants.REDIS_KEY_SUFFIX_LOWER);
                                }
							}
							
							if(page.getId() != null){
								repository.save(page);
							}
						}
						
						Utility.sleep(1000);
					} else {
						logger.info("no url anymore, pls add soon.");
						Utility.sleep(1000);
					}
				}
			});
		}
	}

	private void registerZooKeeper() {
		// TODO Auto-generated method stub
		
	}
}
