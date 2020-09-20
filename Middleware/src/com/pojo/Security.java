package com.pojo;

public class Security {

	

	String securityName;
	int securityId;	
	int securityQuantity;
	int corporateAction=0;
	int corporateActionRatio=0;
	//int performRights=0;
	
	
	public Security() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Security(String securityName, int securityId) {
		super();
		this.securityName = securityName;
		this.securityId = securityId;
	}



	public Security(String securityName, int securityId, int securityQuantity) {
		super();
		this.securityName = securityName;
		this.securityId = securityId;
		this.securityQuantity = securityQuantity;
	}
	
	public Security(String securityName, int securityId, int securityQuantity, int corporateAction,
			int corporateActionRatio) {
		super();
		this.securityName = securityName;
		this.securityId = securityId;
		this.securityQuantity = securityQuantity;
		this.corporateAction = corporateAction;
		this.corporateActionRatio = corporateActionRatio;
		//this.performRights = performRights;
	}

	@Override
	public String toString() {
		return "Security [securityName=" + securityName + ", securityId=" + securityId + ", securityQuantity="
				+ securityQuantity + ", corporateActions=" + corporateAction + ", corporateActionRatio="
				+ corporateActionRatio + "]";
	}
	
	public String getSecurityName() {
		return securityName;
	}
	public void setSecurityName(String securityName) {
		this.securityName = securityName;
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
	public int getCorporateAction() {
		return corporateAction;
	}
	public void setCorporateActions(int corporateAction) {
		this.corporateAction = corporateAction;
	}
	public int getCorporateActionRatio() {
		return corporateActionRatio;
	}
	public void setCorporateActionRatio(int corporateActionRatio) {
		this.corporateActionRatio = corporateActionRatio;
	}
//	public int getPerformRights() {
//		return performRights;
//	}
//	public void setPerformRights(int performRights) {
//		this.performRights = performRights;
//	}
	
	
	
}
