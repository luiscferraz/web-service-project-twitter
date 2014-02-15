package com.myeclipseide.ws;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


public class TwitterTest  {
	
public static void main(String[] args) throws TwitterException {
		
		System.out.println("==========TIMELINE==========");
		printTimeline(getTwitter());
		
		System.out.println("\n==========BUSCA GERAL=============");
		printMentions(getTweets(getTwitter(), "@submarino"));
		System.out.println("\n==========BUSCA NEGATIVA=============");
		printMentions(getNegativeMentions(getTwitter(), "@submarino"));
		System.out.println("\n==========BUSCA POSITIVA=============");		
		printMentions(getPositiveMentions(getTwitter(), "@submarino"));
		
		
	}
	
	
	public static void printTimeline(Twitter twitter) {
	    List<Status> statuses;
		try {
			statuses = twitter.getHomeTimeline();
		    for (Status status : statuses) {
		        System.out.println(status.getUser().getName() + ": " +
		                           status.getText());
		    }
		} catch (TwitterException e) {
			e.printStackTrace();
		}		
	}
	

	public static Twitter getTwitter() {
		Twitter twitter = new TwitterFactory().getInstance();
 
		// Set access
		twitter.setOAuthConsumer("t0i7oSm1ldndO2jqDSVA", "qOtvPyqXH82iltSeVOzxKQGFMK3SNPA17DfWbvkU");
		twitter.setOAuthAccessToken(new AccessToken("125470540-DEzN20RQe7RZd4kYe3Niq2KkVdZyc4wSGU80Z9gB", "YfJjkyTrgvIsSfHg5YRcY68xt3UyY9jgmk2npVKtumkBL"));
 
		return twitter;
	}
 
	public static ArrayList<String> getTweets(Twitter twitter, String stringBusca){
		
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


	public static ArrayList<String> getNegativeMentions(Twitter twitter, String stringBusca){
		//aqui seriam as palavras de busca negativa
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
	
	
	public static ArrayList<String> getPositiveMentions(Twitter twitter, String stringBusca){
		//aqui seriam as palavras de busca positiva
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
	
	//Este método pode ser utilizado para imprimir todos os tipos de menção
	public static void printMentions(ArrayList<String> mentions){
		for(String mention : mentions){
			System.out.println(mention);
		}
	}
	
	public static String getLocationUser(Status status){
		if (status.getUser().getLocation().isEmpty()){
			return "localização não fornecida no cadastro do usuário.";
		} else{
			return status.getUser().getLocation();
		}
	}
	
	
	
	

}
