package com.kobe.spider;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private String id;
	private String content;
	private String url;
	private String source;              
    private String brand;                
    private String title;               
    private Float price;                
    private Integer commentCount;       
    private String imgUrl;             
    private String params;
    private List<String> urls = new ArrayList<>();
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}              

	public List<String> getUrls() {
	        return urls;
	 }

	public void setUrls(List<String> urls) {
			this.urls = urls;
	}
	
}
