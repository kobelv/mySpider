package com.kobe.spider.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
	public static void sleep(long millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String getTopDomain(String url) {
		 if(url != null) {
	            try {
	                String host = new URL(url).getHost().toLowerCase();
	                Pattern pattern = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)");
	                Matcher matcher = pattern.matcher(host);
	                while (matcher.find()) {
	                    return matcher.group();
	                }
	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            }
	        }
		return null;
	}
}
