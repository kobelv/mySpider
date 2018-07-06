package com.kobe.spider.cache;

import com.kobe.spider.constants.Constants;
import com.kobe.spider.utility.JedisUtility;
import com.kobe.spider.utility.Utility;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {

	@Override
	public String getNextAvailableUrl() {
		Jedis jedis = JedisUtility.getJedis();
		String randomDomain = jedis.srandmember(Constants.REDIS_KEY_DOMAINURLS);
		String url = jedis.lpop(randomDomain+Constants.REDIS_KEY_SUFFIX_HIGHER);
		if(url == null){
			url = jedis.lpop(randomDomain+Constants.REDIS_KEY_SUFFIX_LOWER);
		}
		JedisUtility.returnJedis(jedis);
		return url;
	}

	@Override
	public void saveUrlLevelInfo(String url, String level){
		Jedis jedis = JedisUtility.getJedis();
		jedis.lpush(Utility.getTopDomain(url)+level, url);
		JedisUtility.returnJedis(jedis);
	}

}
