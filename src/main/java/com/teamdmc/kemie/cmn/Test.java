package com.teamdmc.kemie.cmn;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Test {

	public static void main(String[] args) {
	        try {
	            HttpClient client = HttpClientBuilder.create().build();
	            HttpGet request = new HttpGet("https://api.upbit.com/v1/candles/minutes/1?market=KRW-BTC&count=1");
	            request.setHeader("Content-Type", "application/json");

	            HttpResponse response = client.execute(request);
	            HttpEntity entity = response.getEntity();

	            System.out.println(EntityUtils.toString(entity, "UTF-8"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
}
}
