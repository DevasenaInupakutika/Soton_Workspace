package com.example.ssitestapp;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.util.Log;

@SuppressWarnings("unused")
public class DOMParser {

	private RSSFeed _feed = new RSSFeed();
	private static final String TAG = "MyActivity";
	private final static int item_page = 20;
	
	//Constructor
	public DOMParser(){
		
	}
	
	/**
     * Getting XML from URL making HTTP request
     * @param url string
     * */
	
    public String getXmlFromUrl(String url) {
		// TODO Auto-generated method stub
		String xml = null;
		 
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
 
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            xml = EntityUtils.toString(httpEntity);
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return XML
        return xml;
	}
	
	public RSSFeed parseXml(String xml,int curr_page) {

		/*URL url = null;
		try {
			url = new URL(xml);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}*/
		
		// Create required instances
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
		
			DocumentBuilder db = dbf.newDocumentBuilder();

			// Parse the xml
			InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is); 
			doc.getDocumentElement().normalize();

			// Get all <item> tags i.e. Root node of each section we want to parse.
			NodeList nl = doc.getElementsByTagName("item"); //Parent node
			int length = nl.getLength();
		//curr_page * item_page
			//(curr_page + 1) * item_page
			for (int i = 0 ; i < length ; i++) {
				//Node currentNode = nl.item(i);
				Node currentNode = nl.item(i);
				RSSItem _item = new RSSItem();
	
				NodeList nchild = currentNode.getChildNodes();
			    int clength = nchild.getLength();
				
				// Get the required elements from each Item
				for (int j = 1; j < clength; j = j + 2) {

					Node thisNode = nchild.item(j);
					String nodeName = thisNode.getNodeName();
					
					//Log.v(TAG, "NodeName is:"+ nodeName);
					String theString = thisNode.getFirstChild().getNodeValue();
					
					//Log.v(TAG, "theString = "+ theString);
					
					
					 if (!"null".equals(theString)) {
						
						if ("title".equals(nodeName)) {
							// Node name is equals to 'title' so set the Node
							// value to the Title in the RSSItem.
							_item.setTitle(theString);
						}

					
				       else if ("link".equals(nodeName)) {
								// Node name is equal to 'link' so set the Node
								// value to the Link in the RSSItem.
							_item.setLink(theString);
						}

						else if ("description".equals(nodeName)) {
							_item.setDescription(theString);

							// Parse the html description to get the image url
							String html = theString;
							org.jsoup.nodes.Document docHtml = Jsoup
									.parse(html);
							
							Elements imgEle = docHtml.select("img");
							_item.setImage(imgEle.attr("src"));
						}
						

						else if ("pubDate".equals(nodeName)) {

							// We replace the plus and zero's in the date with
							// empty string
							String formatedDate = theString.replace(" +0000",
									"");
							_item.setDate(formatedDate);
						}

					}
				}

				// add item to the list
				_feed.addItem(_item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Return the final feed once all the Items are added to the RSSFeed
		// Object(_feed).
		return _feed;
	}

}
