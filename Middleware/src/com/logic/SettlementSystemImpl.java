package com.logic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dao.Connect;
import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.dao.SecurityDAO;
import com.dao.SecurityDAOImpl;
import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.pojo.Participant;
import com.pojo.Security;
import com.pojo.Transaction;

public class SettlementSystemImpl implements SettlementSystem {
	
	static double commissionRate=2.5;
	static double fundBorrowingRate=7.3;
	
	public int findSecurity(List<Security> l1, Security s2)
	{
		for(int i=0;i<l1.size();i++)
		{
			if(l1.get(i).getSecurityId()==s2.getSecurityId())
				return i;
		}
		
		return -1;
	}

	public double findSimpleInterest(int shortage, double marketPrice, double penaltyRate)
	{
		double val = (double) 2/365; 
		//System.out.println(val);
		double actualRate = penaltyRate * val;
		//System.out.println(actualRate);
		double totAmount = shortage * marketPrice * actualRate;
		//System.out.println(totAmount);
		return totAmount;
	}
	
	@Override
	public HashMap<Integer,List> detectSecurityShortages(int participantId) {
	
		ParticipantDAO participantDAO = new ParticipantDaoImpl();
		List<Security> l1 =participantDAO.getOpeningSecurities(participantId); //opening SHARE balance
		List<Security> l2 =participantDAO.getNetSecurities(participantId); //net SHARE balance
		
		
		HashMap<Integer,List> hashMap = new HashMap();
		List l3;
		
		System.out.println("OPENING BALANCES");
		for(Security s: l1)
		{
			System.out.println(s.toString());
		}
		
		System.out.println("net BALANCES");
		for(Security s: l2)
		{
			System.out.println(s.toString());
		}
		
		//System.out.println("opening share balance list:"+l1);
		
		
		for(int i=0;i<l2.size();i++)
		{
			int val =findSecurity(l1, l2.get(i)); //find index
			System.out.println("INDEX OF SECURITY IS:"+val);
			if(val>=0)
			{
				//System.out.println("match found");
				
				if(l2.get(i).getSecurityQuantity()<0)
				{
					//System.out.println("need to give shares");
					
					if(l1.get(val).getSecurityQuantity()<(l2.get(i).getSecurityQuantity()*-1))
					{
						
						int shortage = (l2.get(i).getSecurityQuantity()*-1) - l1.get(val).getSecurityQuantity();
						System.out.println("SHARE Shortage detected:"+shortage);
						
						double fineForShortage = findSimpleInterest(shortage, 23.56, 7.0);
						
						l3 = new ArrayList();
						l3.add(shortage);
						l3.add(fineForShortage);
						
						hashMap.put(l1.get(val).getSecurityId(), l3);
						
					}
					else
					{
						System.out.println("No shortage FOR SHARES");
					}
				}
				else
				{
					System.out.println("NO chance for shortage");
				}
				
			}
			else
			{
				System.out.println("no match for index");
			}
		}
		
		System.out.println(hashMap);
		
		
		
		if(hashMap.isEmpty())
			return null;
		else
			return hashMap;
	}

	@Override
	public boolean performSettlement(int particiapntId) {
		
		ParticipantDAO participantDAO = new ParticipantDaoImpl();
		List<Security> l1 = participantDAO.getOpeningSecurities(particiapntId); //opening balance
		List<Security> l2 = participantDAO.getNetSecurities(particiapntId); //net balance
		

		//SHARE UPDATES
		for(int i=0;i<l2.size();i++)
		{
			int val =findSecurity(l1, l2.get(i));
			if(val>=0)
			{
				//System.out.println("match found");
				
				if(l2.get(i).getSecurityQuantity()<0)
				{
					//System.out.println("need to give shares");
					l1.get(val).setSecurityQuantity(l1.get(val).getSecurityQuantity()-((l2.get(i).getSecurityQuantity()*-1)));
					//System.out.println("new value"+l1.get(val).getSecurityQuantity());
				}
				else
				{
					//System.out.println("need to add shares");
					l1.get(val).setSecurityQuantity(l1.get(val).getSecurityQuantity()+l2.get(i).getSecurityQuantity());
					//System.out.println("new value"+l1.get(val).getSecurityQuantity());
				}
				
			}
			else
			{
				System.out.println("No such entry");
			}
		}
		
	//	System.out.println("Updated share values");
		
	/*	for(Security s : l1)
		{
			System.out.println();
			System.out.print(s.getSecurityId());
			System.out.print(s.getSecurityName());
			System.out.print(s.getSecurityQuantity());
			System.out.println();
		}*/
		
		//FUND UPDATES
		double balanceFund =participantDAO.getOpeningFunds(particiapntId);
		double netFund = participantDAO.getNetFunds(particiapntId);
		
		double finalFunds = balanceFund - netFund;
		for(Security security :l1)
		{
			participantDAO.updateParticipantBalance(particiapntId, security.getSecurityId(),security.getSecurityQuantity());
		}
		
		participantDAO.updateFundBalance(particiapntId, finalFunds);
		return true;
		
	}

