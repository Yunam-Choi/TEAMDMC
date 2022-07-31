package com.teamdmc.kemie.upbit.domain;

public class TickerVO {
	
	public String market;
	public String trade_date;
	public String trade_time;
	public String trade_date_kst;
	public String trade_time_kst;
	public long trade_timestamp;
	public double opening_price;
	public double high_price;
	public double low_price;
	public double trade_price;
	public double prev_closing_price;
	public String change;
	public double change_price;
	public double change_rate;
	public double signed_change_price;
	public double signed_change_rate;
	public double trade_volume;
	public double acc_trade_price;
	public double acc_trade_price_24h;
	public double acc_trade_volume;
	public double acc_trade_volume_24h;
	public double highest_52_week_price;
	public String highest_52_week_date;
	public double lowest_52_week_price;
	public String lowest_52_week_date;
	public long timestamp;
	
	public TickerVO() {}
	
	public TickerVO(String market, String trade_date, String trade_time, String trade_date_kst, String trade_time_kst,
			long trade_timestamp, double opening_price, double high_price, double low_price, double trade_price,
			double prev_closing_price, String change, double change_price, double change_rate,
			double signed_change_price, double signed_change_rate, double trade_volume, double acc_trade_price,
			double acc_trade_price_24h, double acc_trade_volume, double acc_trade_volume_24h,
			double highest_52_week_price, String highest_52_week_date, double lowest_52_week_price,
			String lowest_52_week_date, long timestamp) {
		super();
		this.market = market;
		this.trade_date = trade_date;
		this.trade_time = trade_time;
		this.trade_date_kst = trade_date_kst;
		this.trade_time_kst = trade_time_kst;
		this.trade_timestamp = trade_timestamp;
		this.opening_price = opening_price;
		this.high_price = high_price;
		this.low_price = low_price;
		this.trade_price = trade_price;
		this.prev_closing_price = prev_closing_price;
		this.change = change;
		this.change_price = change_price;
		this.change_rate = change_rate;
		this.signed_change_price = signed_change_price;
		this.signed_change_rate = signed_change_rate;
		this.trade_volume = trade_volume;
		this.acc_trade_price = acc_trade_price;
		this.acc_trade_price_24h = acc_trade_price_24h;
		this.acc_trade_volume = acc_trade_volume;
		this.acc_trade_volume_24h = acc_trade_volume_24h;
		this.highest_52_week_price = highest_52_week_price;
		this.highest_52_week_date = highest_52_week_date;
		this.lowest_52_week_price = lowest_52_week_price;
		this.lowest_52_week_date = lowest_52_week_date;
		this.timestamp = timestamp;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getTrade_date() {
		return trade_date;
	}

	public void setTrade_date(String trade_date) {
		this.trade_date = trade_date;
	}

	public String getTrade_time() {
		return trade_time;
	}

	public void setTrade_time(String trade_time) {
		this.trade_time = trade_time;
	}

	public String getTrade_date_kst() {
		return trade_date_kst;
	}

	public void setTrade_date_kst(String trade_date_kst) {
		this.trade_date_kst = trade_date_kst;
	}

	public String getTrade_time_kst() {
		return trade_time_kst;
	}

	public void setTrade_time_kst(String trade_time_kst) {
		this.trade_time_kst = trade_time_kst;
	}

	public long getTrade_timestamp() {
		return trade_timestamp;
	}

	public void setTrade_timestamp(long trade_timestamp) {
		this.trade_timestamp = trade_timestamp;
	}

	public double getOpening_price() {
		return opening_price;
	}

	public void setOpening_price(double opening_price) {
		this.opening_price = opening_price;
	}

	public double getHigh_price() {
		return high_price;
	}

	public void setHigh_price(double high_price) {
		this.high_price = high_price;
	}

	public double getLow_price() {
		return low_price;
	}

	public void setLow_price(double low_price) {
		this.low_price = low_price;
	}

	public double getTrade_price() {
		return trade_price;
	}

	public void setTrade_price(double trade_price) {
		this.trade_price = trade_price;
	}

	public double getPrev_closing_price() {
		return prev_closing_price;
	}

	public void setPrev_closing_price(double prev_closing_price) {
		this.prev_closing_price = prev_closing_price;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public double getChange_price() {
		return change_price;
	}

	public void setChange_price(double change_price) {
		this.change_price = change_price;
	}

	public double getChange_rate() {
		return change_rate;
	}

	public void setChange_rate(double change_rate) {
		this.change_rate = change_rate;
	}

	public double getSigned_change_price() {
		return signed_change_price;
	}

	public void setSigned_change_price(double signed_change_price) {
		this.signed_change_price = signed_change_price;
	}

	public double getSigned_change_rate() {
		return signed_change_rate;
	}

	public void setSigned_change_rate(double signed_change_rate) {
		this.signed_change_rate = signed_change_rate;
	}

	public double getTrade_volume() {
		return trade_volume;
	}

	public void setTrade_volume(double trade_volume) {
		this.trade_volume = trade_volume;
	}

	public double getAcc_trade_price() {
		return acc_trade_price;
	}

	public void setAcc_trade_price(double acc_trade_price) {
		this.acc_trade_price = acc_trade_price;
	}

	public double getAcc_trade_price_24h() {
		return acc_trade_price_24h;
	}

	public void setAcc_trade_price_24h(double acc_trade_price_24h) {
		this.acc_trade_price_24h = acc_trade_price_24h;
	}

	public double getAcc_trade_volume() {
		return acc_trade_volume;
	}

	public void setAcc_trade_volume(double acc_trade_volume) {
		this.acc_trade_volume = acc_trade_volume;
	}

	public double getAcc_trade_volume_24h() {
		return acc_trade_volume_24h;
	}

	public void setAcc_trade_volume_24h(double acc_trade_volume_24h) {
		this.acc_trade_volume_24h = acc_trade_volume_24h;
	}

	public double getHighest_52_week_price() {
		return highest_52_week_price;
	}

	public void setHighest_52_week_price(double highest_52_week_price) {
		this.highest_52_week_price = highest_52_week_price;
	}

	public String getHighest_52_week_date() {
		return highest_52_week_date;
	}

	public void setHighest_52_week_date(String highest_52_week_date) {
		this.highest_52_week_date = highest_52_week_date;
	}

	public double getLowest_52_week_price() {
		return lowest_52_week_price;
	}

	public void setLowest_52_week_price(double lowest_52_week_price) {
		this.lowest_52_week_price = lowest_52_week_price;
	}

	public String getLowest_52_week_date() {
		return lowest_52_week_date;
	}

	public void setLowest_52_week_date(String lowest_52_week_date) {
		this.lowest_52_week_date = lowest_52_week_date;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "TickerVO [market=" + market + ", trade_date=" + trade_date + ", trade_time=" + trade_time
				+ ", trade_date_kst=" + trade_date_kst + ", trade_time_kst=" + trade_time_kst + ", trade_timestamp="
				+ trade_timestamp + ", opening_price=" + opening_price + ", high_price=" + high_price + ", low_price="
				+ low_price + ", trade_price=" + trade_price + ", prev_closing_price=" + prev_closing_price
				+ ", change=" + change + ", change_price=" + change_price + ", change_rate=" + change_rate
				+ ", signed_change_price=" + signed_change_price + ", signed_change_rate=" + signed_change_rate
				+ ", trade_volume=" + trade_volume + ", acc_trade_price=" + acc_trade_price + ", acc_trade_price_24h="
				+ acc_trade_price_24h + ", acc_trade_volume=" + acc_trade_volume + ", acc_trade_volume_24h="
				+ acc_trade_volume_24h + ", highest_52_week_price=" + highest_52_week_price + ", highest_52_week_date="
				+ highest_52_week_date + ", lowest_52_week_price=" + lowest_52_week_price + ", lowest_52_week_date="
				+ lowest_52_week_date + ", timestamp=" + timestamp + ", toString()=" + super.toString() + "]";
	}
	
}