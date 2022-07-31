package com.teamdmc.kemie.board.domain;

import com.teamdmc.kemie.cmn.DTO;

public class BoardVO extends DTO{
	private int bSeq;         // 게시판 순번
	private String bTitle;    // 게시판 제목
	private String bContents; // 게시판 내용
	private int bReadCnt;  // 게시판 조회수
	private String uNick;     // 회원 닉네임
	private String regDt;     // 작성일
	private String uId;       // 작성자
	private String modDt;     // 수정일
	
	public BoardVO() {}

	public int getbSeq() {
		return bSeq;
	}

	public void setbSeq(int bSeq) {
		this.bSeq = bSeq;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContents() {
		return bContents;
	}

	public void setbContents(String bContents) {
		this.bContents = bContents;
	}

	public int getbReadCnt() {
		return bReadCnt;
	}

	public void setbReadCnt(int bReadCnt) {
		this.bReadCnt = bReadCnt;
	}

	public String getuNick() {
		return uNick;
	}

	public void setuNick(String uNick) {
		this.uNick = uNick;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public BoardVO(int bSeq, String bTitle, String bContents, int bReadCnt, String uNick, String regDt, String uId,
			String modDt) {
		super();
		this.bSeq = bSeq;
		this.bTitle = bTitle;
		this.bContents = bContents;
		this.bReadCnt = bReadCnt;
		this.uNick = uNick;
		this.regDt = regDt;
		this.uId = uId;
		this.modDt = modDt;
	}

	@Override
	public String toString() {
		return "BoardVO [bSeq=" + bSeq + ", bTitle=" + bTitle + ", bContents=" + bContents + ", bReadCnt=" + bReadCnt
				+ ", uNick=" + uNick + ", regDt=" + regDt + ", uId=" + uId + ", modDt=" + modDt + ", toString()="
				+ super.toString() + "]";
	}

}