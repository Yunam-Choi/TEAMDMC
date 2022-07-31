package com.teamdmc.kemie.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.teamdmc.kemie.cmn.JwtTokenMaker;
import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.upbit.dao.ApiDao;
import com.teamdmc.kemie.upbit.domain.AcountsVO;
import com.teamdmc.kemie.upbit.domain.AllMarketVO;
import com.teamdmc.kemie.upbit.domain.TickerVO;
import com.teamdmc.kemie.user.domain.UserVO;
import com.teamdmc.kemie.userinterested.UserinterestedService;
import com.teamdmc.kemie.userinterested.domain.UserInterestedVO;

@Controller("exchangeController")
public class ExchangeController {
	final Logger LOG = LogManager.getLogger(getClass());
	
	final String NAMESPACE ="com.teamdmc.kemie.controller";
	
	//mybatis db연결객체
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
		
	@Autowired
	ApiDao apiDao;
	
	@Autowired
	UserinterestedService userinterestedService;
	
	//@RequestMapping(value = "/doOrders.do", method = RequestMethod.POST, produces = "charset=UTF-8")
	@RequestMapping(value = "/doOrders.do", method = RequestMethod.POST)
	@ResponseBody
	public String doOrders(String market, String side, String volume, String price, String ord_type) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		JwtTokenMaker jwtTokenMaker = new JwtTokenMaker();
		if(ord_type.equals("price") && volume.equals("null")) volume = null;
		if(ord_type.equals("market") && price.equals("null")) price = null;
		HashMap<String, String> params = jwtTokenMaker.orderParamHash(market, side, volume, price, ord_type);
		String token = new JwtTokenMaker().order(market, params);

		LOG.debug("========================================");
		LOG.debug("====doOrders()====");
		LOG.debug("param: " + params.toString());
		LOG.debug("token: " + token);
		
		return "ok";
		//return apiDao.doOrders(token, params);
	}
	
	@RequestMapping("/exchange.do")  
	public String exchange(HttpSession session, HttpServletRequest request, @RequestParam(value="market", defaultValue = "비트코인") String market, Model model){
		UserVO inVO = (UserVO) session.getAttribute("user"); // 로그인 세션 가져오기
		LOG.debug("============================");
		LOG.debug("=exchange.do=");
		LOG.debug("=HttpSession="+session);
		LOG.debug("=inVO="+inVO);
		LOG.debug("=market="+market);
		LOG.debug("============================");

		if(inVO == null) { // 세션 정보가 없는 경우 메인 페이지로 보냄
			LOG.debug("==세선 정보가 없습니다.==");
			return "mainPage"; 
		}
		
		// 관심 코인 시세 조회 -------------------------------------------------------
		String marketNames = ""; // 암호 화폐의 market 이름을 저장할 변수
		UserInterestedVO voVO = new UserInterestedVO(0, "", inVO.getuId()); // 가져온 세션으로 uic VO 생성
		
		List<UserInterestedVO> uicList = userinterestedService.getAll(voVO); // 관심코인 조회 -> market이름만 가져옴
		
		for(UserInterestedVO vo : uicList) 
			marketNames += vo.getUicMarket()+"%2C"; // market 값 뒤에 %2C를 붙여 url에 붙일 수 있도록 String값 조정

		if(marketNames.length() > 0) marketNames = marketNames.substring(0, marketNames.length()-3); // 마지막 %2C 제거
		
		List<TickerVO> uicTickerList = apiDao.getTicker(marketNames);
		
		model.addAttribute("uicTickerList", uicTickerList);

		// 전체 코인 시세 조회 -------------------------------------------------------
		List<AllMarketVO> marketList = apiDao.getAllMarket("false");
		LOG.debug("=marketList="+marketList);
		
		marketNames = ""; // marketNames 변수 초기화
		
		if(marketList.size() > 0) {
			for(AllMarketVO vo : marketList) {
				if(vo.getMarket().contains("KRW")) {
					marketNames+= vo.getMarket()+"%2C";
					if(vo.getKorean_name().equals(market)) model.addAttribute("market", vo.getMarket());
				}
			}
			
			marketNames = marketNames.substring(0, marketNames.length()-3);
			LOG.debug("=마지막 %2C 제거 marketNames="+marketNames);
			
			List<TickerVO> tickerList = apiDao.getTicker(marketNames);
			LOG.debug("=tickerList="+tickerList);
			
			model.addAttribute("tickerList", tickerList);
		}

		// 주문 가능 금액 조회 (매수=MyKRW, 매도=allAccounts)-------------------------------------------------------
		List<AcountsVO> list = apiDao.getAccounts(new JwtTokenMaker().jwtTokenMaker());
		
		if(list.size() > 0)
			for(AcountsVO vo : list)
				if(vo.getCurrency().equals("KRW")) model.addAttribute("MyKRW", vo.getBalance());
		
		if(list.size() <= 0 || list == null) model.addAttribute("MyKRW", 0);
		model.addAttribute("allAccounts", list);
		return "exchange";
	}	

	@RequestMapping(value = "/addOrDelete.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String addOrDelete(UserInterestedVO inVO) throws SQLException {

		LOG.debug("========================");
		LOG.debug("=addOrDelete()=");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("========================");
		
		MessageVO message = userinterestedService.addOrDelete(inVO);
		
		return new Gson().toJson(message);
	}
	
	//GET방식으로 : http://localhost:8081/ehr/user/doSelectOne.do?uId=p31
		@RequestMapping(value = "/getAll.do",method = RequestMethod.GET
				,produces = "application/json;charset=UTF-8")
		@ResponseBody //스프링에서 비동기 처리를 하는 경우, Http 요청의 본문 body부분이 전달된다.
		//UserVO inVO : form name VO 멤버변수명이 동일하면 자동으로 메핑한다.
		public String getAll(UserInterestedVO inVO) throws SQLException{
			LOG.debug("==============================");
			LOG.debug("=getAll()=");
			LOG.debug("=inVO="+inVO);
			LOG.debug("==============================");
			
			List<UserInterestedVO> list = userinterestedService.getAll(inVO);

			return new Gson().toJson(list);
		}
}