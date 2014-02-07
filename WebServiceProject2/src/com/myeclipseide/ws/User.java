
package com.myeclipseide.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

  private int id;
  private String login;
  private String password;
  private String tweet;
  
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTweet() {
		return tweet;
	}
	
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

}
  
