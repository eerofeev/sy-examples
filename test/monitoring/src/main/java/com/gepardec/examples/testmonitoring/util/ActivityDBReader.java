package com.gepardec.examples.testmonitoring.util;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class ActivityDBReader {
	public List<Activity> read(ActivityDBReaderRequestBuilder builder) throws IOException{
		java.net.URL queryUrl = new java.net.URL(System.getProperty(
				"RESTActivityServer.serverURL", "http://localhost:8080")
				+ "/overlord-rtgov/activity/query");
		java.net.HttpURLConnection connection = (java.net.HttpURLConnection) queryUrl
				.openConnection();
		String userPassword = System.getProperty(
				"RESTActivityServer.serverUsername", "fsw")
				+ ":"
				+ System.getProperty("RESTActivityServer.serverPassword", "fsw");
		String encoding = javax.xml.bind.DatatypeConverter.printBase64Binary(userPassword.getBytes());
		connection.setRequestProperty("Authorization", "Basic " + encoding);
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setAllowUserInteraction(false);
		connection.setRequestProperty("Content-Type", "application/json");
		java.io.OutputStream os = connection.getOutputStream();

		// Use jackson to serialize the query spec
		os.write(builder.toGson().getBytes());
		os.flush();
		os.close();
		java.io.InputStream is = connection.getInputStream();
		List<Activity> res = new GsonBuilder().create().fromJson(
				IOUtils.toString(is), new TypeToken<List<Activity>>() {
				}.getType());
		is.close();
		return res;
	}
	
	public List<Activity> read() throws IOException{
		return read(new ActivityDBReaderRequestBuilder());
	}
}
