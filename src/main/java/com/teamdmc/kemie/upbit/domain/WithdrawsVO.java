package com.teamdmc.kemie.upbit.domain;

import java.util.Date;

public class WithdrawsVO {
	
	    public String type;
	    public String uuid;
	    public String currency;
	    public String txid;
	    public String state;
	    public Date created_at;
	    public Date done_at;
	    public String amount;
	    public String fee;
	    public String transaction_type;
	    
	    public WithdrawsVO() {}
	    
		public WithdrawsVO(String type, String uuid, String currency, String txid, String state, Date created_at,
				Date done_at, String amount, String fee, String transaction_type) {
			super();
			this.type = type;
			this.uuid = uuid;
			this.currency = currency;
			this.txid = txid;
			this.state = state;
			this.created_at = created_at;
			this.done_at = done_at;
			this.amount = amount;
			this.fee = fee;
			this.transaction_type = transaction_type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getCurrency() {
			return currency;
		}

		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public String getTxid() {
			return txid;
		}

		public void setTxid(String txid) {
			this.txid = txid;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public Date getCreated_at() {
			return created_at;
		}

		public void setCreated_at(Date created_at) {
			this.created_at = created_at;
		}

		public Date getDone_at() {
			return done_at;
		}

		public void setDone_at(Date done_at) {
			this.done_at = done_at;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getFee() {
			return fee;
		}

		public void setFee(String fee) {
			this.fee = fee;
		}

		public String getTransaction_type() {
			return transaction_type;
		}

		public void setTransaction_type(String transaction_type) {
			this.transaction_type = transaction_type;
		}

		@Override
		public String toString() {
			return "WithdrawsVO [type=" + type + ", uuid=" + uuid + ", currency=" + currency + ", txid=" + txid
					+ ", state=" + state + ", created_at=" + created_at + ", done_at=" + done_at + ", amount=" + amount
					+ ", fee=" + fee + ", transaction_type=" + transaction_type + ", toString()=" + super.toString()
					+ "]";
		}
	 	    
}