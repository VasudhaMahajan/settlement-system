package com.pojo;

public class Transaction {
	
	private int transactionid;
	private int SSIN_Id;
	private int sellerId;
	private String sellerName;
	private int buyerId;
	private String buyerName;
	private int securityId;
	private String securityName;
	private int securityQuantity; 
	private double SecurityRate;
	private double transactionAmount;
	
	

	@Override
	public String toString() {
		return "Transaction [TransactionId=" + transactionid + ", SSIN_Id=" + SSIN_Id + ", sellerId=" + sellerId
				+ ", sellerName=" + sellerName + ", buyerId=" + buyerId + ", buyerName=" + buyerName + ", securityId="
				+ securityId + ", securityName=" + securityName + ", securityQuantity=" + securityQuantity
				+ ", SecurityRate=" + SecurityRate + ", transactionAmount=" + transactionAmount + "]";
	}
	
	public Transaction()
	{
		
	}


	


	public int getTransactionid() {
		return transactionid;
	}





	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}





	public String getSellerName() {
		return sellerName;
	}


	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}


	public String getBuyerName() {
		return buyerName;
	}


	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}


	public String getSecurityName() {
		return securityName;
	}


	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}


	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}


	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}


	public int getSSIN_Id() {
		return SSIN_Id;
	}


	public Transaction(int transactionId, int sSIN_Id, int sellerId, String sellerName, int buyerId, String buyerName,
			int securityId, String securityName, int securityQuantity, double securityRate, double transactionAmount) {
		super();
		transactionid = transactionId;
		SSIN_Id = sSIN_Id;
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.buyerId = buyerId;
		this.buyerName = buyerName;
		this.securityId = securityId;
		this.securityName = securityName;
		this.securityQuantity = securityQuantity;
		SecurityRate = securityRate;
		this.transactionAmount = transactionAmount;
	}


	public void setSSIN_Id(int sSIN_Id) {
		SSIN_Id = sSIN_Id;
	}


	public Integer getBuyerId() {
		return buyerId;
	}


	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}


	public Integer getSellerId() {
		return sellerId;
	}


	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}


	public int getSecurityId() {
		return securityId;
	}


	public void setSecurityId(int securityId) {
		this.securityId = securityId;
	}


	public int getSecurityQuantity() {
		return securityQuantity;
	}


	public void setSecurityQuantity(int securityQuantity) {
		this.securityQuantity = securityQuantity;
	}


	public double getSecurityRate() {
		return SecurityRate;
	}


	public void setSecurityRate(double securityRate) {
		this.SecurityRate = securityRate;
	}


	public double getTransactionAmount() {
		return transactionAmount;
	}


	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}


	public Transaction(int transactionId, int sSIN_Id, int sellerId, int buyerId, int securityId,
			int securityQuantity, double securityRate, double transactionAmount) {
		super();
		transactionid = transactionId;
		SSIN_Id = sSIN_Id;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.securityId = securityId;
		this.securityQuantity = securityQuantity;
		this.SecurityRate = securityRate;
		this.transactionAmount = transactionAmount;
	}


	
	
	
	
	

}
