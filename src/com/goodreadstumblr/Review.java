package com.goodreadstumblr;

import org.w3c.dom.Element;

public class Review {

	private static final String BODY_TAG_TYPE = "body";
	private static final String LINK_TAG_TYPE = "link";
	private static final String TAG_TYPE = "review";
	private Book book;
	private String textReview;
	private String link;

	public Review(Element element) {
		if (element.getTagName().equals(TAG_TYPE)) {
			this.setBook(new Book(element));
			this.textReview = GoodreadsManager.getTextFromTag(element, BODY_TAG_TYPE);
			this.setLink(GoodreadsManager.getTextFromTag(element, LINK_TAG_TYPE));
		}
	}

	String getTextReview() {
		return textReview;
	}

	void setTextReview(String textReview) {
		this.textReview = textReview;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
