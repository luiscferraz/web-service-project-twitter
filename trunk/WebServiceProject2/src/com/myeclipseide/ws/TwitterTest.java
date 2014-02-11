package com.myeclipseide.ws;

import java.util.List;


import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;




public class TwitterTest  {
	public static void main(String[] args) {
		//printTimeline(getTwitter());
		getTweets(getTwitter());
	}
 
	/**
	 * Get Twitter object.
	 * 
	 * @return Twitter object.
	 */
	public static Twitter getTwitter() {
		Twitter twitter = new TwitterFactory().getInstance();
 
		// Set access
		twitter.setOAuthConsumer("t0i7oSm1ldndO2jqDSVA", "qOtvPyqXH82iltSeVOzxKQGFMK3SNPA17DfWbvkU");
		twitter.setOAuthAccessToken(new AccessToken("125470540-DEzN20RQe7RZd4kYe3Niq2KkVdZyc4wSGU80Z9gB", "YfJjkyTrgvIsSfHg5YRcY68xt3UyY9jgmk2npVKtumkBL"));
 
		return twitter;
	}
 
	/**
	 * Print out timeline.
	 * 
	 * @param twitter Twitter object
	 */
	public static void printTimeline(Twitter twitter) {
	    List<Status> statuses;
		try {
			statuses = twitter.getHomeTimeline();
		    System.out.println("Showing home timeline.");
		    for (Status status : statuses) {
		        System.out.println(status.getUser().getName() + ": " +
		                           status.getText());
		    }
		} catch (TwitterException e) {
			e.printStackTrace();
		}		
	}
	
	public static void getTweets(Twitter twitter){
		
		try{
			Query query = new Query("@saraiva");
		    QueryResult result = twitter.search(query);
		    for (Status status : result.getTweets()) {
		        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
		    }
		}catch (TwitterException e) {
			e.printStackTrace();
		}	
		
	}
	
}