package com.test;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.*;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.logic.SettlementSystem;
import com.logic.SettlementSystemImpl;
import com.pojo.Participant;
import com.pojo.Security;
import com.pojo.Transaction;




@Path("/ParticipantFirst")
public class ParticipantFirst {
	
	static int participant_Id = 0;
	
	@POST
	@Path("/getLoginInfo")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public int getLoginInfo(JsonObject obj) 
	{
		String username=obj.getString("username");
		String password=obj.getString("password");
		ParticipantDAO dao= new ParticipantDaoImpl();
		Integer object=dao.getLoginInfo(username, password);
		int participantId=0;
		if(object!=null)
		{
			//String s=Integer.toString(object);
			//participantId=Integer.parseInt(s);
			participantId = (int)object;
			//System.out.println();
			participant_Id = participantId;	
			System.out.println("pid" + participant_Id);
		}
		
		
		return participantId;
		
	}
	
	
	@POST
	@Path("/getParticipantById")
	@Produces({MediaType.APPLICATION_JSON})
	public Participant getParticipantById(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		ParticipantDAO dao= new ParticipantDaoImpl();
		Participant participant=null;
		System.out.println("pid in service" + participant_Id);

		participant=dao.getParticipantById(participant_Id);
		if(participant.getFunds()<0)
		{
			participant.setFunds(0);
		}
		return participant;
	}
	
	@POST
	@Path("/getParticipantSecuritiesById")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Security> getParticipantSecuritiesById(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		ParticipantDAO dao= new ParticipantDaoImpl();
		Participant participant=null;
		participant=dao.getParticipantById(participant_Id);
		List<Security> list=participant.getListOfSecurities();
//		System.out.println(list);
		for(Security s:list)
		{
			if(s.getSecurityQuantity()<0)
			{
				s.setSecurityQuantity(0);
			}
		}
		return list;
	}

	@POST
	@Path("/getAllTransactionsByParticipantId")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Transaction> getAllTransactionsByParticipantId(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
//		System.out.println(participantId);
		TransactionDAO dao=new TransactionDAOImpl();
		List<Transaction> listOfTransactions = new ArrayList<Transaction>();
		listOfTransactions=dao.getTransactionByParticipantId(participant_Id);
//		System.out.println(listOfTransactions);
		return listOfTransactions;
	}
	
	@POST
	@Path("/getNetFunds")
	@Produces({MediaType.APPLICATION_JSON})
	public double getNetFunds(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		ParticipantDAO dao3=new ParticipantDaoImpl();
		 double funds1=dao3.getNetFunds(participant_Id);
		 
		 return funds1;
	}
	
	
	@POST
	@Path("/getNetSecurities")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Security> getNetSecurities(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
//		System.out.println(participantId);
		ParticipantDAO dao=new ParticipantDaoImpl();
		List<Security> listOfSec = new ArrayList<Security>();
		listOfSec=dao.getNetSecurities(participant_Id);
		//System.out.println(listOfSec);
		return listOfSec;
	}
	
	@POST
	@Path("/updateFunds")
	@Produces({MediaType.APPLICATION_JSON})
	public boolean updateFunds(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");//to be checked from frontend
		String fun = obj.getString("funds");
//		System.out.println("funds" + fun);
		double funds=Double.parseDouble(obj.getString("funds")) ;

		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean isUpdated=false;
		isUpdated=dao.updateFundBalance(participant_Id,funds);
		return isUpdated;
	}
	
	@POST
	@Path("/getCorporateActionsByParticipantId")
	@Produces({MediaType.APPLICATION_JSON})
	public JSONArray getCorporateActionsByParticipantId(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean isUpdated=false;
		JSONArray corporateActionSecId=dao.getCorporateActionByParticipantId(participant_Id);
		//System.out.println("Corporate action : " + corporateActionSecId);
		return corporateActionSecId;
	}
	
	@POST
	@Path("/excuteRights")
	@Produces({MediaType.APPLICATION_JSON})
	public boolean executeRights(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		int securityId=obj.getInt("security_id");
		//System.out.println("id: " + participantId + " secid = " + securityId);
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean isUpdated=false;
		isUpdated=dao.updateRights(participant_Id, securityId);
		
		return isUpdated;
	}
	

	
	
	@POST
	@Path("/viewFundObligationReport")
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject viewObligationReport(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		SettlementSystem dao= new SettlementSystemImpl();
		JSONObject object=dao.generateFundObligationReport(participant_Id);
//		System.out.println("obligation: " + object);
		return object;
	}
	@POST
	@Path("/viewSecuritiesObligationReport")
	@Produces({MediaType.APPLICATION_JSON})
	public JSONArray viewObligationReportShares(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		SettlementSystem dao= new SettlementSystemImpl();
		JSONArray object=dao.generateSecurityObligationReport(participant_Id);
//		System.out.println("obligation: " + object);
		return object;
	}
	
	@POST
	@Path("/viewCostOfsettlementReport")
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject viewCostOfsettlementReport(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		ParticipantDAO dao=new ParticipantDaoImpl();
		JSONObject object=dao.generateCostOfSettlementReport(participant_Id);
//		System.out.println("cos=>" + object);
		return object;
	}
	
	
	@POST
	@Path("/viewCostOfsettlementReportSecurities")
	@Produces({MediaType.APPLICATION_JSON})
	public JSONArray viewCostOfsettlementReportSecurities(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		ParticipantDAO dao=new ParticipantDaoImpl();
		JSONArray object=dao.generateCostOfSettlementReportSecurities(participant_Id);
//		System.out.println("cos=>" + object);
		return object;
	}
	
	
	@POST
	@Path("/viewFundReconciliation")
	@Produces({MediaType.APPLICATION_JSON})
	public JSONObject viewFundReconciliation(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		ParticipantDAO dao=new ParticipantDaoImpl();
		JSONObject object=dao.viewFundReconciliation(participant_Id);
		
		return object;
	}
	
	
	@POST
	@Path("/viewSecurityReconciliation")
	@Produces({MediaType.APPLICATION_JSON})
	public JSONArray viewSecurityReconciliation(JsonObject obj) 
	{
		//int participantId=obj.getInt("id");
		ParticipantDAO dao=new ParticipantDaoImpl();
		JSONArray object=dao.viewSecurityReconciliation(participant_Id);
		
		return object;
	}
	
	@POST
	@Path("/logout")
	@Produces({MediaType.APPLICATION_JSON})
	public void logout(JsonObject obj) 
	{
		
		participant_Id = 0;
		//int participantId=obj.getInt("id");
		//ParticipantDAO dao=new ParticipantDaoImpl();
		//JSONArray object=dao.viewSecurityReconciliation(participant_Id);
		
		//return object;
	}
	
	
	
	
}
