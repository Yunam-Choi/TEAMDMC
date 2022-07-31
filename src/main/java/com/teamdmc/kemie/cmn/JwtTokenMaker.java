package com.teamdmc.kemie.cmn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


public class JwtTokenMaker {
	final Logger LOG = LogManager.getLogger(getClass());
	
	private String accessKey = "WwyxkDxjCrxn3L6WypXGDiL92xxJcG8sDT6kiVG5";
	private String secretKey = "tS269yZPTIwE4inqSxOWnGCrYHDlAEFu0bch8lKu";
	
	public String jwtTokenMaker() {
		if(accessKey.length() == 40 && secretKey.length() == 40) {
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			String jwtToken = JWT.create()
					.withClaim("access_key", accessKey)
					.withClaim("nonce", UUID.randomUUID().toString())
					.sign(algorithm);
			String authenticationToken = "Bearer " + jwtToken;
			
			return authenticationToken;
		} else return "토큰 키를 확인하세요";
	}
	
	public String depositAndPostKRW(String filedName, String amount) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if(accessKey.length() == 40 && secretKey.length() == 40) {
			ArrayList<String> queryElements = new ArrayList<>();
			
	        for(Map.Entry<String, String> entity : paramsHashMap(filedName, amount).entrySet()) {
	            queryElements.add(entity.getKey() + "=" + entity.getValue());
	        }
			
	        String queryString = String.join("&", queryElements.toArray(new String[0]));
	        
	        MessageDigest md = MessageDigest.getInstance("SHA-512");
	        md.update(queryString.getBytes("UTF-8"));
	        
	        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
	        
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			String jwtToken = JWT.create()
					.withClaim("access_key", accessKey)
					.withClaim("nonce", UUID.randomUUID().toString())
	                .withClaim("query_hash", queryHash)
	                .withClaim("query_hash_alg", "SHA512")
					.sign(algorithm);
			
			String authenticationToken = "Bearer " + jwtToken;
			
			return authenticationToken;
		} else return "토큰 키를 확인하세요";
	}
	
	public String order(String market, HashMap<String, String> params) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		if(accessKey.length() == 40 && secretKey.length() == 40) {
			ArrayList<String> queryElements = new ArrayList<>();
			
	        for(Map.Entry<String, String> entity : params.entrySet()) {
	            queryElements.add(entity.getKey() + "=" + entity.getValue());
	        }
			
	        String queryString = String.join("&", queryElements.toArray(new String[0]));
	        
	        MessageDigest md = MessageDigest.getInstance("SHA-512");
	        md.update(queryString.getBytes("UTF-8"));
	        
	        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));
	        
			Algorithm algorithm = Algorithm.HMAC256(secretKey);
			String jwtToken = JWT.create()
					.withClaim("access_key", accessKey)
					.withClaim("nonce", UUID.randomUUID().toString())
	                .withClaim("query_hash", queryHash)
	                .withClaim("query_hash_alg", "SHA512")
					.sign(algorithm);
			
			String authenticationToken = "Bearer " + jwtToken;
			
			return authenticationToken;
		} else return "토큰 키를 확인하세요";
	}
	
	public HashMap<String, String> paramsHashMap(String filedName, String amount) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(filedName, amount);
		
		return params;
	}
	
	public HashMap<String, String> orderParamHash(String market, String side, String volume, String price, String ord_type) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("market", market);
        params.put("side", side);
        params.put("volume", volume);
        params.put("price", price);
        params.put("ord_type", ord_type);
		
		return params;
	}
}