package com.example.ssitestapp;

public class RSSBlog {
	private String title = null;
	private String description = null;
	private String pubDate = null;
	private String link = null;
	private String image = null;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}
	
	public String getImage() {
		return image;
		}
	
	public void setImage(String image1) {
		image = image1;
		}
		

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