	@Override
	public double detectFundShortages(int participantId) {
		
		ParticipantDAO participantDAO = new ParticipantDaoImpl();
		double balanceFund =participantDAO.getOpeningFunds(participantId);
		double netFund = participantDAO.getNetFunds(participantId);
		double fundShortage = 0;
		if(netFund<0)
		{
			System.out.println("participant HAS negative obligation for funds");
			
			if((balanceFund-(netFund*(-1)))<0)
			{
				fundShortage =(netFund*(-1)-balanceFund);
				System.out.println("Shortage detedted:"+fundShortage);
			}
		}
		else
		{
			System.out.println("positive obligation for funds");
		}
		
		
		return fundShortage;
	}

	@Override
	public JSONObject generateFundObligationReport(int participantId) {
	
		ParticipantDAO participantDAO = new ParticipantDaoImpl();
		Participant par =participantDAO.getParticipantById(participantId);
		//par.getParticipantName(participantId);
		
		List<Security> list =participantDAO.getNetSecurities(participantId);
		double netFunds = participantDAO.getNetFunds(participantId);
		JSONObject obj=new JSONObject();
		obj.put("CLEARING_MEMBER_ID", par.getParticipantId());
		obj.put("CLEARING_MEMBER_NAME", par.getParticipantName());
		obj.put("CONTACT_NUMBER", par.getContactNumber());
		
		
		JSONArray jsonarray=new JSONArray();
		for(Security s : list)
		{
			JSONObject objectElement=new JSONObject();

			objectElement.put("SECURITY_ID", s.getSecurityId());
			objectElement.put("SECURITY_NAME", s.getSecurityName());
			objectElement.put("SECURITY_QUANTITY", s.getSecurityQuantity());
			
			jsonarray.add(objectElement);
		}
		obj.put("SHARE_OBLIGATION", jsonarray);
		obj.put("TOTAL_FUND_OBLIGATION", netFunds);
		
		return obj;
	}
	
	@Override
	public JSONArray generateSecurityObligationReport(int participantId) {
	
		ParticipantDAO participantDAO = new ParticipantDaoImpl();
		Participant par =participantDAO.getParticipantById(participantId);
		
		List<Security> list =participantDAO.getNetSecurities(participantId);
		double netFunds = participantDAO.getNetFunds(participantId);
		
		JSONArray jsonarray=new JSONArray();
		for(Security s : list)
		{
			JSONObject objectElement=new JSONObject();

			objectElement.put("SECURITY_ID", s.getSecurityId());
			objectElement.put("SECURITY_NAME", s.getSecurityName());
			objectElement.put("SECURITY_QUANTITY", s.getSecurityQuantity());
			
			jsonarray.add(objectElement);
		}
		
		
		return jsonarray;
	}


