package it.univaq.disim.mwt.wm.tweet.domain.impl;


import it.univaq.disim.mwt.wm.tweet.domain.PersistenceService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import twitter4j.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileSystemService  implements PersistenceService{
	
	private Gson gson;
	
	
	public FileSystemService() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
	}
	
	
	
	private FileWriter createWriter() {
		FileWriter writer = null;
		long timestamp = new Date().getTime();
		try {
			writer =new FileWriter("./json/" + timestamp + ".json");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return writer;
	}
	
	@Override
	public void store(List<Status> statusList) {
		FileWriter writer = this.createWriter();
		String json = this.gson.toJson(statusList);
		try {
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
