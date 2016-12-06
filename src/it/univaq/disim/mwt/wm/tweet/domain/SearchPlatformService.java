package it.univaq.disim.mwt.wm.tweet.domain;

import java.util.List;

import it.univaq.disim.mwt.wm.tweet.domain.model.Tweet;


public interface SearchPlatformService {
	void save(List<Tweet> tweets);
}
