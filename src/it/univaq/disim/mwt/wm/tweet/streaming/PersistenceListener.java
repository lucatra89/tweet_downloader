package it.univaq.disim.mwt.wm.tweet.streaming;

import it.univaq.disim.mwt.wm.tweet.domain.PersistenceService;
import it.univaq.disim.mwt.wm.tweet.domain.impl.FileSystemService;

import java.util.ArrayList;
import java.util.List;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class PersistenceListener implements StatusListener{
	
	private final int max = 200;
	
	private  List<Status> statusList;
	
	private PersistenceService service;
	
	public PersistenceListener() {
		super();
		this.service = new FileSystemService();
		this.statusList = new ArrayList<Status>();
	}
	

	@Override
	public void onException(Exception arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScrubGeo(long arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStallWarning(StallWarning arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatus(Status status) {
		this.statusList.add(status);
		if(this.statusList.size()>=this.max){
			this.service.store(this.statusList);
			this.statusList.clear();
		}
		
	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
	}

}
