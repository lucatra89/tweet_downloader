package it.univaq.disim.mwt.wm.tweet.domain.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.univaq.disim.mwt.wm.tweet.domain.SearchPlatformService;
import it.univaq.disim.mwt.wm.tweet.domain.model.Tweet;
import it.univaq.disim.mwt.wm.tweet.serializer.TweetSerializer;

public class SolrService implements SearchPlatformService {
	
	private Gson gson;
	private String uri;
	private CloseableHttpClient client;

	public SolrService() {
		this.gson = new GsonBuilder().registerTypeAdapter(Tweet.class, new TweetSerializer()).setPrettyPrinting().create();
        this.uri = "http://localhost:8983/solr/twitter/update/json";
        this.client = HttpClients.createDefault();
	}
	
	
	private HttpPost createHttpJsonPost(String json){
        HttpPost post = new HttpPost(this.uri);
        post.setHeader("Accept", "application/json");
        
        HttpEntity payload = new StringEntity(json, ContentType.APPLICATION_JSON);
        post.setEntity(payload);
        
        return post;
	}
	
	private void sendImpl(String json) {
        try {

            HttpPost post = this.createHttpJsonPost(json);
            
            CloseableHttpResponse response = this.client.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    entity.writeTo(System.out);
                    System.out.println();
                }
            } finally {
                response.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(SolrService.class.getName()).log(Level.SEVERE, null, ex);
        } 
	}

	@Override
	public void save(List<Tweet> tweets) {
		
		String json = this.gson.toJson(tweets);
		
		this.sendImpl(json);
	}

}
