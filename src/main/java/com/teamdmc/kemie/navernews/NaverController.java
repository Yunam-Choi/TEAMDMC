package com.teamdmc.kemie.navernews;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.teamdmc.kemie.cmn.SearchVO;

@Controller("naverController")
@RequestMapping("naver")
public class NaverController {
	
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	NaverService naverService;
	
	public NaverController() {}
	
	@RequestMapping(value="/naverView.do", method=RequestMethod.GET)
	public String naverView() throws SQLException{
		
		return "mainPage";
	}
	
	//http://localhost:8081/ehr/naverBlog/doRetrieve.do
	@RequestMapping(value="/doRetrieve.do", method=RequestMethod.GET
			, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(SearchVO inVO) throws SQLException{
		String jsonString = "";
		
		LOG.debug("=================================");
		LOG.debug("doRetrieve");
		LOG.debug("inVO : "+ inVO);
		LOG.debug("=================================");
		
		if(null == inVO.getSearchWord() && inVO.getSearchWord().equals("")) {
			throw new NullPointerException("검색어를 입력하세요");
		}
		
		List<Item> list = naverService.doRetrieve();
		Gson gson = new Gson();
		jsonString = gson.toJson(list);
		
		LOG.debug("jsonString : "+ jsonString);
		LOG.debug("=================================");
		
		return jsonString;
	}

}
