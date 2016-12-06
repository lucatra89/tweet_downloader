package it.univaq.disim.mwt.wm.tweet.util;

import twitter4j.Status;
import it.univaq.disim.mwt.wm.tweet.domain.model.Location;
import it.univaq.disim.mwt.wm.tweet.domain.model.Tweet;

public class Transformer {
	public static Tweet fromStatus(Status status) {
		Tweet tweet = new Tweet();
		
		Location location = new Location();
		location.setLatitude(status.getGeoLocation().getLatitude());
		location.setLongitude(status.getGeoLocation().getLongitude());
		
		tweet.setLocation(location);
		tweet.setUser(status.getUser().getName());
		tweet.setLang(status.getLang());
		tweet.setRetweetCount(status.getRetweetCount());
		tweet.setFavoriteCount(status.getFavoriteCount());
		tweet.setCreatedAt(status.getCreatedAt());
		tweet.setText(status.getText());
		
		return tweet;

	}
	
	
}