	@Override
	public void performNettingProcedure() {
		// TODO Auto-generated method stub
		
		//Reseting for next day
		ParticipantDAO participantDAO = new ParticipantDaoImpl();
		if(participantDAO.resetParticipant()) System.out.println("Participant reset successfully");
		else System.out.println("Participant reset unsuccessful");
		if(participantDAO.generateSecurityReconciliationTable())
			System.out.println("reconciliation table for security half filled successful");
		else
			System.out.println("reconciliation table for security half filled unsuccessful");
		
				try {
					HashMap<Integer, HashMap<Integer, Integer>> securitiesNettingMap =new HashMap<>();
					HashMap<Integer, Double> fundsNettingMap=new HashMap<>();
					TransactionDAO transactionDAO=new TransactionDAOImpl();
					List<Transaction> transactions=transactionDAO.getAllTransaction();
					HashMap<Integer, Integer> map;
					//System.out.println("Transactions::");
					//System.out.println(transactions);
					for(Transaction transaction:transactions) {						
						map=null;
						if(securitiesNettingMap.containsKey(transaction.getBuyerId())) {
							map=(securitiesNettingMap.get(transaction.getBuyerId()));
							fundsNettingMap.replace(transaction.getBuyerId(),fundsNettingMap.get(transaction.getBuyerId())-transaction.getTransactionAmount());
							if(map.containsKey(transaction.getSecurityId())) {
								map.replace(transaction.getSecurityId(), map.get(transaction.getSecurityId())+transaction.getSecurityQuantity());
							}else {
								map.put(transaction.getSecurityId(), transaction.getSecurityQuantity());
							}
							map=null;
						}else {
							map=new HashMap<>();
							fundsNettingMap.put(transaction.getBuyerId(), (-1)*transaction.getTransactionAmount());
							map.put(transaction.getSecurityId(), transaction.getSecurityQuantity());
							securitiesNettingMap.put(transaction.getBuyerId(), map);
							map=null;
						}
						
						if(securitiesNettingMap.containsKey(transaction.getSellerId())) {
							map=(securitiesNettingMap.get(transaction.getSellerId()));
							fundsNettingMap.replace(transaction.getSellerId(),fundsNettingMap.get(transaction.getSellerId())+transaction.getTransactionAmount());
							if(map.containsKey(transaction.getSecurityId())) {
								map.replace(transaction.getSecurityId(), map.get(transaction.getSecurityId())-transaction.getSecurityQuantity());
							}else {
								map.put(transaction.getSecurityId(), (-1)*transaction.getSecurityQuantity());
							}
							map=null;
						}else {
							map=new HashMap<>();
							fundsNettingMap.put(transaction.getSellerId(), transaction.getTransactionAmount());
							map.put(transaction.getSecurityId(), (-1)*transaction.getSecurityQuantity());
							securitiesNettingMap.put(transaction.getSellerId(), map);
							map=null;
						}	
						//System.out.println(securitiesNettingMap);
					}	
					transactionDAO.truncateNettingTables();
					transactionDAO.generateNettingTable(securitiesNettingMap);
					transactionDAO.generateNettingFundsTable(fundsNettingMap);
					
					///	transactionDAO.updateFunds(fundsNettingMap);
					System.out.println("Final   map:"+securitiesNettingMap);
					System.out.println("Final   map:"+fundsNettingMap);
			}	
				catch (NullPointerException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
		
	}

	@Override
	public void handleCorporateAction() {
		
		ParticipantDAO dao = new ParticipantDaoImpl();
		List<List<Integer>> balanceSecurityJoin=dao.perfomJoinBalanceSecurity();	//make performJoin.. func in Dao
		//The internal list contains--
		//"p.participant_id,s.security_id,s.corporate_action,s.corporate_action_ratio,p.security_quantity,p.perform_rights,s.default_market_price\r\n" + 
		SecurityDAO securityDAO=new SecurityDAOImpl();
		HashMap<Integer, Double> dmpMap=securityDAO.getdefaultMarketPrices();
		
		for(List<Integer> list:balanceSecurityJoin) 
		{
			int corporateAction=list.get(2);
			
			if(corporateAction==1)//Stock dividends
			{
				System.out.println("its stock_dividend");
				int updateqty=Math.round((float)list.get(4)/list.get(3));
				System.out.println("updateQTY="+list.get(0)+"  "+list.get(1)+"  "+updateqty);
				list.set(4, list.get(4)+updateqty);
				if(dao.updateParticipantBalance(list.get(0), list.get(1), list.get(4))) {
					System.out.println("corporate balance updated for participant:"+list.get(0));
				}
				else {
					System.out.println("corporate balance balance updation for participant:"+list.get(0)+" failed");
				}
				
			}
			else if(corporateAction==2)//Stock split
			{
				int updateqty=list.get(4)*list.get(3);
				list.set(4, updateqty);
				if(dao.updateParticipantBalance(list.get(0), list.get(1), list.get(4))) {
					System.out.println("corporate balance updated for participant:"+list.get(0));
				}
				else {
					System.out.println("corporate balance balance updation for participant:"+list.get(0)+" failed");
				}
			}
			else if(corporateAction==3)//Rights
			{
				if(list.get(5)==1)// 1 means perform the rights
				{					
					double dmp=dmpMap.get(list.get(1));
					
					//updating qty
					int updateqty=Math.round((float)list.get(4)/list.get(3));
					list.set(4, list.get(4)+updateqty);
					
					//double updateFund = updateqty*randomDouble;
					double updateFund = updateqty*dmp;  //calculating on default market price
					
					
					
					if((dao.getOpeningFunds(list.get(0))-updateFund)>0 && dao.updateFundBalance(list.get(0),dao.getOpeningFunds(list.get(0))-updateFund))
					{
						if(dao.updateParticipantBalance(list.get(0), list.get(1), list.get(4))) {
							System.out.println("corporate balance updated for participant:"+list.get(0));
						}
						else {
							System.out.println("corporate balance balance updation for participant:"+list.get(0)+" failed");
						}
						System.out.println("Rights performed successfully");
					}
					else
					{
						System.out.println("Rights failure due to insufficient fund balance");
					}
					
				}
			}
		}
	}


//	@Override
//	public void generateCostofSelletmentReceipt(int participantId) {
//		// TODO Auto-generated method stub		
//		ParticipantDAO dao = new ParticipantDaoImpl();		
//		Map<Integer, List> map =detectSecurityShortages(participantId);
//		double totalShortagePenalty=0.0f;
//		if(map.size()!=0) 
//		{
//			System.out.println("--------------Shortages---------------");
//			for (Map.Entry<Integer,List> entry : map.entrySet())
//			{
//				System.out.println("SecurityId="+entry.getKey());
//				System.out.println("ShortageAmt="+entry.getValue().get(0));
//				System.out.println("ShortagePenalty="+entry.getValue().get(1));
//				totalShortagePenalty=totalShortagePenalty+(double)entry.getValue().get(1);
//			}
//		}
//		System.out.println("Total Security Shortage Penalty="+totalShortagePenalty);
//		System.out.println("Total Fund Shortage penalty="+detectFundShortages(participantId));
//		
//		
//		Map<String, List<Integer>> corporateActionSecId = dao.getCorporateActionByParticipantId(participantId);
//		if(corporateActionSecId.size()!=0)
//		{
//			System.out.println("--------Corporate Actions Executed----------");
//			for (Map.Entry<String,List<Integer>> entry : corporateActionSecId.entrySet())
//			{
//				//s.security_id,s.corporate_action,s.corporate_action_ratio\r\n
//				System.out.println("SecurityName="+entry.getKey());
//				System.out.println("CorporateAction="+entry.getValue().get(0));
//				System.out.println("CorporateActionRatio="+entry.getValue().get(1));
//			}
//		}
//		
//		//Cost of settlement 
//		TransactionDAO dao2 = new TransactionDAOImpl();
//		int transactionListSize = dao2.getTransactionByParticipantId(participantId).size();
//		double costOfSettlement = transactionListSize*commissionRate;
//		System.out.println("Cost of settlement = "+costOfSettlement);
//	}

	@Override
	public void performSettlement() {
		ParticipantDAO participantDAO = new ParticipantDaoImpl();
		
		
		//settling the securities
		HashMap<Integer, HashMap<Integer, Integer>> securitiesBalanceMap=participantDAO.getAllSecurities(); //opening balance
		HashMap<Integer, HashMap<Integer, Integer>> securitiesNettingMap =participantDAO.getNetSecurities(); //net balance
		boolean added = false;
		int participantId=-1;
		int securityId=-1;
		int securityNetVal=0;
		String INSERT_NETTING;
		Connection connection = Connect.connection;
	    PreparedStatement ps;   
		for(Map.Entry participant: securitiesNettingMap.entrySet())
		{
			participantId =(int) participant.getKey();
			HashMap<Integer,Integer> nettingMap =(HashMap<Integer, Integer>) participant.getValue();
			HashMap<Integer,Integer> balanceMap =(HashMap<Integer, Integer>) securitiesBalanceMap.get(participantId);	
	
			for(Map.Entry netSecurity: nettingMap.entrySet())
			{
				securityId =(int)netSecurity.getKey();
				securityNetVal =(int) netSecurity.getValue();
				if(balanceMap.containsKey(securityId)) {
					participantDAO.updateParticipantBalance(participantId, securityId, securityNetVal+balanceMap.get(securityId));
				}
				else {
					participantDAO.addParticipantBalance(participantId, securityId, securityNetVal);
				}
			}
		}
		
		
		//settling funds
		HashMap<Integer, Double> balanceFund=participantDAO.getAllFunds();
		HashMap<Integer, Double> nettingFund=participantDAO.getNetFunds();

		for(Map.Entry participant: nettingFund.entrySet())
		{
			participantId=(int)participant.getKey();
			participantDAO.updateFundBalance(participantId, balanceFund.get(participantId)+(double)participant.getValue());
		}
		
		
		generateCostofSelletmentReceipt();
		
		///performing corportae Actions
		
		handleCorporateAction();
		
		///generating CSR in the database
	}




	@Override
	public void generateCostofSelletmentReceipt() {
		//calculating penalty for shortages
		ParticipantDAO participantDAO = new ParticipantDaoImpl();
		SecurityDAO securityDAO=new SecurityDAOImpl();
		HashMap<Integer, HashMap<Integer, Integer>> shortagesMap=participantDAO.getAllShortages();
		HashMap<Integer, Double> fundShortagesMap=participantDAO.getAllFundShortages();
		HashMap<Integer, Double> borrowingRates=securityDAO.getBorrowingRates();
		HashMap<Integer, Double> defaultMarketPrices=securityDAO.getdefaultMarketPrices();
		//HashMap<Integer, Double>totalTransactionValue=participantDAO.getTotalTransactionValue();// map of participant and totalTransactionValue
		HashMap<Integer, HashMap<Integer, Integer>> penaltyMap=new HashMap<>();
		double penalty=0.0D,totalPenalty=0.0D;
		int securityId;
		int shortageAmount;
		double borrowingRate;
		double dmp; //defult market price
		double fundPenalty=0.0D;
		double costOfSettlement=0.0D;
		int participantId;
		for(Map.Entry participant: shortagesMap.entrySet())
		{
			totalPenalty=0.0D;
			participantId=(int)participant.getKey();
			HashMap<Integer, Integer> securityMap=(HashMap<Integer, Integer>) participant.getValue();
			for(Map.Entry security: securityMap.entrySet())
			{
				penalty=0.0D;
				securityId=(int)security.getKey();
				shortageAmount=(int)security.getValue();
				borrowingRate=borrowingRates.get(securityId);
				dmp=defaultMarketPrices.get(securityId);
				System.out.println("securityid:"+securityId+" dmp:"+dmp+" br:"+borrowingRate+" shortage:"+shortageAmount);
				penalty=dmp*borrowingRate*shortageAmount*2/365/100;
				participantDAO.updateSecurityPenalty(participantId,securityId,penalty);
				totalPenalty+=penalty;
				System.out.println(participantId+" "+securityId+" "+penalty);
			}
			System.out.println(+participantId+" total Penalty:"+totalPenalty);
			participantDAO.updateTotalSecurityPenalty(participantId,totalPenalty);
		}
		
		for(Map.Entry participant: fundShortagesMap.entrySet()) {
			participantId=(int)participant.getKey();
			fundPenalty=fundBorrowingRate*fundShortagesMap.get(participantId)*2/365/100;
			participantDAO.updateFundPenalty(participantId,fundPenalty);
		}
							
			//costOfSettlement = totalTransactionValue.get(participantId)*commissionRate/100;
			//participantDAO.updateFeeForSettlement(participantId,costOfSettlement);
			//System.out.println("Cost of settlement = "+costOfSettlement);
		
	}
}