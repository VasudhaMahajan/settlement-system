package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.pojo.Participant;
import com.pojo.Security;

public interface ParticipantDAO {

	//Pooja
		public List<Security> getOpeningSecurities(int participantId);
		public double getOpeningFunds(int participantId);//existing balance of funds and shares
		public List<Security> getNetSecurities(int participantId);
		public double getNetFunds(int participantId);//netted value of funds and shares
		
		//Akanksha
		
		public boolean updateSecurityBalance(int participantId,List<Security> listOfSecurities);//
		public boolean updateFundBalance(int participantId,double funds);
		public boolean updateParticipant(int participantId, String emailId, String contactNumber);
		public HashMap<String,Integer> getParticipantIdMap();//should be called from servlet and added into participant object then can only other methods be called
		public boolean addParticipant(Participant participant);
		public boolean addLoginInfo(int participantId, String username, String password);
		public boolean updateLoginInfo(int participantId, String password);
		public Participant getParticipantById(int participantId);
		public Integer getLoginInfo(String username, String password); //login validation from db, will return the participant_id
		
		//Shafa
		public String getParticipantNameById(int participantId);	//For mapping clientId to its Name for UI
		public List<Participant> getAllParticipantName();
		public List<List<Integer>> perfomJoinBalanceSecurity();
		public JSONArray getCorporateActionByParticipantId(int participant_id); //do it by joining of table
		public boolean updateParticipantBalance(int ParticipantId,int SecurityId,int SecurityQty);
		
		public int getSecurityQtyByParticipantId(int participantId,int securityId);
		public HashMap<Integer, Double> getAllFunds();
		public HashMap<Integer, HashMap<Integer, Integer>> getAllSecurities();
		public HashMap<Integer, HashMap<Integer, Integer>> getNetSecurities();
		public boolean addParticipantBalance(int participantId, int securityId, int securityNetVal);
		public HashMap<Integer, Double> getNetFunds();
		public HashMap<Integer, HashMap<Integer, Integer>> getAllShortages();
		public void updateSecurityPenalty(int participantId, int securityId, double penalty);
		public void updateTotalSecurityPenalty(int participantId, double penalty);
		public HashMap<Integer, Double> getAllFundShortages();
		public void updateFundPenalty(int participantId,double fundPenalty);
		HashMap<Integer, Double> getTotalTransactionValue();
		public void updateFeeForSettlement(int participantId, double costOfSettlement);
		public boolean updateRights(int participantId,int securityId);
		public JSONObject viewFundReconciliation(int participantId);
		public JSONArray viewSecurityReconciliation(int participantId);
		public JSONObject generateCostOfSettlementReport(int participantId);
		public boolean resetParticipant();
		public boolean generateSecurityReconciliationTable();
		public JSONArray generateCostOfSettlementReportSecurities(int participantId);
	
}

