package com.kobe.spider.utility;

import java.io.IOException;
import java.util.Properties;

import com.kobe.spider.constants.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtility {
	static JedisPool jedisPool;
	static{
		Properties properties = new Properties();
		try {
			properties.load(JedisUtility.class.getClassLoader().getResourceAsStream("redis.properties") );
			JedisPoolConfig poolConf = new JedisPoolConfig();
			poolConf.setMaxIdle(Integer.valueOf(properties.getProperty(Constants.JEDIS_MAX_IDLE)));
			poolConf.setMinIdle(Integer.valueOf(properties.getProperty(Constants.JEDIS_MIN_IDLE)));
			poolConf.setMaxWaitMillis(Long.valueOf(properties.getProperty(Constants.JEDIS_MAX_WAIT_MILLIS)));
			poolConf.setMaxTotal(Integer.valueOf(properties.getProperty(Constants.JEDIS_MAX_TOTAL)));
			
			jedisPool = new JedisPool(poolConf, properties.getProperty(Constants.JEDIS_HOST),
					Integer.valueOf(properties.getProperty(Constants.JEDIS_PORT)), 1000);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Jedis getJedis() {
		return jedisPool.getResource();
	}

	public static void returnJedis(Jedis jedis) {
		jedis.close();
	}
	
}
