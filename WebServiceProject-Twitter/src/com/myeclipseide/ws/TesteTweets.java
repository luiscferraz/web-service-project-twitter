package com.myeclipseide.ws;

import com.myeclipseide.ws.TweetsResource;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TesteTweets {

	public static void main(String[] args) throws TwitterException {
		
		TweetsResource tweetsResource = new TweetsResource();
		Twitter twitter = tweetsResource.getTwitter();
		
        System.out.println("\n==========BUSCA GERAL=============");
        System.out.println(tweetsResource.getTweets(twitter, "@submarino"));
        System.out.println("\n==========BUSCA NEGATIVA=============");
        System.out.println(tweetsResource.getNegativeMentions(twitter, "@submarino"));
        System.out.println("\n==========BUSCA POSITIVA=============");          
        System.out.println(tweetsResource.getPositiveMentions(twitter, "@submarino"));
        System.out.println("\n==========LOCALIDADES=============");          
        //tweetsResource.getLocationUser(Status status);

	}

}
