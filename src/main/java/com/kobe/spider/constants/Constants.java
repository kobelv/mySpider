package com.kobe.spider.constants;

public class Constants {
	public static int THREAD_POOL_SIZE = 2;
	public static String REDIS_KEY_SEEDURLS = "spider.seed.urls";
    public static String REDIS_KEY_DOMAINURLS = "spider.domain.urls";
    public static String REDIS_KEY_SUFFIX_HIGHER = ".higher";
    public static String REDIS_KEY_SUFFIX_LOWER = ".lower";
    public static String JD_DOMAIN_NAME="jd.com";
    
    public static String JEDIS_HOST = "jedis.host";
    public static String JEDIS_PORT = "jedis.port";
    public static String JEDIS_PASSWORD = "jedis.password";
    public static String JEDIS_MAX_TOTAL = "jedis.max.total";
    public static String JEDIS_MAX_IDLE = "jedis.max.idle";
    public static String JEDIS_MIN_IDLE = "jedis.min.idle";
    public static String JEDIS_MAX_WAIT_MILLIS = "jedis.max.wait.millis";

}
