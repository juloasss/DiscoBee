package fr.discobee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class Configuration {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private JsonObject element;
	private File configFile;
	
	public Configuration(String path) {
		configFile = new File(path);
		element = new JsonObject();
		if(!configFile.exists()) {
			save();
		}
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader(configFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(reader != null) element = GSON.fromJson(reader, JsonObject.class);
	}
	
	public JsonObject config() {
		return element;
	}
	
	public void save() {
		try {
			FileUtils.writeStringToFile(configFile, GSON.toJson(element), Charset.forName("UTF-8"));
			DiscoBee.logger.info("[Configuration] Saved configuration file to disk.");
		} catch (IOException e) {
			DiscoBee.logger.severe("[Configuration] An error occured while save the bot configuration:");
			e.printStackTrace();
		}
	}

	public String string(String key, String defaultValue) {
		if(!element.has(key))
			element.addProperty(key, defaultValue);
		DiscoBee.logger.info("[Configuration] Retrieved value : " + element.get(key).getAsString() + " from " + key);
		return element.get(key).getAsString();
	}
	
	public int integer(String key, int defaultValue) {
		if(!element.has(key))
			element.addProperty(key, defaultValue);
		DiscoBee.logger.info("[Configuration] Retrieved value : " + element.get(key).getAsInt() + " from " + key);
		return element.get(key).getAsInt();
	}

	public JsonArray getJsonArray(String key) {
		if(!element.has(key))
			element.add(key, new JsonArray());
		DiscoBee.logger.info("[Configuration] Retrieved value : " + element.get(key).getAsJsonArray().toString() + " from " + key);
		return element.get(key).getAsJsonArray();
	}
	
}
