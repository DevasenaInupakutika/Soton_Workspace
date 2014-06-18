package com.example.ssitestapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class BlogParser {
	
	private List<RSSBlog> arrayListParse;

	public List<RSSBlog> getData(String _url) {
		try {

			arrayListParse = new ArrayList<RSSBlog>();
			URL url = new URL(_url);
			URLConnection con = url.openConnection();

			System.out.println("Connection is : " + con);

			System.out.println("Connection InputStream is : "
					+ con.getInputStream());

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			System.out.println("Reader us now : " + reader);

			String inputLine;
			String fullStr = "";
			while ((inputLine = reader.readLine()) != null)
				fullStr = fullStr.concat(inputLine + "\n");

			InputStream istream = url.openStream();

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			System.out.println("Builder : " + builder);

			Document doc = builder.parse(istream);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("item");

			System.out.println("nlist " + nList);

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					RSSBlog objBean = new RSSBlog();

					objBean.setTitle(getTagValue("title", eElement));
					objBean.setDescription(getTagValue("description", eElement));
					objBean.setPubDate(getTagValue("pubDate", eElement));
					objBean.setLink(getTagValue("link", eElement));
					objBean.setImage(getTagValue("image",eElement));
					arrayListParse.add(objBean);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayListParse;
	}

	private String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();

	}

}
