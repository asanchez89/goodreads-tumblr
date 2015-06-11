package com.goodreadstumblr;

import org.w3c.dom.Element;

public class Book {

	private static final String TITLE_TAG_TYPE = "title";
	private static final String IMGURL_TAG_TYPE = "image_url";

	private static final String TAG_TYPE = "book";
	private String title;
	private String imgurl;

	public Book(Element element) {
		if (element.getTagName().equals(TAG_TYPE)) {
			this.title = GoodreadsManager.getTextFromTag(element, TITLE_TAG_TYPE);
			this.imgurl = GoodreadsManager.getTextFromTag(element, IMGURL_TAG_TYPE);
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

}
