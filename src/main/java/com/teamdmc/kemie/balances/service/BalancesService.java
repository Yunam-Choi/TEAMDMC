package com.teamdmc.kemie.balances.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.teamdmc.kemie.upbit.dao.ApiDao;
import com.teamdmc.kemie.upbit.domain.AllMarketVO;
import com.teamdmc.kemie.upbit.domain.TickerVO;

public class BalancesService {

	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	ApiDao apiDao;
	
	public List<TickerVO> getAllMarket() {
		String marketContainKRW = "";
		
		List<AllMarketVO> marketList = apiDao.getAllMarket("false");

		for (int i = 0; i < marketList.size(); i++) {
			if (marketList.get(i).getMarket().contains("KRW"))
				marketContainKRW += marketList.get(i).getMarket() + "%2C";
		}
		marketContainKRW = marketContainKRW.substring(0, marketContainKRW.length() - 3);
		LOG.debug("marketContainKRW 마지막 %2C 제거 후 : " + marketContainKRW);
		
		List<TickerVO> tickerList = apiDao.getTicker(marketContainKRW);
		LOG.debug("tickerList: " + tickerList);
	
		return tickerList;
}
}
