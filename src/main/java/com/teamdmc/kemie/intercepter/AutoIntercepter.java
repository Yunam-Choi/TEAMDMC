//package com.teamdmc.kemie.intercepter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//@SuppressWarnings("deprecation")
//public class AutoIntercepter extends HandlerInterceptorAdapter {
//
//	final Logger LOG = LogManager.getLogger(getClass());
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		LOG.debug("==========================");
//		LOG.debug("=preHandle=컨트롤러 호출되기 전=");
//		LOG.debug("==========================");
//		return super.preHandle(request, response, handler);
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		LOG.debug("============================");
//		LOG.debug("=postHandle=컨트롤러 실행 후 호출=");
//		LOG.debug("============================");
//		super.postHandle(request, response, handler, modelAndView);
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		LOG.debug("==================================");
//		LOG.debug("=afterCompletion=모든 일이 완료되었을 때=");
//		LOG.debug("==================================");
//		super.afterCompletion(request, response, handler, ex);
//	}
//
//	
//	
//}