package com.pojo;

import java.util.List;

public class Participant {
	
	private int participantId;
	private String participantName;
	private String emailId;
	private String contactNumber;
	private float funds;
	private float feeForSettlement;
	private String imagePath;
	private List<Security> listOfSecurities;
	
	
	public Participant(int participantId, String participantName, String emailId, String contactNumber, float funds,
			float feeForSettlement, List<Security> listOfSecurities) {
		super();
		this.participantId = participantId;
		this.participantName = participantName;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.funds = funds;
		this.feeForSettlement = feeForSettlement;
		this.listOfSecurities = listOfSecurities;
	}


	public Participant(int participantId, String participantName, String emailId, String contactNumber, float funds,
			float feeForSettlement, String imagePath, List<Security> listOfSecurities) {
		super();
		this.participantId = participantId;
		this.participantName = participantName;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.funds = funds;
		this.feeForSettlement = feeForSettlement;
		this.imagePath = imagePath;
		this.listOfSecurities = listOfSecurities;
	}
	public Participant(int participantId, String participantName, String emailId, String contactNumber, String imagePath) {
		super();
		this.participantId = participantId;
		this.participantName = participantName;
		this.emailId = emailId;
		this.contactNumber = contactNumber;
		this.imagePath = imagePath;
		
	}

	public Participant(int participantId, String participantName) {
		super();
		this.participantId = participantId;
		this.participantName = participantName;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public int getParticipantId() {
		return participantId;
	}


	public void setParticipantId(int participantId) {
		this.participantId = participantId;
	}


	@Override
	public String toString() {
		return "Participant [participantId=" + participantId + ", participantName=" + participantName + ", emailId="
				+ emailId + ", contactNumber=" + contactNumber + ", funds=" + funds + ", feeForSettlement="
				+ feeForSettlement + ", listOfSecurities=" + listOfSecurities + "]";
	}


	public String getParticipantName() {
		return participantName;
	}


	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getContactNumber() {
		return contactNumber;
	}


	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}


	public float getFunds() {
		return funds;
	}


	public void setFunds(float funds) {
		this.funds = funds;
	}


	public float getFeeForSettlement() {
		return feeForSettlement;
	}


	public void setFeeForSettlement(float feeForSettlement) {
		this.feeForSettlement = feeForSettlement;
	}


	public List<Security> getListOfSecurities() {
		return listOfSecurities;
	}


	public void setListOfshares(List<Security> listOfSecurities) {
		this.listOfSecurities = listOfSecurities;
	}

	
	
	
	

}
