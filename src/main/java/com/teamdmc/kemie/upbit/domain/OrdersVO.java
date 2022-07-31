package com.teamdmc.kemie.upbit.domain;

import java.util.Date;

public class OrdersVO {
	
	    public String uuid;
	    public String side;
	    public String ord_type;
	    public String price;
	    public String avg_price;
	    public String state;
	    public String market;
	    public Date created_at;
	    public String volume;
	    public String remaining_volume;
	    public String reserved_fee;
	    public String remaining_fee;
	    public String paid_fee;
	    public String locked;
	    public String executed_volume;
	    public int trades_count;
	    
	    public OrdersVO() {}
	    
		public OrdersVO(String uuid, String side, String ord_type, String price, String avg_price, String state,
				String market, Date created_at, String volume, String remaining_volume, String reserved_fee,
				String remaining_fee, String paid_fee, String locked, String executed_volume, int trades_count) {
			super();
			this.uuid = uuid;
			this.side = side;
			this.ord_type = ord_type;
			this.price = price;
			this.avg_price = avg_price;
			this.state = state;
			this.market = market;
			this.created_at = created_at;
			this.volume = volume;
			this.remaining_volume = remaining_volume;
			this.reserved_fee = reserved_fee;
			this.remaining_fee = remaining_fee;
			this.paid_fee = paid_fee;
			this.locked = locked;
			this.executed_volume = executed_volume;
			this.trades_count = trades_count;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getSide() {
			return side;
		}

		public void setSide(String side) {
			this.side = side;
		}

		public String getOrd_type() {
			return ord_type;
		}

		public void setOrd_type(String ord_type) {
			this.ord_type = ord_type;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getAvg_price() {
			return avg_price;
		}

		public void setAvg_price(String avg_price) {
			this.avg_price = avg_price;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getMarket() {
			return market;
		}

		public void setMarket(String market) {
			this.market = market;
		}

		public Date getCreated_at() {
			return created_at;
		}

		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}

		public String getVolume() {
			return volume;
		}

		public void setVolume(String volume) {
			this.volume = volume;
		}

		public String getRemaining_volume() {
			return remaining_volume;
		}

		public void setRemaining_volume(String remaining_volume) {
			this.remaining_volume = remaining_volume;
		}

		public String getReserved_fee() {
			return reserved_fee;
		}

		public void setReserved_fee(String reserved_fee) {
			this.reserved_fee = reserved_fee;
		}

		public String getRemaining_fee() {
			return remaining_fee;
		}

		public void setRemaining_fee(String remaining_fee) {
			this.remaining_fee = remaining_fee;
		}

		public String getPaid_fee() {
			return paid_fee;
		}

		public void setPaid_fee(String paid_fee) {
			this.paid_fee = paid_fee;
		}

		public String getLocked() {
			return locked;
		}

		public void setLocked(String locked) {
			this.locked = locked;
		}

		public String getExecuted_volume() {
			return executed_volume;
		}

		public void setExecuted_volume(String executed_volume) {
			this.executed_volume = executed_volume;
		}

		public int getTrades_count() {
			return trades_count;
		}

		public void setTrades_count(int trades_count) {
			this.trades_count = trades_count;
		}

		@Override
		public String toString() {
			return "OrdersVO [uuid=" + uuid + ", side=" + side + ", ord_type=" + ord_type + ", price=" + price
					+ ", avg_price=" + avg_price + ", state=" + state + ", market=" + market + ", created_at="
					+ created_at + ", volume=" + volume + ", remaining_volume=" + remaining_volume + ", reserved_fee="
					+ reserved_fee + ", remaining_fee=" + remaining_fee + ", paid_fee=" + paid_fee + ", locked="
					+ locked + ", executed_volume=" + executed_volume + ", trades_count=" + trades_count
					+ ", toString()=" + super.toString() + "]";
		}
}