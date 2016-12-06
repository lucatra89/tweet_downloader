package it.univaq.disim.mwt.wm.tweet.domain;

import java.util.List;

import twitter4j.Status;

public interface PersistenceService {
	public void store(List<Status> statusList) ;
}
