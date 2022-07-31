package com.teamdmc.kemie.upbit.domain;

import com.teamdmc.kemie.cmn.DTO;

public class AllMarketVO extends DTO{
	private String market; // 업비트에서 제공중인 시장 정보	
	private String korean_name; // 거래 대상 암호화폐 한글명	
	private String english_name; // 거래 대상 암호화폐 영문명
	
	public AllMarketVO() {}
	
	public AllMarketVO(String market, String korean_name, String english_name) {
		super();
		this.market = market;
		this.korean_name = korean_name;
		this.english_name = english_name;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getKorean_name() {
		return korean_name;
	}

	public void setKorean_name(String korean_name) {
		this.korean_name = korean_name;
	}

	public String getEnglish_name() {
		return english_name;
	}

	public void setEnglish_name(String english_name) {
		this.english_name = english_name;
	}

	@Override
	public String toString() {
		return "AllMarketVO [market=" + market + ", korean_name=" + korean_name + ", english_name=" + english_name
				+ ", toString()=" + super.toString() + "]";
	}

}