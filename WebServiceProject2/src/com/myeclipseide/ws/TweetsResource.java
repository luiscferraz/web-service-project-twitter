package com.myeclipseide.ws;

import java.util.List;

import javax.ws.rs.GET;


public class TweetsResource {

	@GET
	public List<Tweet> getMentions() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}
	
}