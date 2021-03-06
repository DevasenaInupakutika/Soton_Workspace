package com.example.ssitestapp;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.util.Log;

/**
 * Mimic the server side logic to return results with some delay
 */

public class BlogRemoteService {
	private static final String TAG = BlogRemoteService.class.getSimpleName();
	
	private int blogIdx = 0;
	private static final String RSSFEEDURL = "http://www.software.ac.uk/blog/rss-all";
	private int length = 0;
	
	DOMParser myParser;
	
	String xml; 
	
	RSSFeed feed; 
	
	public List<RSSItem> getNextBlogBatch(int batchSize){
		
		List<RSSItem> results = new ArrayList<RSSItem>();
		xml = myParser.getXmlFromUrl(RSSFEEDURL);
		feed = myParser.parseXml(xml,0);
		
		length = feed.getItemCount();
		
		
		try {
			for (int i = blogIdx; i < (blogIdx + batchSize < length ? blogIdx + batchSize : length); i++) {
		    	results.add(feed.getItem(i) );
				if (blogIdx != 0) {
					// Delay when retrieving each item
					Thread.sleep(400L);
				}
		    }
	    	// Keep track where the pointer is
			blogIdx = blogIdx + batchSize;
		} catch (InterruptedException e) {
			blogIdx = 0;
			results.clear();
			Log.i(TAG, "Blog service interrupted");
		}
		return results;
		
	}
	
	public void reset(){
		//Reset pointer
		blogIdx = 0;
	}

}
