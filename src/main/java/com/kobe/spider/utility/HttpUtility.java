package com.kobe.spider.utility;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kobe.spider.constants.Constants;

import redis.clients.jedis.Jedis;

public class HttpUtility {
	private static Logger logger = LoggerFactory.getLogger(HttpUtility.class);
    
	public static String getPageContent(String url) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0");

		RequestConfig config = RequestConfig.custom()
				.setConnectionRequestTimeout(1000)
				.setConnectTimeout(4000)
				.setSocketTimeout(4000)
				.build();
		httpGet.setConfig(config);
		
		CloseableHttpClient httpClient = null;
		//if has proxy 10.12.12.12:8080, httpClient = HttpClients.custom().setProxy(proxy).build();  
		httpClient = HttpClients.custom().build();
		
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("download failed:{}", url);
			if (url.contains("list.jd.com") ) { 
                String domain = Utility.getTopDomain(url); 
                retryUrl(url, domain + Constants.REDIS_KEY_SUFFIX_HIGHER); 
            }
			e.printStackTrace();
		}
		
		return null;
	}
	
	 public static void retryUrl(String url, String key) {
	        Jedis jedis = JedisUtility.getJedis();
	        jedis.lpush(key, url);
	 }

}
