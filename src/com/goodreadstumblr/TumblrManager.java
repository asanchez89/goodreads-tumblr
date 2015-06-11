package com.goodreadstumblr;

import java.io.IOException;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Photo;
import com.tumblr.jumblr.types.PhotoPost;
import com.tumblr.jumblr.types.User;

public class TumblrManager {

	private static final String OAUTH_TOKEN = "";
	private static final String OAUTH_TOKEN_SECRET = "";
	private static final String OAUTH_CONSUMER_KEY = "";
	private static final String OAUTH_CONSUMER_SECRET = "";

	public static void main(String[] args) {
		JumblrClient client = new JumblrClient(OAUTH_CONSUMER_KEY, OAUTH_CONSUMER_SECRET);
		client.setToken(OAUTH_TOKEN, OAUTH_TOKEN_SECRET);

		User user = client.user();
		System.out.println(user.getName());
		
		try {
			createPost(client, null);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void createPost(JumblrClient client, Review review) throws IllegalAccessException, InstantiationException, IOException{
		PhotoPost photoPost = client.newPost(client.user().getName(), PhotoPost.class);
		photoPost.addTag("tag1");
		photoPost.addTag("tag2");
		photoPost.setPhoto(new Photo("https://fbcdn-profile-a.akamaihd.net/hprofile-ak-xft1/v/t1.0-1/p160x160/11150186_423178647854626_9108097051957920646_n.jpg?oh=8a7769f8548d3833918037cbddfcd8a6&oe=55A07868&__gda__=1436326689_2fd47dc369348647f66533b758b89e34"));
		photoPost.setCaption(formatBookReview(review));
		photoPost.save();
	}
	
	public static String formatBookReview(Review review){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<b>"+review.getBook().getTitle()+"</b><br/>");
		stringBuilder.append(review.getTextReview()+"<br/>");
		stringBuilder.append(review.getLink()+"<br/>");
		return stringBuilder.toString();
	}

}
