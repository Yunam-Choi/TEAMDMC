package com.teamdmc.kemie.user.domain;

import com.teamdmc.kemie.cmn.DTO;

public class UserVO extends DTO{
	private String uId;     // 사용자 id
	private String passwd;  // 사용자 비밀번호
	private String name;    // 사용자 이름
	private String pNum;	// 사용자 전화번호
	private String nick;	// 닉네임
	private String type;	// 회원구분
	private String regDt;   // 가입일
	
	public UserVO() {}

	public UserVO(String uId, String passwd, String name, String pNum, String nick, String type, String regDt) {
		super();
		this.uId = uId;
		this.passwd = passwd;
		this.name = name;
		this.pNum = pNum;
		this.nick = nick;
		this.type = type;
		this.regDt = regDt;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "UserVO [uId=" + uId + ", passwd=" + passwd + ", name=" + name + ", pNum=" + pNum + ", nick=" + nick
				+ ", type=" + type + ", regDt=" + regDt + ", toString()=" + super.toString() + "]";
	}	
}