package com.teamdmc.kemie.usernews.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.usernews.domain.UserNewsVO;
import com.teamdmc.kemie.usernews.service.UserNewsService;

@Controller("userNewsController")
@RequestMapping("user")
public class UserNewsController {
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	UserNewsService userNewsService;
	
	public UserNewsController() {}
	
	// test url: http://localhost:8081/ehr/user/add.do
	// http://localhost:8081/ehr/user/add.do 이 요청이 되면 Controller가 아래의 메서드를 실행하여 매핑시켜준다.
	@RequestMapping(value = "/add.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody // 스프링에서 비동기 처리를 하는 경우, Http 요청의 본문 body부분이 전달된다.
	public String doInsert(UserNewsVO inVO) throws SQLException{
		String jsonString = "";
		LOG.debug("=========================");
		LOG.debug("=inVO: ="+inVO);
		LOG.debug("=========================");
		String resultMsg = "";
		
		int flag = userNewsService.doInsert(inVO);
		
		if(flag == 1) resultMsg = inVO.getuId() + "가 입력되었습니다.";
		else resultMsg = inVO.getuId() + "가 입력되지 않았습니다.";
		
		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		Gson gson = new Gson();
		
		jsonString = gson.toJson(message);
		
		LOG.debug("=========================");
		LOG.debug("=jsonString: ="+jsonString);
		LOG.debug("=========================");
		
		return jsonString;
	}
	
	@RequestMapping(value="/doUpdate.do", method = RequestMethod.POST, produces =  "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(UserNewsVO inVO) throws SQLException{
		String jsonString = "";
		
		LOG.debug("---------------------------");
		LOG.debug("-inVO- : " + inVO);
		LOG.debug("---------------------------");
		
		String resultMsg = "";
		int flag = userNewsService.doUpdate(inVO);
		if(1==flag) {
			resultMsg = inVO.getuId() + "가 수정되었습니다.";
		} else {
			resultMsg = inVO.getuId() + "가 수정 실패!";
		}
		
		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		Gson gson = new Gson();
		jsonString = gson.toJson(message);
		
		LOG.debug("---------------------------");
		LOG.debug("--jsonString-- : " + jsonString);
		LOG.debug("---------------------------");
		
		return jsonString;
	}
	
	// http://localhost:8081/ehr/user/doDelete.do?uId=p26
	@RequestMapping(value="/doDelete.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(HttpServletRequest req, UserNewsVO inVO) throws SQLException{
		String jsonString ="";
		String unIndex = req.getParameter("unIndex");
		
		LOG.debug("---------------------------");
		LOG.debug("--unIndex : " + unIndex);
		// command로 param 읽기 (from name 값이 vo memeber 변수와 이름이 일치, setter로 값이 설정됨)
		LOG.debug("--inVO-- : " + inVO);
		LOG.debug("---------------------------");
		
		String resultMsg = "";
		int flag = userNewsService.doDelete(inVO);

		if(1==flag) {
			resultMsg = inVO.getuId() + "가 삭제되었습니다.";
		}else {
			resultMsg = inVO.getuId() + "가 삭제 실패!";
		}
		
		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		Gson gson = new Gson();
		
		jsonString = gson.toJson(message);
		
		LOG.debug("---------------------------");
		LOG.debug("--jsonString-- : " + jsonString);
		LOG.debug("---------------------------");
		
		return jsonString;
	}
	
	
	// GET 방식으로http://localhost:8081/ehr/user/doSelectOne.do?uId=p26 요청
	@RequestMapping(value = "/doSelectOne.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody 
	// 스프링에서 비동기 처리를 하는 경우 HTTP 요청의 본문 BODY 부분이 전달 됨
	// UserNewsVO inVO : form name VO 멤버 변수명이 동일하면 자동으로 맵핑
	public String doSelectOne(UserNewsVO inVO) throws SQLException{
		LOG.debug("--------------------------");
		LOG.debug("-inVO- : " + inVO);
		LOG.debug("--------------------------");
		
		UserNewsVO outVO = userNewsService.doSelectOne(inVO);
		Gson gson = new Gson();
		String jsonString = gson.toJson(outVO);
		
		LOG.debug("--------------------------");
		LOG.debug("-jsonString- : " + jsonString);
		LOG.debug("--------------------------");
		
		return jsonString;
	}
}
