package it.univaq.disim.mwt.wm.tweet.streaming;

import java.util.ArrayList;
import java.util.List;

import it.univaq.disim.mwt.wm.tweet.domain.SearchPlatformService;
import it.univaq.disim.mwt.wm.tweet.domain.impl.SolrService;
import it.univaq.disim.mwt.wm.tweet.domain.model.Tweet;
import it.univaq.disim.mwt.wm.tweet.util.Transformer;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class SolrListener implements StatusListener{
	
	SearchPlatformService service;
	
	private final int max = 100;


	private List<Tweet> tweets;	
	
	public SolrListener() {
		super();
		this.service = new SolrService();
		this.tweets = new ArrayList<Tweet>();
	}
	
	private void onStatusImpl(Status status) {
		Tweet tweet = Transformer.fromStatus(status);
		this.tweets.add(tweet);
		if(this.tweets.size()>this.max){
			this.service.save(this.tweets);
			this.tweets.clear();
		}
	}
	

	
	@Override
	public void onStatus(Status status) {
		if(status.getLang().equals("en")){
			this.onStatusImpl(status);
		}
	}

	@Override
	public void onException(Exception arg0) {}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {}

	@Override
	public void onScrubGeo(long arg0, long arg1) {}

	@Override
	public void onStallWarning(StallWarning arg0) {}

	@Override
	public void onTrackLimitationNotice(int arg0) {}

}
