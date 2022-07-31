package com.teamdmc.kemie.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SuppressWarnings("deprecation")
public class LoginInterceptor extends HandlerInterceptorAdapter {
	final Logger LOG = LogManager.getLogger(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//session이 없으면 로그인 페이지로 이동
		HttpSession session = request.getSession();
		
		//session 정보를 추출
		Object obj = session.getAttribute("user");
		LOG.debug("obj: "+obj);
			if(null==obj) {
				LOG.debug("request.getContextPath():"+request.getContextPath());
				//로그인 화면으로 이동
				response.sendRedirect(request.getContextPath()+"/login/loginAlert.do");
				//더이상 컨트롤러 요청으로 가지 않도록 false
				return false;
			}
		//컨트롤러 uri로 이동/false는 안감
		return true;
	}
}