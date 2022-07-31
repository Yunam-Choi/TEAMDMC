package com.teamdmc.kemie.upbit.domain;

import com.teamdmc.kemie.cmn.DTO;

public class MinitesCandleVO extends DTO{
	
	private String market; // 마켓명
	private String candle_date_time_utc; // 캔들 기준 시각(UTC 기준)	
	private String candle_date_time_kst; // 캔들 기준 시각(KST 기준)	
	private Double opening_price; // 시가
	private Double high_price; // 고가
	private Double low_price; // 저가
	private Double trade_price; // 종가
	private long timestamp; // 해당 캔들에서 마지막 틱이 저장된 시각	
	private Double candle_acc_trade_price; // 누적 거래 금액	
	private Double candle_acc_trade_volume; // 누적 거래량	
	private int unit; // 분 단위(유닛)	

	
	public MinitesCandleVO() {}

	public MinitesCandleVO(String market, String candle_date_time_utc, String candle_date_time_kst,
			Double opening_price, Double high_price, Double low_price, Double trade_price, long timestamp,
			Double candle_acc_trade_price, Double candle_acc_trade_volume, int unit) {
		super();
		this.market = market;
		this.candle_date_time_utc = candle_date_time_utc;
		this.candle_date_time_kst = candle_date_time_kst;
		this.opening_price = opening_price;
		this.high_price = high_price;
		this.low_price = low_price;
		this.trade_price = trade_price;
		this.timestamp = timestamp;
		this.candle_acc_trade_price = candle_acc_trade_price;
		this.candle_acc_trade_volume = candle_acc_trade_volume;
		this.unit = unit;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getCandle_date_time_utc() {
		return candle_date_time_utc;
	}

	public void setCandle_date_time_utc(String candle_date_time_utc) {
		this.candle_date_time_utc = candle_date_time_utc;
	}

	public String getCandle_date_time_kst() {
		return candle_date_time_kst;
	}

	public void setCandle_date_time_kst(String candle_date_time_kst) {
		this.candle_date_time_kst = candle_date_time_kst;
	}

	public Double getOpening_price() {
		return opening_price;
	}

	public void setOpening_price(Double opening_price) {
		this.opening_price = opening_price;
	}

	public Double getHigh_price() {
		return high_price;
	}

	public void setHigh_price(Double high_price) {
		this.high_price = high_price;
	}

	public Double getLow_price() {
		return low_price;
	}

	public void setLow_price(Double low_price) {
		this.low_price = low_price;
	}

	public Double getTrade_price() {
		return trade_price;
	}

	public void setTrade_price(Double trade_price) {
		this.trade_price = trade_price;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Double getCandle_acc_trade_price() {
		return candle_acc_trade_price;
	}

	public void setCandle_acc_trade_price(Double candle_acc_trade_price) {
		this.candle_acc_trade_price = candle_acc_trade_price;
	}

	public Double getCandle_acc_trade_volume() {
		return candle_acc_trade_volume;
	}

	public void setCandle_acc_trade_volume(Double candle_acc_trade_volume) {
		this.candle_acc_trade_volume = candle_acc_trade_volume;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "MinitesCandleVO [market=" + market + ", candle_date_time_utc=" + candle_date_time_utc
				+ ", candle_date_time_kst=" + candle_date_time_kst + ", opening_price=" + opening_price
				+ ", high_price=" + high_price + ", low_price=" + low_price + ", trade_price=" + trade_price
				+ ", timestamp=" + timestamp + ", candle_acc_trade_price=" + candle_acc_trade_price
				+ ", candle_acc_trade_volume=" + candle_acc_trade_volume + ", unit=" + unit + ", toString()="
				+ super.toString() + "]";
	}

	public MinitesCandleVO mCandleNull(String market) {
		return new MinitesCandleVO(market, null, null, 0.0, 0.0, 0.0, 0.0, 0, 0.0, 0.0, 0);
	}		
}