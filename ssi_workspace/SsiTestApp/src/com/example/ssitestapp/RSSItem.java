package com.example.ssitestapp;

import java.io.Serializable;

// This handles RSS Item node in rss xml.

public class RSSItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String _title = null;
	private String _description = null;
	private String _date = null;
	private String _link = null;
	private String _image = null;
	

	void setTitle(String title) {
	_title = title;
	}
	
	void setLink(String link) {
		_link = link;
		}
	 
	void setDescription(String description) {
	_description = description;
	}
	 
	void setDate(String pubdate) {
	_date = pubdate;
	}
	 
	void setImage(String image) {
	_image = image;
	}
	
	 
	public String getTitle() {
	return _title;
	}
	 
	public String getDescription() {
	return _description;
	}
	 
	public String getDate() {
	return _date;
	}
	 
	public String getImage() {
	return _image;
	}
	
	public String getLink(){
	return _link;	
	}
}
