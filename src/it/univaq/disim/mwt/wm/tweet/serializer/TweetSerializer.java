package it.univaq.disim.mwt.wm.tweet.serializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import it.univaq.disim.mwt.wm.tweet.domain.model.Tweet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class TweetSerializer implements JsonSerializer<Tweet>{

	@Override
	public JsonElement serialize(Tweet tweet, Type type,JsonSerializationContext context) {
		
        SimpleDateFormat dateTimeParser = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("text", tweet.getText());
		jsonObject.addProperty("user", tweet.getUser());
		jsonObject.addProperty("favoriteCount",tweet.getFavoriteCount());
		jsonObject.addProperty("retweetCount",tweet.getRetweetCount());
		jsonObject.addProperty("createdAt",dateTimeParser.format(tweet.getCreatedAt()));
		jsonObject.addProperty("lang",tweet.getLang());
		jsonObject.addProperty("location",tweet.getLocation().getLatitude()+","+tweet.getLocation().getLongitude());
		return jsonObject;
	}

}
