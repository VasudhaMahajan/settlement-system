package com.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.pojo.Security;

public interface SettlementSystem {
	
	//public HashMap<String,Integer> getParticipantIdMap();
	//public HashMap<String,Integer> getSecurityIdMap();
	public void performNettingProcedure();
	public JSONObject generateFundObligationReport(int participantId);
	public JSONArray generateSecurityObligationReport(int participantId);
	public Map<Integer,List> detectSecurityShortages(int participantId);
	public double detectFundShortages(int participantId);
	public boolean performSettlement(int particiapntId);//update the account balance from the netted table 
	public void handleCorporateAction();
//	public void generateCostofSelletmentReceipt(int participantId);
	
	public void performSettlement();
	void generateCostofSelletmentReceipt();
	
}

