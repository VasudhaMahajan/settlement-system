package com.dao;

import java.io.ObjectInputStream.GetField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.*;
import com.pojo.Participant;
import com.pojo.Security;

public class ParticipantDaoImpl implements ParticipantDAO
{
	
	public List<Security> getOpeningSecurities(int participantId) { 
		
		List<Security> sec_list=new ArrayList<Security>();
		String GET_OPENING_SECURITIES="select participant_balance.participant_id,participant_balance.security_id,participant_balance.security_quantity,securities.security_name from participant_balance join securities on participant_balance.security_id=securities.security_id where participant_id=?";  
		
		try {
			
			PreparedStatement ps = Connect.connection.prepareStatement(GET_OPENING_SECURITIES);
			ps.setInt(1, participantId);
			
			
			ResultSet set= ps.executeQuery();
			while(set.next()) {
				//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				int sid=set.getInt("security_id");
				String sname=set.getString("security_name");
				int qty=set.getInt("security_quantity");
				System.out.println(sid+" "+sname+" "+qty);
				
				Security sec=new Security(sname,sid,qty);
				sec_list.add(sec);
			}
			System.out.println("List size= "+sec_list.size());
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sec_list;
		
	
	}
	

public List<Security> getNetSecurities(int participantId) {
	
	List<Security> sec_list=new ArrayList<Security>();
	//String GET_NET_SECURITIES="select securities_netting_result.participant_id, securities_netting_result.security_id,securities_netting_result.amount,securities.security_name from securities_netting_result join securities on securities_netting_result.security_id=securities.security_id where participant_id=?"; 
	String GET_NET_SECURITIES="select securities_netting_result.participant_id, securities_netting_result.security_id,securities_netting_result.amount,securities.security_name from securities_netting_result join securities on securities_netting_result.security_id=securities.security_id where participant_id="+participantId;	 
		
	try {
		Statement ps = Connect.connection.createStatement();
		//PreparedStatement ps = Connect.connection.prepareStatement(GET_NET_SECURITIES);
		//ps.setInt(1, participantId);
		//ResultSet set= ps.executeQuery();
		ResultSet set = ps.executeQuery(GET_NET_SECURITIES);
		while(set.next()) {
			int sid=set.getInt("security_id");
			String sname=set.getString("security_name");
			int qty=set.getInt("amount");
			int corporateAction=0;
			int corporateActionRatio=0;
			
			
			Security sec=new Security(sname,sid,qty,corporateAction,corporateActionRatio);
			sec_list.add(sec);
		}
		//System.out.println("List size= "+sec_list.size());
		
	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return sec_list;
	

	
}




@Override
public double getOpeningFunds(int participantId) {
	double funds=0;
	String GET_OPENING_FUNDS="select funds from participants where participant_id=?";
try {
		
		PreparedStatement ps = Connect.connection.prepareStatement(GET_OPENING_FUNDS);
		ps.setInt(1, participantId);
		ResultSet set= ps.executeQuery();
		while(set.next()) {
			 funds=set.getDouble("funds");
		}
} catch (SQLException e) {
	e.printStackTrace();
}
	return funds;
	
}

@Override
public double getNetFunds(int participantId) {
	double funds=0;
	String GET_NET_FUNDS="select fund_amount from funds_netting_result where participant_id=?";
try {
		
		PreparedStatement ps = Connect.connection.prepareStatement(GET_NET_FUNDS);
		ps.setInt(1, participantId);
		ResultSet set= ps.executeQuery();
		while(set.next()) {
			 funds=set.getFloat("fund_amount");
		}
} catch (SQLException e) {
	e.printStackTrace();
}
	return funds;
	
	
	
}




	@Override
	public boolean updateSecurityBalance(int participantId, List<Security> listOfSecurities) {
		// TODO Auto-generated method stub
		
		boolean sharesUpdated=false;
		
		
		try {
			//to delete existing records for that participant
			String DELETE_SHARE_BALANCE="delete from participant_balance where participant_id=?";
			PreparedStatement ps = Connect.connection.prepareStatement(DELETE_SHARE_BALANCE);
			int rowDeleted=0;
			ps.setInt(1, participantId);
			//System.out.println(rowDeleted+ "  ");
			rowDeleted=ps.executeUpdate();
			//System.out.println("existing shares deleted");
			
			
			//to add updated list
			String INSERT_INTO_PARTICIPANT_BALANCE="Insert into participant_balance values(?,?,?)";
			PreparedStatement ps1=Connect.connection.prepareStatement(INSERT_INTO_PARTICIPANT_BALANCE);
			int size=listOfSecurities.size();
			//System.out.println("size =" +size);
			int rowInserted=0;
			int i=0;
			while(i<size)
			{
				ps1.setInt(1, participantId);
				ps1.setInt(2, listOfSecurities.get(i).getSecurityId());
				ps1.setInt(3, listOfSecurities.get(i).getSecurityQuantity());
				
				rowInserted=ps1.executeUpdate();
				i++;
				//System.out.println("security added "+ i);
				rowInserted++;
			}
			if (rowInserted!=0)
			{
				sharesUpdated=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sharesUpdated;
	}



	@Override
	public boolean updateFundBalance(int participantId, double funds) {
		// TODO Auto-generated method stub
		int rowInserted=0;
		boolean fundsUpdated=false;
//		String output = String.format(" %.2f",funds);
//		funds=Double.parseDouble(output);
//		System.out.println("-------------"+output);

		try {
			String UPDATE_FUNDS="update Participants set funds=? where participant_Id=?";
			PreparedStatement ps=Connect.connection.prepareStatement(UPDATE_FUNDS);
			double initialFunds=getOpeningFunds(participantId);
			double updatedFunds=initialFunds+funds;
			ps.setDouble(1, updatedFunds);
			ps.setInt(2, participantId);
			rowInserted=ps.executeUpdate();
			
			if (rowInserted!=0)
			{
				fundsUpdated=true;
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fundsUpdated;
	}

	@Override
	public boolean updateParticipant(int participantId, String emailId, String contactNumber) {
		// TODO Auto-generated method stub
		int rowInserted=0;
		boolean participantUpdated=false;
		
		try {
			String UPDATE_PARTICIPANT="update Participants set email_id=? , contact_no=? where participant_Id=?";
			PreparedStatement ps=Connect.connection.prepareStatement(UPDATE_PARTICIPANT);
			//System.out.println("ps run");
			
			ps.setString(1, emailId);
			ps.setString(2, contactNumber);
			ps.setInt(3, participantId);
			rowInserted=ps.executeUpdate();
			
			if (rowInserted!=0)
			{
				participantUpdated=true;
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return participantUpdated;
		
	}

	@Override
	public boolean addParticipant(Participant participant) {
		// TODO Auto-generated method stub
		int rowInserted=0;
		boolean participantAdded=false;
		
		try {
			String INSERT_INTO_PARTICIPANT="Insert into Participants values(?,?,?,?,?,?)";
			PreparedStatement ps=Connect.connection.prepareStatement(INSERT_INTO_PARTICIPANT);
			
			ps.setInt(1, participant.getParticipantId());
			ps.setString(2, participant.getParticipantName());
			ps.setString(3, participant.getEmailId());
			ps.setString(4, participant.getContactNumber());
			ps.setFloat(5, participant.getFunds());
			ps.setFloat(6, participant.getFeeForSettlement());
			rowInserted=ps.executeUpdate();
			//System.out.println("row inserted in participant table");
			
			
			String INSERT_INTO_PARTICIPANT_BALANCE="Insert into participant_balance values(?,?,?)";
			PreparedStatement ps1=Connect.connection.prepareStatement(INSERT_INTO_PARTICIPANT_BALANCE);
			int size=participant.getListOfSecurities().size();
			//System.out.println("size =" +size);
			rowInserted=0;
			int i=0;
			while(i<size)
			{
				ps1.setInt(1, participant.getParticipantId());
				ps1.setInt(2, participant.getListOfSecurities().get(i).getSecurityId());
				ps1.setInt(3, participant.getListOfSecurities().get(i).getSecurityQuantity());
				
				rowInserted=ps1.executeUpdate();
				i++;
				//System.out.println("security added "+ i);
				rowInserted++;
			}
			if (rowInserted!=0)
			{
				participantAdded=true;
			}
			
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return participantAdded;
	}

	@Override
	public boolean addLoginInfo (int participantId, String username, String password) {
		// TODO Auto-generated method stub
		int rowInserted=0;
		boolean loginInfoAdded=false;
		
		try {
			String INSERT_INTO_LOGIN_INFO="Insert into Login_info values(?,?,?)";
			PreparedStatement ps=Connect.connection.prepareStatement(INSERT_INTO_LOGIN_INFO);
			
			ps.setInt(1, participantId);
			ps.setString(2, username);
			ps.setString(3, password);
			rowInserted=ps.executeUpdate();
			//System.out.println("row inserted in login info table");
			
			if (rowInserted!=0)
			{
				loginInfoAdded=true;
			}
			
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loginInfoAdded;
	}

	@Override
	public boolean updateLoginInfo(int participantId, String password) {
		// TODO Auto-generated method stub
		boolean isUpdated=false;
		String LOGIN_INFO_UPDATE="update login_info set password=? where participant_id=?";
		try {
			PreparedStatement ps=Connect.connection.prepareStatement(LOGIN_INFO_UPDATE);
			ps.setString(1, password);
			ps.setInt(2,participantId);
			int rows=ps.executeUpdate();
			if(rows>0) {
				isUpdated=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return isUpdated;
	}

	@Override
	public Participant getParticipantById(int participantId) {
		// TODO Auto-generated method stub
		Participant participant=null;
		
		String FIND_PARTICIPANT="select * from Participants where participant_id=?";
		try {
			String imagePath="";
			String participantName="";
			String emailId="";
			String contactNumber="";
			float funds=0;
			float feeForSettlement=0;
			List<Security> listOfSecurities=new ArrayList<>();
			
			PreparedStatement ps = Connect.connection.prepareStatement(FIND_PARTICIPANT);
			ps.setInt(1, participantId);
			ResultSet set= ps.executeQuery();
			while(set.next()) {
				imagePath=set.getString("image_path");
				participantName= set.getString("participant_name");
				emailId= set.getString("email_ID");
				contactNumber=set.getString("contact_no");
				funds=set.getFloat("funds");
				feeForSettlement=set.getFloat("fee_for_settlement");
				//System.out.println("details obtained from participant");
			}
			
			String FIND_PARTICIPANT_BALANCE="select pb.security_id,s.security_name,pb.security_quantity from participant_balance pb join securities s on pb.security_id=s.security_id where participant_id=?";
			PreparedStatement ps1 = Connect.connection.prepareStatement(FIND_PARTICIPANT_BALANCE);
			ps1.setInt(1, participantId);
			ResultSet set1= ps1.executeQuery();
			
			while(set1.next()) 
			{
				Security security=new Security();
				security.setSecurityId(set1.getInt(1));
//				System.out.println(set1.getInt(1));
				security.setSecurityName(set1.getString(2));
				security.setSecurityQuantity(set1.getInt(3));
				
				listOfSecurities.add(security);
			}
			
			participant=new Participant(participantId, participantName, emailId, contactNumber, funds, feeForSettlement, imagePath, listOfSecurities);
			//			System.out.println(listOfSecurities);
			
			//System.out.println("participant=\n"+ participant.toString());
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return participant;
	}

	@Override
	public Integer getLoginInfo(String username, String password) {
		// TODO Auto-generated method stub
		int participantId=0;
		Integer intObj=null;
		String GET_LOGIN_INFO="select participant_id from login_info where username=? and password=?";
		try {
			PreparedStatement ps = Connect.connection.prepareStatement(GET_LOGIN_INFO);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet resultSet=ps.executeQuery();
			while(resultSet.next()) {
			participantId=resultSet.getInt(1);
			}
			if(participantId==0)
				return null;
			intObj=new Integer(participantId);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Integer intObj=new Integer(participantId);
		
		return intObj;
	}

	@Override
	public String getParticipantNameById(int participantId) {
		// TODO Auto-generated method stub
		String participantName=null;
		String findParticipant = "select participant_name from participants where participant_id=?";
		
		try {
			PreparedStatement ps = Connect.connection.prepareStatement(findParticipant);
			ps.setInt(1, participantId);
			ResultSet set = ps.executeQuery();
			
			while(set.next())
			{
				participantName=set.getString("participant_name");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return participantName;
	}

	@Override
	public List<Participant> getAllParticipantName() {
		// TODO Auto-generated method stub
		List<Participant> participantNames = new ArrayList<Participant>();
		String findAllParticipantName = "select * from participants";
		try {
			Statement st = Connect.connection.createStatement();
			ResultSet set = st.executeQuery(findAllParticipantName);
			while(set.next())
			{
				String participantName = set.getString("participant_name");
				Integer participantId = set.getInt("participant_id");
				String emailId = set.getString("email_id");
				String contactNumber = set.getString("contact_no");
				String imagePath = set.getString("image_path");

				Participant participant = new Participant(participantId, participantName,emailId,contactNumber,imagePath);
				participantNames.add(participant);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("parti: "+participantNames);
		return participantNames;
	}



	@Override
	public HashMap<String, Integer> getParticipantIdMap() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> participantIdName = new HashMap<String,Integer>();
		String PARTICIPANT_INFO = "select * from participants";
		
		try {
			Statement st = Connect.connection.createStatement();
			ResultSet set = st.executeQuery(PARTICIPANT_INFO);
			
			while(set.next())
			{
				String participantName = set.getString("participant_name");
				Integer participantId = set.getInt("participant_id");
				participantIdName.put(participantName, participantId);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return participantIdName;
	}


	@Override
	public List<List<Integer>> perfomJoinBalanceSecurity() {
		
		List<List<Integer>> joinedList = new ArrayList<List<Integer>>();
		
		String joinBalanceSecurity = "select p.participant_id,s.security_id,s.corporate_action,s.corporate_action_ratio,p.security_quantity,\r\n" + 
				"p.perform_rights from participant_balance p\r\n " + 
				"JOIN securities s on p.security_id=s.security_id";
		
		Statement st;
		try {
			st = Connect.connection.createStatement();
			ResultSet set = st.executeQuery(joinBalanceSecurity);
			
			while(set.next())
			{
				List<Integer> list=new ArrayList<Integer>();
				list.add(set.getInt(1));
				list.add(set.getInt(2));
				list.add(set.getInt(3));
				list.add(set.getInt(4));
				list.add(set.getInt(5));
				list.add(set.getInt(6));
//				list.add(set.getInt(7));
				
				joinedList.add(list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return joinedList;
	}


	
	@Override
	public JSONArray getCorporateActionByParticipantId(int participant_id) {
		// TODO Auto-generated method stub
		Map<String, List<Integer>> corporateActionSecId = new HashMap<>();
		JSONArray jsonArray=new JSONArray();
//		JSONObject jsonObject;
		String joinBalanceSecurity = "select s.security_name,s.corporate_action,s.corporate_action_ratio,s.security_id from securities s join participant_balance p on p.security_id=s.security_id where p.participant_id=?";
		
		try {
			
			PreparedStatement st = Connect.connection.prepareStatement(joinBalanceSecurity);
			st.setInt(1, participant_id);
			ResultSet set = st.executeQuery();
			while(set.next())
			{
				JSONObject jsonArrayelement=new JSONObject();
				if(set.getInt(2)!=0)
				{
						//System.out.println("sec Id: " + set.getInt(1));
						jsonArrayelement.put("securityId", set.getInt(4));
						jsonArrayelement.put("securityName", set.getString(1));
						//System.out.println("sec Name: " + set.getInt(2));

						jsonArrayelement.put("corporateActionName", set.getInt(2));
						jsonArrayelement.put("corporateActionRatio", set.getInt(3));
						
						jsonArray.add(jsonArrayelement);
						
	
				}
				
			}
			System.out.println(jsonArray);

//			System.out.println(corporateActionSecId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonArray;
	}



	@Override
	public boolean updateParticipantBalance(int participantId, int securityId, int securityQty) {
		// TODO Auto-generated method stub
		int rowInserted=0;
		boolean participantUpdated=false;
		
		try {
			String UPDATE_PARTICIPANT="update Participant_balance set security_quantity=? where security_id=? and participant_Id=?";
			PreparedStatement ps=Connect.connection.prepareStatement(UPDATE_PARTICIPANT);
			//System.out.println("ps run");
			
			ps.setInt(2, securityId);
			ps.setInt(1, securityQty);
			ps.setInt(3, participantId);
			rowInserted=ps.executeUpdate();
			
			if (rowInserted!=0)
			{
				participantUpdated=true;
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return participantUpdated;
	}



	@Override
	public int getSecurityQtyByParticipantId(int participantId, int securityId) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public HashMap<Integer, Double> getAllFunds() {
		HashMap<Integer, Double> map=new HashMap<>();
		String GET_ALL_FUNDS="select participant_id,funds from participants";
			try{
				
				Statement s = Connect.connection.createStatement();
				ResultSet set=s.executeQuery(GET_ALL_FUNDS);
				while(set.next()) {
					 map.put(set.getInt(1), set.getDouble(2));
				}
				return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public HashMap<Integer, HashMap<Integer, Integer>> getAllSecurities() {
		// TODO Auto-generated method stub
		HashMap<Integer, HashMap<Integer, Integer>> hashMap=new HashMap<>();
		String GET_ALL_SECURITIES="select participant_id,security_id,security_quantity from participant_balance";
			try{
				
				Statement s = Connect.connection.createStatement();
				ResultSet set=s.executeQuery(GET_ALL_SECURITIES);
				while(set.next()) {
				//	System.out.println(set.getInt(1)+"  "+set.getInt(2)+"  "+set.getInt(3));
					 if(hashMap.containsKey(set.getInt(1))) {
						 hashMap.get(set.getInt(1)).put(set.getInt(2), set.getInt(3));
					 }
					 else
					 {
						 HashMap<Integer,Integer> map=new HashMap<>();
						 map.put(set.getInt(2), set.getInt(3));
						 hashMap.put(set.getInt(1), map);
					 }
				}
				System.out.println(hashMap);
				return hashMap;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public HashMap<Integer, HashMap<Integer, Integer>> getNetSecurities() {
		HashMap<Integer, HashMap<Integer, Integer>> hashMap=new HashMap<>();
		String GET_ALL_SECURITIES="select participant_id,security_id,amount from securities_netting_result";
			try{
				
				Statement s = Connect.connection.createStatement();
				ResultSet set=s.executeQuery(GET_ALL_SECURITIES);
				while(set.next()) {
					 if(hashMap.containsKey(set.getInt(1))) {
						 hashMap.get(set.getInt(1)).put(set.getInt(2), set.getInt(3));
					 }
					 else
					 {
						 HashMap<Integer,Integer> map=new HashMap<>();
						 map.put(set.getInt(2), set.getInt(3));
						 hashMap.put(set.getInt(1), map);
					 }
				}
				return hashMap;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	//when a participant buys a security that he did not have previously
	public boolean addParticipantBalance(int participantId, int securityId, int securityNetVal) {
		int rowInserted=0;
		boolean participantUpdated=false;
		
		try {
			String UPDATE_PARTICIPANT="insert into Participant_balance values(?,?,?,?)";
			PreparedStatement ps=Connect.connection.prepareStatement(UPDATE_PARTICIPANT);
			ps.setInt(2, securityId);
			ps.setInt(3, securityNetVal);
			ps.setInt(1, participantId);
			ps.setInt(4, 0);
			rowInserted=ps.executeUpdate();			
			if (rowInserted!=0)
			{
				participantUpdated=true;
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return participantUpdated;
	}


	@Override
	public HashMap<Integer, Double> getNetFunds() {
		HashMap<Integer, Double> map=new HashMap<>();
		String GET_NET_FUNDS="select participant_id,fund_amount from funds_netting_result";
			try{			
				Statement s = Connect.connection.createStatement();
				ResultSet set=s.executeQuery(GET_NET_FUNDS);
				while(set.next()) {
					 map.put(set.getInt(1), set.getDouble(2));
				}
				return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public HashMap<Integer, HashMap<Integer, Integer>> getAllShortages() {

		HashMap<Integer, HashMap<Integer, Integer>> hashMap=new HashMap<>();
		String GET_ALL_SECURITIES="select participant_id,security_id,shortage_quantity from securities_netting_result where shortage_quantity<0";
			try{
				
				Statement s = Connect.connection.createStatement();
				ResultSet set=s.executeQuery(GET_ALL_SECURITIES);
				while(set.next()) {
					 if(hashMap.containsKey(set.getInt(1))) {
						 hashMap.get(set.getInt(1)).put(set.getInt(2), set.getInt(3));
					 }
					 else
					 {
						 HashMap<Integer,Integer> map=new HashMap<>();
						 map.put(set.getInt(2), set.getInt(3));
						 hashMap.put(set.getInt(1), map);
					 }
				}
				return hashMap;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void updateSecurityPenalty(int participantId, int securityId, double penalty) {
		
		try {
			String UPDATE_PARTICIPANT="update Participant_balance set penalty=? where security_id=? and participant_Id=?";
			PreparedStatement ps=Connect.connection.prepareStatement(UPDATE_PARTICIPANT);
			//System.out.println("ps run");
			
			ps.setInt(2, securityId);
			ps.setDouble(1, penalty);
			ps.setInt(3, participantId);
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void updateTotalSecurityPenalty(int participantId, double penalty) {
		
		try {
			String UPDATE_PARTICIPANT="update Participants set securities_penalty=? where participant_Id=?";
			PreparedStatement ps=Connect.connection.prepareStatement(UPDATE_PARTICIPANT);		
			ps.setDouble(1, penalty);
			ps.setInt(2, participantId);
			ps.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public HashMap<Integer, Double> getAllFundShortages() {

		HashMap<Integer, Double> map=new HashMap<>();
		String GET_ALL_FUNDS="select participant_id,shortage_amount from funds_netting_result where shortage_amount<0";
			try{
				
				Statement s = Connect.connection.createStatement();
				ResultSet set=s.executeQuery(GET_ALL_FUNDS);
				while(set.next()) {
					 map.put(set.getInt(1), set.getDouble(2));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return map;
	}


	@Override
	public void updateFundPenalty(int participantId, double fundPenalty) {
		// TODO Auto-generated method stub
		String QUERY="update participants set funds_penalty=? where participant_id=?";
		try {
			PreparedStatement statement=Connect.connection.prepareStatement(QUERY);
			statement.setDouble(1, fundPenalty);
			statement.setInt(2, participantId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

////Incomplete Function Its not used right now
	@Override
	public HashMap<Integer, Double> getTotalTransactionValue() {

		HashMap<Integer, Double> map=new HashMap<>();
		double netValue=0.0;
		String GET_NET_FUNDS="select seller_id,sum(security_quantity*share_price) from transactions group by seller_id";
			try{			
				Statement s = Connect.connection.createStatement();
				ResultSet set=s.executeQuery(GET_NET_FUNDS);
				while(set.next()) {
					 map.put(set.getInt(1), set.getDouble(2));
				}
				return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void updateFeeForSettlement(int participantId, double costOfSettlement) {
		// TODO Auto-generated method stub
		String QUERY="update participants set fee_for_settlement=? where participant_id=?";
		try {
			PreparedStatement statement=Connect.connection.prepareStatement(QUERY);
			statement.setDouble(1, costOfSettlement);
			statement.setInt(2, participantId);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	@Override
	public boolean updateRights(int participantId, int securityId) {
		// TODO Auto-generated method stub
		String updateRight = "update participant_balance set perform_rights =1 where participant_id=? and security_id=?";
		boolean isUpdated=false;
		int updated=0;
		
		try {
			PreparedStatement ps = Connect.connection.prepareStatement(updateRight);
			ps.setInt(1, participantId);
			ps.setInt(2,securityId );
			updated=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(updated!=0)
		{
			isUpdated=true;
		}
		return isUpdated;
	}
	
	@Override
	public JSONObject viewFundReconciliation(int participantId) {
		// TODO Auto-generated method stub
		String VIEW_FUNDS_RECONCILIATION = "select * from funds_reconciliation where participant_id=?";
		JSONObject obj=new JSONObject();
		try {
			PreparedStatement ps = Connect.connection.prepareStatement(VIEW_FUNDS_RECONCILIATION);
			ResultSet set = ps.executeQuery();
			
			while(set.next())
			{

				obj.put("openingBalance",set.getDouble(2));
				obj.put("nettingBalance", set.getDouble(3));
				obj.put("fundShortageAmount", set.getDouble(4));
				obj.put("totalFundpenalty", set.getDouble(5));
				obj.put("totalSecurityPenalty", set.getDouble(6));
				obj.put("rightEffect", set.getDouble(7));
				obj.put("closingBalance", set.getDouble(8));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return obj;
	}
	
	
	@Override
	public JSONArray viewSecurityReconciliation(int participantId) {
		// TODO Auto-generated method stub
		String VIEW_SECURITIES_RECONCILIATION = "select * from securities_reconciliation where participant_id=?";
		JSONArray object=new JSONArray();
		try {
			PreparedStatement ps = Connect.connection.prepareStatement(VIEW_SECURITIES_RECONCILIATION);
			ResultSet set = ps.executeQuery();
			
			while(set.next())
			{
				JSONObject obj=new JSONObject();
				obj.put("securityName", set.getString(3));
				obj.put("openingBalance",set.getDouble(4));
				obj.put("nettingBalance", set.getDouble(5));
				obj.put("shortageQuantity", set.getInt(6));
				obj.put("corporateAction", set.getInt(7));
				obj.put("corporateActionratio", set.getInt(8));
				obj.put("corporateActionEffect", set.getDouble(9));
				obj.put("closingBalance", set.getDouble(10));
				
				object.add(obj);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return object;
	}
	
	
	
	@Override
	public JSONObject generateCostOfSettlementReport(int participantId)
	{
		Connection connection = Connect.connection;
		
		Statement statement;
		Statement statement1;
		Statement statement2;
		JSONObject json = new JSONObject();
		try {
			
			statement = connection.createStatement();
			statement1 = connection.createStatement();
			statement2 = connection.createStatement();
			ResultSet rs = statement.executeQuery("select s.security_id,s.security_name,pb.security_quantity,pb.penalty from securities s inner join participant_balance pb on s.security_id=pb.security_id where pb.penalty<0 and participant_id="+participantId);
			ResultSet rs1 = statement1.executeQuery("select shortage_amount from funds_netting_result where shortage_amount<0 and participant_id ="+participantId);
			ResultSet rs2 = statement2.executeQuery("select funds_penalty, securities_penalty from participants where participant_id ="+participantId);
			
			
			JSONArray securityArray = new JSONArray();
			
			ParticipantDAO dao = new ParticipantDaoImpl();
			
			json.put("ParticipantName", dao.getParticipantNameById(participantId));
			
			rs1.next();
			double fundShortage =rs1.getDouble("shortage_amount");
			json.put("FundShortage", fundShortage);
			
			rs2.next();
			json.put("FundPenalty", rs2.getDouble("funds_penalty"));
			json.put("SharePenalty", rs2.getDouble("securities_penalty"));
			
			System.out.println("Json object:"+json);
			while(rs.next())
			{
				JSONObject securityEntry = new JSONObject();
				securityEntry.put("SecurityID",  rs.getInt("security_id"));
				securityEntry.put("SecurityName",rs.getString("security_name"));
				securityEntry.put("SecurityQuantity",rs.getInt("security_quantity"));
				securityEntry.put("Penalty",rs.getDouble("penalty"));
				
				securityArray.add(securityEntry);
			}
			
			json.put("Securities", securityArray);
			
			System.out.println("Final json:"+json);
		
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Override
	public JSONArray generateCostOfSettlementReportSecurities(int participantId)
	{
		Connection connection = Connect.connection;
		
		Statement statement;
		Statement statement1;
		Statement statement2;
		JSONArray securityArray = new JSONArray();
		try {
			
			statement = connection.createStatement();
			statement1 = connection.createStatement();
			statement2 = connection.createStatement();
			ResultSet rs = statement.executeQuery("select s.security_id,s.security_name,pb.security_quantity,pb.penalty from securities s inner join participant_balance pb on s.security_id=pb.security_id where pb.penalty<0 and participant_id="+participantId);
			
			ParticipantDAO dao = new ParticipantDaoImpl();
		
			while(rs.next())
			{
				JSONObject securityEntry = new JSONObject();
				securityEntry.put("SecurityID",  rs.getInt("security_id"));
				securityEntry.put("SecurityName",rs.getString("security_name"));
				securityEntry.put("SecurityQuantity",rs.getInt("security_quantity"));
				securityEntry.put("Penalty",rs.getDouble("penalty"));
				
				securityArray.add(securityEntry);
			}
		
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return securityArray;
	}
	
	
	
	@Override
	public boolean resetParticipant() {
		String query1 = "update participants set funds_penalty=0,securities_penalty=0";
		String query2 = "update participants set funds=0 where funds<0";
		String query3 = "update participant_balance set penalty=0,perform_rights=0";
		try {
			Statement st = Connect.connection.createStatement();
			st.executeUpdate(query1);
			st.executeUpdate(query2);
			st.executeUpdate(query3);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean generateSecurityReconciliationTable() {
		// TODO Auto-generated method stub
		String QUERY1="truncate table  securities_reconciliation";
		String query2="insert into securities_reconciliation(participant_id,security_id,security_name,opening_balance) \r\n" + 
				"select p.participant_id,p.security_id,s.security_name,p.security_quantity "
				+ "from participant_balance p join securities s on p.security_id=s.security_id";
		try {
			Statement statement=Connect.connection.createStatement();
			statement.execute(QUERY1);
			statement.executeUpdate(query2);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

	
}
