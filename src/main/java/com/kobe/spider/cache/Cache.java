package com.kobe.spider.cache;

public interface Cache {
	public String getNextAvailableUrl();
	public void saveUrlLevelInfo(String url, String level);
}
