package com.teamdmc.kemie.faq.domain;

import com.teamdmc.kemie.cmn.DTO;

public class FaqVO extends DTO{
	private int fSeq;         // 게시판 순번
	private String fTitle;    // 게시판 제목
	private String fContents; // 게시판 내용
	private String fReadCnt;  // 게시판 조회수
	private String uId;       // 작성자
	private String regDt;     // 작성일
	
	public FaqVO() {}
	
	public FaqVO(int fSeq, String fTitle, String fContents, String fReadCnt, String uId, String regDt) {
		super();
		this.fSeq = fSeq;
		this.fTitle = fTitle;
		this.fContents = fContents;
		this.fReadCnt = fReadCnt;
		this.uId = uId;
		this.regDt = regDt;
	}

	public int getfSeq() {
		return fSeq;
	}

	public void setfSeq(int fSeq) {
		this.fSeq = fSeq;
	}

	public String getfTitle() {
		return fTitle;
	}

	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}

	public String getfContents() {
		return fContents;
	}

	public void setfContents(String fContents) {
		this.fContents = fContents;
	}

	public String getfReadCnt() {
		return fReadCnt;
	}

	public void setfReadCnt(String fReadCnt) {
		this.fReadCnt = fReadCnt;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "boardVO [fSeq=" + fSeq + ", fTitle=" + fTitle + ", fContents=" + fContents + ", fReadCnt=" + fReadCnt
				+ ", uId=" + uId + ", regDt=" + regDt + ", toString()=" + super.toString() + "]";
	}
}