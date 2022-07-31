package com.teamdmc.kemie.upbit.dao;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamdmc.kemie.upbit.domain.AcountsVO;
import com.teamdmc.kemie.upbit.domain.AllMarketVO;
import com.teamdmc.kemie.upbit.domain.DepositsVO;
import com.teamdmc.kemie.upbit.domain.MinitesCandleVO;
import com.teamdmc.kemie.upbit.domain.OrdersVO;
import com.teamdmc.kemie.upbit.domain.TickerVO;
import com.teamdmc.kemie.upbit.domain.WithdrawsVO;

@Repository("apiDao")
public class ApiDao {

	final Logger LOG = LogManager.getLogger(getClass());

	public int doOrders(String token, HashMap<String, String> params) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost("https://api.upbit.com/v1/orders");
			request.setHeader("Content-Type", "application/json");
			request.addHeader("Authorization", token);
			request.setEntity(new StringEntity(new Gson().toJson(params)));

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			System.out.println(EntityUtils.toString(entity, "UTF-8"));

			return 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<WithdrawsVO> getWithdraws(String token, String currency) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://api.upbit.com/v1/withdraws?currency=" + currency);
			request.setHeader("Content-Type", "application/json");
			request.addHeader("Authorization", token);

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			String data = EntityUtils.toString(entity, "UTF-8");
			LOG.debug("data: " + data);

			List<WithdrawsVO> withList = Arrays.asList(new ObjectMapper().readValue(data, WithdrawsVO[].class));
			for (WithdrawsVO vo : withList)
				LOG.debug("vo: " + vo);

			return withList;
		} catch (IOException e) {
			LOG.debug(e.toString());
			System.out.println(e.getMessage());
		}
		return null;
	}

	public List<DepositsVO> getDeposit(String token, String currency) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://api.upbit.com/v1/deposits?currency=" + currency);
			request.setHeader("Content-Type", "application/json");
			request.addHeader("Authorization", token);

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			String data = EntityUtils.toString(entity, "UTF-8");
			LOG.debug("data: " + data);

			List<DepositsVO> depoList = Arrays.asList(new ObjectMapper().readValue(data, DepositsVO[].class));
			for (DepositsVO vo : depoList)
				LOG.debug("vo: " + vo);

			return depoList;
		} catch (IOException e) {
			LOG.debug(e.toString());
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int depositKRW(String token, HashMap<String, String> params) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost("https://api.upbit.com/v1/deposits/krw");
			request.setHeader("Content-Type", "application/json");
			request.addHeader("Authorization", token);
			request.setEntity(new StringEntity(new Gson().toJson(params)));

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			String data = EntityUtils.toString(entity, "UTF-8");

			if (data.contains("error"))
				return 0;

			System.out.println(data);

			return 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int withDrawsKRW(String token, HashMap<String, String> params) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost("https://api.upbit.com/v1/withdraws/krw");
			request.setHeader("Content-Type", "application/json");
			request.addHeader("Authorization", token);
			request.setEntity(new StringEntity(new Gson().toJson(params)));

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			String data = EntityUtils.toString(entity, "UTF-8");

			if (data.contains("error"))
				return 0;

			System.out.println(data);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<AcountsVO> getAccounts(String token) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://api.upbit.com/v1/accounts"); // 사용 API URL
			request.setHeader("Content-Type", "application/json");
			request.addHeader("Authorization", token); // 인증 토큰

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			String data = EntityUtils.toString(entity, "UTF-8");

			if (data.contains("no_authorization_i_p")) {
				LOG.debug("=====================================");
				LOG.debug("=인증되지 않은 IP오류입니다. UPBIT IP 권환 확인 요망=");
				LOG.debug("=https://upbit.com/");
				LOG.debug("=====================================");
			}

			LOG.debug("=data=" + data);

			Type acountTypeToken = new TypeToken<ArrayList<AcountsVO>>() {
			}.getType();
			return new Gson().fromJson(data, acountTypeToken);
		} catch (IOException e) {
			LOG.debug(e.getMessage());
		}
		return null;
	}

	public List<AllMarketVO> getAllMarket(String isDetails) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet("https://api.upbit.com/v1/market/all?isDetails=" + isDetails);
			request.setHeader("Content-Type", "application/json");

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			String data = EntityUtils.toString(entity, "UTF-8");

			LOG.debug("=data=" + data);

			Type allMarketTypeToken = new TypeToken<ArrayList<AllMarketVO>>() {
			}.getType();
			return new Gson().fromJson(data, allMarketTypeToken);
		} catch (IOException e) {
			LOG.debug(e.getMessage());
		}
		return null;
	}

	public List<MinitesCandleVO> getMiniutes(String marketName) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(
					"https://api.upbit.com/v1/candles/minutes/1?market=" + marketName + "&count=1");
			request.setHeader("Content-Type", "application/json");

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			String data = EntityUtils.toString(entity, "UTF-8");

			LOG.debug("=data=" + data);

			Type mCandleTypeToken = new TypeToken<ArrayList<MinitesCandleVO>>() {
			}.getType();
			return new Gson().fromJson(data, mCandleTypeToken);
		} catch (IOException e) {
			LOG.debug(e.getMessage());
		}
		return null;
	}

	public List<TickerVO> getTicker(String markets) {
		if (markets.length() > 0) {
			try {
				HttpClient client = HttpClientBuilder.create().build();
				HttpGet request = new HttpGet("https://api.upbit.com/v1/ticker?markets=" + markets);
				request.setHeader("Content-Type", "application/json");

				HttpResponse response = client.execute(request);
				HttpEntity entity = response.getEntity();

				String data = EntityUtils.toString(entity, "UTF-8");

				LOG.debug("=data=" + data);

				Type tickerType = new TypeToken<ArrayList<TickerVO>>() {
				}.getType();
				return new Gson().fromJson(data, tickerType);
			} catch (IOException e) {
				LOG.debug(e.getMessage());
			}
		}
		return null;
	}

}
