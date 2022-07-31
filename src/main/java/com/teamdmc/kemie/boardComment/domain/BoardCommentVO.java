package com.teamdmc.kemie.boardComment.domain;

import com.teamdmc.kemie.cmn.DTO;

public class BoardCommentVO extends DTO{
	private int bcSeq;         // 게시판 댓글 순번
	private String bcContents; // 게시판 댓글 내용
	private int bSeq;          // 게시판 순번
	private String uId;        // 회원 ID
	private String uNick;      // 회원 닉네임
	private String regDt;      // 작성일
	
	public BoardCommentVO() {}

	public BoardCommentVO(int bcSeq, String bcContents, int bSeq, String uId, String uNick, String regDt) {
		super();
		this.bcSeq = bcSeq;
		this.bcContents = bcContents;
		this.bSeq = bSeq;
		this.uId = uId;
		this.uNick = uNick;
		this.regDt = regDt;
	}
	
	public int getBcSeq() {
		return bcSeq;
	}

	public void setBcSeq(int bcSeq) {
		this.bcSeq = bcSeq;
	}

	public String getBcContents() {
		return bcContents;
	}

	public void setBcContents(String bcContents) {
		this.bcContents = bcContents;
	}

	public int getbSeq() {
		return bSeq;
	}

	public void setbSeq(int bSeq) {
		this.bSeq = bSeq;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
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

	@Override
	public String toString() {
		return "boardVO [bcSeq=" + bcSeq + ", bcContents=" + bcContents + ", bSeq=" + bSeq + ", uId=" + uId + ", uNick="
				+ uNick + ", regDt=" + regDt + ", toString()=" + super.toString() + "]";
	}
}