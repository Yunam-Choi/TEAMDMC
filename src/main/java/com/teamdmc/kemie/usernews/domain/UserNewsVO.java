package com.teamdmc.kemie.usernews.domain;

import com.teamdmc.kemie.cmn.DTO;

public class UserNewsVO extends DTO{
	private int unIndex;       // 알림 인덱스
	private String unContents; // 회원 알림 내용
	private String unTime;     // 회원 알림 시간
	private String uId;        // 회원 ID
	
	public UserNewsVO() {}
	
	public UserNewsVO(int unIndex, String unContents, String unTime, String uId) {
		super();
		this.unIndex = unIndex;
		this.unContents = unContents;
		this.unTime = unTime;
		this.uId = uId;
	}

	public int getUnIndex() {
		return unIndex;
	}

	public void setUnIndex(int unIndex) {
		this.unIndex = unIndex;
	}

	public String getUnContents() {
		return unContents;
	}

	public void setUnContents(String unContents) {
		this.unContents = unContents;
	}

	public String getUnTime() {
		return unTime;
	}

	public void setUnTime(String unTime) {
		this.unTime = unTime;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	@Override
	public String toString() {
		return "UserNewsVO [unIndex=" + unIndex + ", unContents=" + unContents + ", unTime=" + unTime + ", uId=" + uId
				+ ", toString()=" + super.toString() + "]";
	}
}