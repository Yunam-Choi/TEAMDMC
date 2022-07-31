package com.teamdmc.kemie.naver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.teamdmc.kemie.cmn.DTO;

public class NaverSearchVO extends DTO {

	private String lastBuildDate;
	private List<NaverSearchItemVO> items = new ArrayList<NaverSearchItemVO>();
	
	public NaverSearchVO() {}

	public List<NaverSearchItemVO> getItems() {
		return items;
	}

	public void setItems(List<NaverSearchItemVO> items) {
		this.items = items;
	}

	public String getLastBuildDate() throws ParseException {
		SimpleDateFormat dtFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		SimpleDateFormat pbFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		return pbFormat.format(dtFormat.parse(lastBuildDate));
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}
}
