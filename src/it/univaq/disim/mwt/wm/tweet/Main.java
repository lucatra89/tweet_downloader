package it.univaq.disim.mwt.wm.tweet;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.univaq.disim.mwt.wm.tweet.domain.model.Location;
import it.univaq.disim.mwt.wm.tweet.domain.model.Tweet;
import it.univaq.disim.mwt.wm.tweet.streaming.SolrListener;
import it.univaq.disim.mwt.wm.tweet.streaming.PersistenceListener;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class Main {
	
	
	private static void appStart() {
		 StatusListener storeListener = new PersistenceListener();
		 StatusListener solrListener = new SolrListener();
		 		 
		 TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		 twitterStream.addListener(storeListener);
		 twitterStream.addListener(solrListener);
		 
		 
		 twitterStream.sample();
		 
		 
	}
	
	public static void main(String[] args) throws TwitterException {
		appStart();
		
		
	}

}
