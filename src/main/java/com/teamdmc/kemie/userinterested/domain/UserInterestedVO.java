package com.teamdmc.kemie.userinterested.domain;

import com.teamdmc.kemie.cmn.DTO;

public class UserInterestedVO extends DTO{
	private int uicIndex;      // 관심 암호화폐 인덱스
	private String uicMarket; // 거래 대상 암호화폐 한글명
	private String uId;        // 회원 ID
	
	public UserInterestedVO() {}

	public UserInterestedVO(int uicIndex, String uicMarket, String uId) {
		this.uicIndex = uicIndex;
		this.uicMarket = uicMarket;
		this.uId = uId;
	}

	public int getUicIndex() {
		return uicIndex;
	}

	public void setUicIndex(int uicIndex) {
		this.uicIndex = uicIndex;
	}

	public String getUicMarket() {
		return uicMarket;
	}

	public void setUicMarket(String uicMarket) {
		this.uicMarket = uicMarket;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	@Override
	public String toString() {
		return "UserInterestedVO [uicIndex=" + uicIndex + ", uicMarket=" + uicMarket + ", uId=" + uId + "]";
	}
}