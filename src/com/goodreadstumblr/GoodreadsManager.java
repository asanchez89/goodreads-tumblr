package com.goodreadstumblr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GoodreadsManager {

	private static final String KEY = "";
	private static final String ID = "24634481";
	private static final String READ_SHELF = "read";

	public void doProccess() {
		try {
			String g = " https://www.goodreads.com/review/list/" + ID + "?format=xml&key=" + KEY + "&v=2" + "&shelf="
					+ READ_SHELF + "&per_page=" + 200;
			URL u = new URL(g);
			URLConnection c = u.openConnection();
			parseXmlDom(u);
		} catch (Exception e) {
			// an error occurred, handle this
		}
	}

	public String getBufferedReader(URLConnection c) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
		String inputLine;
		StringBuffer b = new StringBuffer();
		while ((inputLine = in.readLine()) != null)
			b.append(inputLine + "\n");
		in.close();
		return b.toString();
	}

	public void parseXmlDom(URL u) throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(u.openStream());
		getReviewList(getElementsList(doc, "review"));
	}

	public ArrayList<Element> getElementsList(Document doc, String tagType) {
		NodeList nodes = doc.getElementsByTagName("review");
		ArrayList<Element> elementList = new ArrayList<>();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node nNode = nodes.item(i);

			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				elementList.add(eElement);
			}

		}
		return elementList;
	}
	
	public ArrayList<Review> getReviewList(ArrayList<Element> list){
		ArrayList<Review> reviewList = new ArrayList<Review>();
		for (Element element : list) {
			reviewList.add(new Review(element));
		}
		return reviewList;
	}
	
	public static String getTextFromTag(Element element, String tagType){
		StringBuffer sb = new StringBuffer();
		NodeList nodes = element.getElementsByTagName(tagType);
		for (int i = 0; i < nodes.getLength(); i++) {
			sb.append(nodes.item(i).getTextContent());

		}
		return sb.toString();
	}

}
