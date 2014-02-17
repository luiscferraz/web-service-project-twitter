package com.myeclipseide.ws;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable.PrintMode;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import javax.ws.rs.Consumes;

import com.sun.jersey.spi.resource.Singleton;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("tweets")
public class TweetsResource {

	@GET
	public Twitter getTwitter() {
		Twitter twitter = new TwitterFactory().getInstance();
		 
		// Set access
		twitter.setOAuthConsumer("t0i7oSm1ldndO2jqDSVA", "qOtvPyqXH82iltSeVOzxKQGFMK3SNPA17DfWbvkU");
		twitter.setOAuthAccessToken(new AccessToken("125470540-DEzN20RQe7RZd4kYe3Niq2KkVdZyc4wSGU80Z9gB", "YfJjkyTrgvIsSfHg5YRcY68xt3UyY9jgmk2npVKtumkBL"));
 
		return twitter;
	}

	@GET
	public ArrayList<String> getTweets(Twitter twitter,
			@QueryParam("stringBusca") String stringBusca) {
		ArrayList<String> tweets = new ArrayList<String>();
        
        try{
                Query query = new Query(stringBusca);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                //System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
                tweets.add("@" + status.getUser().getScreenName() + ":" + status.getText() + "\nLocalização do usuário fornecida no perfil: " + getLocationUser(status) +"\n");
            }
                
        }catch (TwitterException e) {
                e.printStackTrace();
        }       
        
        return tweets;
      
	}

	@GET
	public ArrayList<String> getPositiveMentions(Twitter twitter,
			@QueryParam("stringBusca") String stringBusca) {
		String[] palavrasPositivas = {"bom","maravilha","ótimo","adorei","gostei","adoro","amo","boa","ótima"};
		
		ArrayList<String> listaTweetsPositivos = new ArrayList<String>();
		
		ArrayList<String> listaTweets = getTweets( getTwitter(),stringBusca);
		
			for (String texto : listaTweets){
				for (String palavraPos : palavrasPositivas){
					if (texto.contains(palavraPos)){
						if (!listaTweetsPositivos.contains(texto)){
							listaTweetsPositivos.add(texto);
							//System.out.println(texto);
						 }
						
					}
				}
			}
			//System.out.println(listaTweetsPositivos);
			System.out.println("Menções Positivas:" + listaTweetsPositivos.size());
			return listaTweetsPositivos;
	}

	@GET
	public ArrayList<String> getNegativeMentions(Twitter twitter,
			@QueryParam("stringBusca") String stringBusca) {
		String[] palavrasNegativas = {"ruim", "raiva", "péssimo","horrível","não consigo","mal","não","odeio", "merda","bosta","lixo","reclamar","demora"};
		
		ArrayList<String> listaTweetsNegativos = new ArrayList<String>();		
		ArrayList<String> listaTweets = getTweets( getTwitter(),stringBusca);
		
		for (String texto : listaTweets){
			for (String palavraNeg : palavrasNegativas){
				if (texto.contains(palavraNeg)){
					if (!listaTweetsNegativos.contains(texto)){
						listaTweetsNegativos.add(texto);						
					 }
					
				}
			}
		}
		//System.out.println(listaTweetsNegativos);
		System.out.println("Menções Negativas:" + listaTweetsNegativos.size());
		
		return listaTweetsNegativos;
	}

	@GET
	public String getLocationUser(Status status) {
		if (status.getUser().getLocation().isEmpty()){
			return "localização não fornecida no cadastro do usuário.";
		} else{
			return status.getUser().getLocation();
		}
	}
}