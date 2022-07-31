package com.teamdmc.kemie.upbit.domain;

import com.teamdmc.kemie.cmn.DTO;

public class AcountsVO extends DTO{

	private String currency; // 화폐를 의미하는 영문 대문자 코드
	private String balance; // 주문가능 금액/수량
	private String locked; // 주문 중 묶여있는 금액/수량
	private String avg_buy_price; // 매수평균가
	private boolean avg_buy_price_modified; // 매수평균가 수정 여부
	private String unit_currency; // 평단가 기준 화폐
	
	public AcountsVO() {}
	
	public AcountsVO(String currency, String balance, String locked, String avg_buy_price,
			boolean avg_buy_price_modified, String unit_currency) {
		super();
		this.currency = currency;
		this.balance = balance;
		this.locked = locked;
		this.avg_buy_price = avg_buy_price;
		this.avg_buy_price_modified = avg_buy_price_modified;
		this.unit_currency = unit_currency;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getLocked() {
		return locked;
	}

	public void setLocked(String locked) {
		this.locked = locked;
	}

	public String getAvg_buy_price() {
		return avg_buy_price;
	}

	public void setAvg_buy_price(String avg_buy_price) {
		this.avg_buy_price = avg_buy_price;
	}

	public boolean isAvg_buy_price_modified() {
		return avg_buy_price_modified;
	}

	public void setAvg_buy_price_modified(boolean avg_buy_price_modified) {
		this.avg_buy_price_modified = avg_buy_price_modified;
	}

	public String getUnit_currency() {
		return unit_currency;
	}

	public void setUnit_currency(String unit_currency) {
		this.unit_currency = unit_currency;
	}

	@Override
	public String toString() {
		return "AcountsVO [currency=" + currency + ", balance=" + balance + ", locked=" + locked + ", avg_buy_price="
				+ avg_buy_price + ", avg_buy_price_modified=" + avg_buy_price_modified + ", unit_currency="
				+ unit_currency + ", toString()=" + super.toString() + "]";
	}
	
}