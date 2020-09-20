package com.test;

import javax.ws.rs.FormParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.dao.SecurityDAO;
import com.dao.SecurityDAOImpl;
import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.dao.TransactionNewImpl;
import com.logic.SettlementSystem;
import com.logic.SettlementSystemImpl;
import com.pojo.Participant;
import com.pojo.Security;
import com.pojo.Transaction;

import java.util.HashMap;
import java.util.List;

import javax.json.JsonObject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Application;


import org.json.simple.*;

@Path("/hello")
public class Hello {

	

	
	@GET
	@Path("/getAllCorporateActions")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray getCorporateActions()
	{		
		TransactionNewImpl tranObj = new TransactionNewImpl();
		JSONArray arr =tranObj.getCorporateActions();
		
		return arr;
	}

	@POST
	@Path("/updateTransaction")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN})
	public int update(Transaction json) 
	{
		System.out.println("in service");
		System.out.println(json);
		TransactionDAO dao = new TransactionDAOImpl();
		boolean val =dao.updateTransaction(json);
		
		System.out.println("Transaction added:"+val);
		
		return 1;
	}
	
	@GET
	@Path("/costOfSettlementReport")
	@Produces({MediaType.TEXT_PLAIN})
	public int costOfSettlement() 
	{
		SettlementSystem settlementSystem = new SettlementSystemImpl();
		settlementSystem.generateCostofSelletmentReceipt();
		
		return 1;
	}
	
	
	/*@POST
	@Path("/validateLogin")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public int validateLogin(JsonObject json) 
	{
		String username = json.getString("username");
		String password = json.getString("password");
		
		if(username.equals("admin") && password.equals("admin"))
			return 1;
		else 
			return 0;
//		ParticipantDAO dao = new ParticipantDaoImpl();
//		int check = dao.getLoginInfo(username, password);
//		if(check == -1)
//			return -1;
//		else
//			return check;
		
		*/

		@GET
		@Path("/getNet")
		@Produces(MediaType.APPLICATION_JSON)
		public JSONArray getNet()
		{		
			SettlementSystem settlementSystem = new SettlementSystemImpl();
			settlementSystem.performNettingProcedure();
			TransactionNewImpl obb = new TransactionNewImpl();
			JSONArray arr = new JSONArray();
			arr =obb.getNettingResult();
			//System.out.println("netted:"+arr);
			return arr;
		}
		
		@GET
		@Path("/performSettlement")
		@Produces(MediaType.APPLICATION_JSON)
		public int performSettlement()
		{		
			SettlementSystem settlementSystem = new SettlementSystemImpl();
			settlementSystem.performSettlement();
			
			return 1;
		}
		
		@POST
		@Path("/deleteTransaction")
		@Consumes({MediaType.APPLICATION_JSON})
		@Produces({MediaType.TEXT_PLAIN})
		public int deleteTransaction(JsonObject json) 
		{
			
			int tranId = json.getInt("transactionId");
			System.out.println(tranId);
			TransactionDAO tran = new TransactionDAOImpl();
			tran.deleteTransaction(tranId);
			
			System.out.println("Deleted entry");
			return 1;
		}
		
		
		@POST
		@Path("/addTransaction")
		@Consumes({MediaType.APPLICATION_JSON})
		@Produces({MediaType.TEXT_PLAIN})
		public int addNewTransaction(Transaction json) 
		{
			System.out.println(json);
			TransactionDAO dao = new TransactionDAOImpl();
			boolean val =dao.addTransaction(json);
			
			System.out.println("Transaction added:"+val);
			
			return 1;
		}
		
	@GET
	@Path("/getAllTransactions")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Transaction> getAllTransactions() 
	{
		TransactionDAO tran = new TransactionDAOImpl();
		List<Transaction> listOfTranssaction =tran.getAllTransaction();
		
		return listOfTranssaction;
	}
	
	
	
//	@GET
//	@Path("/getAllNames")
//	@Produces({MediaType.APPLICATION_JSON})
//	public HashMap<Integer,String> getAllNames() 
//	{
//		ParticipantDAO dao = new ParticipantDaoImpl();
//		HashMap<Integer,String> hashMap =  dao.getAllParticipantName();
//		
//		return hashMap;
//	}


	
	@GET
	@Path("/getAllSecurities")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Security> getAllSecurities() 
	{
		SecurityDAO dao = new SecurityDAOImpl();
		List<Security> list = dao.getAllSecurityName();
		System.out.println(list);
		return list;
	}
	
	@GET
	@Path("/getAllParticipants")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Participant> getParticipantById() 
	{
		ParticipantDAO dao= new ParticipantDaoImpl();
		List<Participant> participant=dao.getAllParticipantName();
		System.out.println("participant"+participant);
		return participant;
		
	}
	
	@GET
	@Path("/getSecuritiesShortageResult")
	@Produces({MediaType.APPLICATION_JSON})
	public JSONArray getSecurityShortageValues()
	{
		TransactionNewImpl tranObj = new TransactionNewImpl();
		JSONArray arr =tranObj.getFundShortageResult();
		
		return arr;
	}
	
	@GET
	@Path("/getFundShortageResult")
	@Produces({MediaType.APPLICATION_JSON})
	public JSONArray getFundShortageValues()
	{
		TransactionNewImpl tranObj = new TransactionNewImpl();
		JSONArray arr =tranObj.getFundShortageResult();
		
		return arr;
	}
}
