package com.teamdmc.kemie.naver;

import com.teamdmc.kemie.cmn.DTO;

public class NaverSearchItemVO extends DTO {

	private String title      ;
	private String link       ;
	private String description;
	private String pubDate   ;
	
	public NaverSearchItemVO() {}
	
	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Item [title=" + title + ", link=" + link + ", description=" + description + ", pubDate=" + pubDate
				+ ", toString()=" + super.toString() + "]";
	}
}
