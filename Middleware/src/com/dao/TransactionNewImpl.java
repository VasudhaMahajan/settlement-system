
package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import org.json.simple.*;

import com.pojo.Transaction;

public class TransactionNewImpl {
	public JSONArray getCorporateActions()
	{
		Connection connection = Connect.connection;
		JSONArray securityArray = new JSONArray();
		Statement statement=null;
		try 
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select security_id,security_name,corporate_action,corporate_action_ratio from securities where corporate_action!=0");
			
			while(rs.next())
			{
				JSONObject json = new JSONObject();
				json.put("SecurityID",rs.getInt("security_id"));
				json.put("SecurityName", rs.getString("security_name"));
				json.put("CorporateAction", rs.getInt("corporate_action"));
				json.put("CorporateRatio", rs.getInt("corporate_action_ratio"));
				
				securityArray.add(json);
			}
		
		
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(securityArray);
		return securityArray;
	}
	
    
public JSONArray getAllSecuritiesNettingResult(){
		
		Connection connection = Connect.connection;
		JSONArray Obj = new JSONArray();
		//JsonArray jsonArr;
		
		try {
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery("select p.participant_id,p.participant_name,s.security_id,s.security_name,snr.amount from participants p inner join securities_netting_result snr on p.participant_id=snr.participant_id inner join\r\n" + 
				"securities s on s.security_id=snr.security_id");
		
		
			while(rs.next())
			{
				
				JSONObject res = new JSONObject();
				res.put("ParticipantId",(int) rs.getInt("participant_id"));
				res.put("Name",rs.getString("participant_name"));
				res.put("securitryId", rs.getInt("security_id"));
				res.put("SecurityName",rs.getString("security_name"));
				res.put("securityAmount", rs.getInt("amount"));
				
				Obj.add(res);
				
			}
			
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return Obj;
	}

	



public JSONArray getAllFundsNettingResult()
{
	
	Connection connection = Connect.connection;
	JSONArray Obj = new JSONArray();
	try {
	Statement statement = connection.createStatement();
	
	ResultSet rs = statement.executeQuery("select p.participant_id,p.participant_name,fnr.fund_amount from participants p join funds_netting_result fnr on fnr.participant_id=p.participant_id");
	
	
		while(rs.next())
		{
			JSONObject res = new JSONObject();
			res.put("ParticipantId",rs.getInt("participant_id"));
			res.put("Name",rs.getString("participant_name"));
			res.put("FundAmount", rs.getDouble("fund_amount"));
			
			
			Obj.add(res);
			
		}
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	
	return Obj;
}

public JSONArray getNettingResult()
{
	
	Connection connection = Connect.connection;
	JSONArray Obj = new JSONArray();
	
	
	try {
	Statement statement = connection.createStatement();
	
	ResultSet rs = statement.executeQuery("select p.participant_id,p.participant_name,fnr.fund_amount from participants p join funds_netting_result fnr on fnr.participant_id=p.participant_id");
	
	Statement statement1 = connection.createStatement();
	
	
	

		while(rs.next())
		{
			JSONObject res = new JSONObject();
			JSONArray Obj_shares = new JSONArray();
			ResultSet rs1 = statement1.executeQuery("select p.participant_id,p.participant_name,s.security_id,s.security_name,snr.amount from participants p inner join securities_netting_result snr on p.participant_id=snr.participant_id inner join\r\n" + 
					"securities s on s.security_id=snr.security_id");
			
			res.put("ParticipantId",rs.getInt("participant_id"));
			res.put("Name",rs.getString("participant_name"));
			res.put("FundAmount", rs.getDouble("fund_amount"));
			
			while(rs1.next())
			{
				if(rs.getInt("participant_id") == rs1.getInt("participant_id")) 
				{
					JSONObject res1 = new JSONObject();
					res1.put("ParticipantId",(int) rs1.getInt("participant_id"));
					res1.put("Name",rs1.getString("participant_name"));
					res1.put("securitryId", rs1.getInt("security_id"));
					res1.put("SecurityName",rs1.getString("security_name"));
					res1.put("securityAmount", rs1.getInt("amount"));
					
					Obj_shares.add(res1);
					//System.out.println("obj shares;"+Obj_shares);
				}			
			}	
			 
			res.put("shares", Obj_shares);
			Obj.add(res);
			
		}
		//System.out.println(Obj);
		return Obj;
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	
	return Obj;
}
public JSONArray getFundShortageResult()
{
	Connection connection = Connect.connection;
	JSONArray fundJsonArray = new JSONArray();
	
	
	try {
	Statement statement = connection.createStatement();
	//Statement statement1 = connection.createStatement();
	
	ResultSet rs = statement.executeQuery("select p.participant_id,p.participant_name,fnr.fund_amount from participants p join funds_netting_result fnr on fnr.participant_id=p.participant_id where fnr.shortage_amount<0");

		while(rs.next())
		{
			JSONObject fundJasonElement = new JSONObject();
			
			fundJasonElement.put("ParticipantId",rs.getInt("participant_id"));
			fundJasonElement.put("Name",rs.getString("participant_name"));
			fundJasonElement.put("ShortageAmount", rs.getDouble("fund_amount"));
			 
			
			fundJsonArray.add(fundJasonElement);
		}
		
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println(fundJsonArray);
	return fundJsonArray;
}


public JSONArray getSecuritiesShortageResult()
{
	Connection connection = Connect.connection;
	JSONArray fundJsonArray = new JSONArray();
	
	
	try {
	Statement statement = connection.createStatement();
	//Statement statement1 = connection.createStatement();
	
	ResultSet rs = statement.executeQuery("select p.participant_id,p.participant_name,s.security_id,s.security_name,snr.shortage_quantity from participants p inner join securities_netting_result snr on p.participant_id=snr.participant_id inner join securities s on s.security_id=snr.security_id where snr.shortage_quantity <0");

		while(rs.next())
		{
			JSONObject fundJasonElement = new JSONObject();
			
			fundJasonElement.put("ParticipantId",rs.getInt("participant_id"));
			fundJasonElement.put("ParticipantName",rs.getString("participant_name"));
			fundJasonElement.put("SecurityId",rs.getInt("security_id"));
			fundJasonElement.put("SecurityName",rs.getString("security_name"));
			fundJasonElement.put("ShortageAmount", rs.getDouble("shortage_quantity"));
			 
			
			fundJsonArray.add(fundJasonElement);
		}
		
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	System.out.println(fundJsonArray);
	return fundJsonArray;
}

}

