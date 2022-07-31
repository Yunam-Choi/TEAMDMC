package com.teamdmc.kemie.user.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.user.domain.UserVO;
import com.teamdmc.kemie.user.service.userService;

@Controller("userController")
@RequestMapping("userinfo")
public class UserController {
	
	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	userService Uservice;
	
	public UserController() {}
	
	@RequestMapping("/myPage.do")  
	public String myPage() {
		System.out.println("userController테스트입니다.!");
		return "myPage";
	}
	
	@RequestMapping(value = "/doInsert.do",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(UserVO inVO) throws SQLException{
		String jsonString = "";
		LOG.debug("==============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("==============================");
		
		int flag = Uservice.doInsert(inVO);
		String resultMessage = "";
		
		if(1==flag) {//가입 성공
			resultMessage = inVO.getuId()+"님의 가입을 환영합니다.";
		}else {
			resultMessage = inVO.getuId()+"가입에 실패 했습니다.";
		}
		
		MessageVO message=new MessageVO(String.valueOf(flag), resultMessage);
		Gson gson=new Gson();
		jsonString = gson.toJson(message);
		LOG.debug("==============================");
		LOG.debug("=jsonString="+jsonString);
		LOG.debug("==============================");		
		
		return jsonString;
	}
	
	@RequestMapping(value="/doDelete.do",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(UserVO inVO) throws SQLException{
		String jsonString = "";
		  
		LOG.debug("==============================");
		LOG.debug("=doDelete()=");
		LOG.debug("=inVO="+inVO);
		LOG.debug("==============================");		
		
		MessageVO message = Uservice.doDelete(inVO);
		// msgId
		//     1. 비밀번호 확인 필요 : 10
		//     2. 아이디 삭제: 20
		
		jsonString = new Gson().toJson(message);
		
		LOG.debug("==============================");
		LOG.debug("=jsonString="+jsonString);		
		LOG.debug("==============================");
		
		return jsonString;
//		return "login/doLogout.do";
	}
	
	// testURL : http://localhost:8080/kemie/doNickUpdate.do?nick=테스트01
	@RequestMapping(value="/doPassUpdate.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doPassUpdate(Model model, UserVO inVO) throws SQLException {
		String resultMsg = "";
		String jsonString = "";
		
		LOG.debug("==========================");
		LOG.debug("=doPassUpdate()=");
		LOG.debug("==========================");
		
		int flag = Uservice.doPassUpdate(inVO);
		
		if(flag == 1) resultMsg = "비밀번호가 성공적으로 변경되었습니다.";
		else resultMsg = "비밀번호가 변경되지 않았습니다.";
				
		jsonString = new Gson().toJson(new MessageVO(String.valueOf(flag), resultMsg));
		
		LOG.debug("=========================");
		LOG.debug("=jsonString: ="+jsonString);
		LOG.debug("=========================");
		
		return jsonString;
	}
	
	@RequestMapping(value="/idCheck.do",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")	
	@ResponseBody
	public String idCheck(UserVO inVO) throws SQLException{
		String jsonString = "";
		LOG.debug("==============================");
		LOG.debug("=inVO="+inVO);
		LOG.debug("==============================");
		
		String resultMsg = "";
		int flag = Uservice.idCheck(inVO);
		if(1==flag) {
			resultMsg =inVO.getuId()+"가 존재 합니다.";
		}else {
			resultMsg =inVO.getuId()+"는 사용할 수 있습니다.";
		}
		
		MessageVO message=new MessageVO(String.valueOf(flag), resultMsg);	
		jsonString = new Gson().toJson(message);
		LOG.debug("==============================");
		LOG.debug("=jsonString="+jsonString);		
		LOG.debug("==============================");
		return jsonString;
	}
	
	// testURL : http://localhost:8080/kemie/getPass.do?u_id=id01
		@RequestMapping(value="/passCheck.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public String passCheck(Model model, UserVO inVO) throws SQLException {
			String resultMsg = "";
			String jsonString = "";
			
			LOG.debug("==========================");
			LOG.debug("=getPass()=");
			LOG.debug("==========================");
			
			int flag = Uservice.passCheck(inVO);
			
			if(flag == 1) resultMsg = "이전과 동일한 비밀번호입니다!";
			else resultMsg = "이전과 동일하지 않은 비밀번호입니다.";
					
			jsonString = new Gson().toJson(new MessageVO(String.valueOf(flag), resultMsg));
			
			LOG.debug("=========================");
			LOG.debug("=jsonString: ="+jsonString);
			LOG.debug("=========================");
			
			return jsonString;
		}
	
	// testURL : http://localhost:8080/kemie/nickCheck.do?nick=테스트01
	@RequestMapping(value="/nickCheck.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String nickCheck(Model model, UserVO inVO) throws SQLException {
		String resultMsg = "";
		String jsonString = "";
		
		LOG.debug("==========================");
		LOG.debug("=nickCheck()=");
		LOG.debug("==========================");
		
		int flag = Uservice.nickCheck(inVO);
		
		if(flag == 1) resultMsg = inVO.getNick() + "이 존재 합니다.";
		else resultMsg = inVO.getNick() + "는 사용할 수 있습니다.";
				
		jsonString = new Gson().toJson(new MessageVO(String.valueOf(flag), resultMsg));
		
		LOG.debug("=========================");
		LOG.debug("=jsonString: ="+jsonString);
		LOG.debug("=========================");
		
		return jsonString;
	}
	
	// testURL : http://localhost:8080/kemie/doNickUpdate.do?nick=테스트01
		@RequestMapping(value="/doNickUpdate.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
		@ResponseBody
		public String doNickUpdate(Model model, UserVO inVO) throws SQLException {
			String resultMsg = "";
			String jsonString = "";
			
			LOG.debug("==========================");
			LOG.debug("=doNickUpdate()=");
			LOG.debug("==========================");
			
			int flag = Uservice.doNickUpdate(inVO);
			
			if(flag == 1) resultMsg = inVO.getNick() + "가 변경되었습니다.";
			else resultMsg = inVO.getNick() + "는 변경되지 않았습니다.";
					
			jsonString = new Gson().toJson(new MessageVO(String.valueOf(flag), resultMsg));
			
			LOG.debug("=========================");
			LOG.debug("=jsonString: ="+jsonString);
			LOG.debug("=========================");
			
			return jsonString;
		}
}
