package com.teamdmc.kemie.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.teamdmc.kemie.cmn.JwtTokenMaker;
import com.teamdmc.kemie.upbit.dao.ApiDao;
import com.teamdmc.kemie.upbit.domain.AcountsVO;
import com.teamdmc.kemie.upbit.domain.AllMarketVO;
import com.teamdmc.kemie.upbit.domain.MinitesCandleVO;
import com.teamdmc.kemie.upbit.domain.TickerVO;

@Controller("balancesController")
public class BalancesController {
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	ApiDao apiDao;
	
	@RequestMapping(value = "/getWithdraws.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getWithdraws(String currency) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String token = new JwtTokenMaker().depositAndPostKRW("currency", currency);

		LOG.debug("========================================");
		LOG.debug("====getWithdraws()====");
		LOG.debug("param: " + currency);
		LOG.debug("token: " + token);
		
		//return new Gson().toJson(apiDao.getWithdraws(token, currency));
		return "뿡";
	}
	
	@RequestMapping(value = "/getDeposit.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getDeposit(String currency) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String token = new JwtTokenMaker().depositAndPostKRW("currency", currency);

		LOG.debug("========================================");
		LOG.debug("====getDeposit()====");
		LOG.debug("param: " + currency);
		LOG.debug("token: " + token);
		
		return new Gson().toJson(apiDao.getDeposit(token, currency)); 
	}

	@RequestMapping(value = "/withDrawsKRW.do", method = RequestMethod.POST)
	@ResponseBody
	public int withDrawsKRW(String amount) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		JwtTokenMaker jwtTokenMaker = new JwtTokenMaker();
		String token = jwtTokenMaker.depositAndPostKRW("amount", amount); // 토큰 생성
		HashMap<String, String> params = jwtTokenMaker.paramsHashMap("amount", amount);
		
		return apiDao.withDrawsKRW(token, params);
	}

	@RequestMapping(value = "/depositKRW.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int depositKRW(String amount) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		JwtTokenMaker jwtTokenMaker = new JwtTokenMaker();
		String token = jwtTokenMaker.depositAndPostKRW("amount", amount); // 토큰 생성
		HashMap<String, String> params = jwtTokenMaker.paramsHashMap("amount", amount);
		
		return apiDao.depositKRW(token, params);
	}
	
	@RequestMapping(value = "/getAccounts.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAccounts() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String token = new JwtTokenMaker().jwtTokenMaker();
		
		return new Gson().toJson(apiDao.getAccounts(token));
	}
	
	@RequestMapping(value = "/getAllItems.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAllItems(String currency) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return new Gson().toJson(apiDao.getAllMarket("false"));
	}
	
	@RequestMapping(value = "/balancesPage.do")
	public String balancesPage(Model model) {
		JwtTokenMaker tokenMaker = new JwtTokenMaker();

		String token = tokenMaker.jwtTokenMaker(); // JwTTokenMaker 객체의 jwtTokenMaker 메소드를 이용하여 토큰 생성

		LOG.debug(token);

		List<AcountsVO> list = apiDao.getAccounts(token);

		if (list.size() > 0) { // API를 통해 가져온 json 데이터가 있으면
			LOG.debug("---------- list.size() > 0 is true");
			List<List<MinitesCandleVO>> mListList = new ArrayList<List<MinitesCandleVO>>();

			List<MinitesCandleVO> minitesList = new ArrayList<MinitesCandleVO>();

			List<AllMarketVO> marketList = apiDao.getAllMarket("true");
			LOG.debug("allMarketList==" + marketList);
			
			double sum = 0;

			for(AcountsVO vo : list) {
				if(vo.getCurrency().equals("KRW"))
					sum = Math.round(Double.parseDouble(vo.getBalance()));
			}
			
			for (int i = 0; i < list.size(); i++) {
				for (int j = 1; j < marketList.size(); j++) {
					// 만약 marketList에 포함된 market명에 KRW를 포함하고, currency와 market명(ex. KRW-BTC)에서
					// 구분자('-') 뒤 3글자가 같으면 실행
					// 위의 조건식이 참일 경우 for문을 통해 API 분 캔들 조회 실행
					String currnecyStr = marketList.get(j).getMarket(); // marketList의 j번째 요소의 market 변수 가져오기
					currnecyStr = currnecyStr.substring(currnecyStr.lastIndexOf('-') + 1); // market 값(ex. KRW-BTC에서 BTC만 추출)
					
					if (marketList.get(j).getMarket().contains("KRW") && currnecyStr.equals(list.get(i).getCurrency())) {
						// 원하는 currency에 해당하는 데이터 만들기(분 캔들 조회)
						
						minitesList = apiDao.getMiniutes("KRW-" + list.get(i).getCurrency());
						LOG.debug("========minitesList: "+minitesList);
						mListList.add(minitesList);
					} else if (j == marketList.size() - 1) {
						minitesList.add(new MinitesCandleVO().mCandleNull(list.get(i).getCurrency()));
						mListList.add(minitesList);
					}
				}
				LOG.debug("========mListList: "+mListList);
				
				sum += Math.round(
						mListList.get(i).get(0).getTrade_price() * Double.parseDouble(list.get(i).getBalance()));
			}
			
			model.addAttribute("sum", sum);
			model.addAttribute("mListList", mListList);
			model.addAttribute("list", list);
			LOG.debug("---------- models on! success!");
		}
		// 코인 시세
		String marketContainKRW = ""; // 가상 화폐 market 명에서 KRW를 포함하는 market명을 저장하는 String

		List<AllMarketVO> marketList = apiDao.getAllMarket("false");

		for (int i = 0; i < marketList.size(); i++)
			if (marketList.get(i).getMarket().contains("KRW"))
				marketContainKRW += marketList.get(i).getMarket() + "%2C";
		
		marketContainKRW = marketContainKRW.substring(0, marketContainKRW.length() - 3);
		LOG.debug("marketContainKRW 마지막 %2C 제거 후 : " + marketContainKRW);
		
		List<TickerVO> tickerList = apiDao.getTicker(marketContainKRW);
		LOG.debug("tickerList: " + tickerList);

		model.addAttribute("tickerList", tickerList);
		
		return "balancesPage";
	}
}