package com.teamdmc.kemie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("kemieController")
public class KemieController {
	
	public KemieController() {}
	
	@RequestMapping("/board.do")  
	public String board() {
		System.out.println("메인 테스트입니다 메인 테스트입니다.");
		
		return "board";
	}
	
	@RequestMapping("/details.do")  
	public String details() {
		System.out.println("메인 테스트입니다 메인 테스트입니다.");
		
		return "details";
	}
	
	@RequestMapping("/faq.do")  
	public String faq() {
		System.out.println("메인 테스트입니다 메인 테스트입니다.");
		
		return "faq";
	}
	
	@RequestMapping("/loginPage.do")  
	public String loginPage() {
		System.out.println("메인 테스트입니다 메인 테스트입니다.");
		
		return "loginPage";
	}
	
	@RequestMapping("/signin.do")  
	public String signin() {
		System.out.println("메인 테스트입니다 메인 테스트입니다.");
		
		return "signin";
	}
	
	@RequestMapping("/write.do")  
	public String write() {
		System.out.println("메인 테스트입니다 메인 테스트입니다.");
		
		return "write";
	}
}
