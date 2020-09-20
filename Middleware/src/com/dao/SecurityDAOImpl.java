package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pojo.Security;

public class SecurityDAOImpl implements SecurityDAO {

	@Override
	public List<Security> getAllSecurityName() {
		
		List<Security> securitiesNames = new ArrayList<Security>();
		String findAllSecurityName = "select security_id,security_name from securities";
		try {
			Statement st = Connect.connection.createStatement();
			ResultSet set = st.executeQuery(findAllSecurityName);
			while(set.next())
			{
				String securityName = set.getString("security_name");
				Integer securityId = set.getInt("security_id");

				Security securities = new Security(securityName, securityId);
				securitiesNames.add(securities);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return securitiesNames;
	}

	@Override
	public HashMap<String, Integer> getSecurityIdMap() {
		// TODO Auto-generated method stub
		HashMap<String, Integer> securityIdName = new HashMap<String,Integer>();
		String securitiesInfo = "select * from securities";
		
		try {
			Statement st = Connect.connection.createStatement();
			ResultSet set = st.executeQuery(securitiesInfo);
			
			while(set.next())
			{
				String securityName = set.getString("security_name");
				Integer securityId = set.getInt("security_id");
				securityIdName.put(securityName, securityId);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return securityIdName;
	}


	@Override
	public HashMap<Integer,HashMap<Integer, Integer> > getAllCorporateAction() {
		// TODO Auto-generated method stub
		HashMap<Integer,HashMap<Integer, Integer>> hashMap=new HashMap<>();
		Connection connection=Connect.connection;
		String QUERY="select security_id,corporate_action,corporate_action_ratio from securities";
		try {
			PreparedStatement statement=connection.prepareStatement(QUERY);
			ResultSet set=statement.executeQuery();
			while(set.next()) {
				int security_id=set.getInt(1);
				int corporate_action=set.getInt(2);
				int corporate_action_ratio=set.getInt(3);
				HashMap<Integer, Integer> m=new HashMap();
				m.put(corporate_action, corporate_action_ratio);
				hashMap.put(security_id, m);
			}
			return hashMap;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public boolean updateCorporateAction(Security security) {
		// TODO Auto-generated method stub
		Connection connection=Connect.connection;
		String QUERY="update table securities set corporate_action=? and corporate_action_ratio=? where security_id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(QUERY);
			statement.setInt(1, security.getCorporateAction());
			statement.setInt(2, security.getCorporateActionRatio());
			statement.setInt(3, security.getSecurityId());
			int rowsUpdated=statement.executeUpdate();
			if(rowsUpdated==1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}



	@Override
	public boolean deleteCorporateAction(int securityId) {
		// TODO Auto-generated method stub
		Connection connection=Connect.connection;
		String QUERY="update securities set corporate_action=? and corporate_action_ratio=? where security_id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(QUERY);
			statement.setInt(1, 0);
			statement.setInt(2, 0);
			statement.setInt(3, securityId);
			int rowsUpdated=statement.executeUpdate();
			if(rowsUpdated==1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}

	@Override
	public HashMap<Integer, Integer> getCorporateActionById(int security_id) {
		Connection connection=Connect.connection;
		HashMap<Integer, Integer> hashMap=new HashMap();
		String QUERY="select corporate_action,corporate_action_ratio from securities where security_id=?";
		try {
			PreparedStatement statement=connection.prepareStatement(QUERY);
			statement.setInt(1, security_id);
			ResultSet set=statement.executeQuery();
			while(set.next()) {
				int corporate_action=set.getInt(1);
				int corporate_action_ratio=set.getInt(2);
				hashMap.put(corporate_action, corporate_action_ratio);
			}
			return hashMap;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public HashMap<Integer, Integer> getCorporateAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCorporateAction(Security security) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HashMap<Integer, Double> getBorrowingRates() {
		HashMap<Integer, Double> map=new HashMap<>();
		String QUERY="select security_id,borrowing_rate from securities";
		try {
			Statement statement=Connect.connection.createStatement();
			ResultSet set=statement.executeQuery(QUERY);
			while(set.next()) {
				map.put(set.getInt(1), set.getDouble(2));
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public HashMap<Integer, Double> getdefaultMarketPrices() {
		HashMap<Integer, Double> map=new HashMap<>();
		String QUERY="select security_id,default_market_price from securities";
		try {
			Statement statement=Connect.connection.createStatement();
			ResultSet set=statement.executeQuery(QUERY);
			while(set.next()) {
				map.put(set.getInt(1), set.getDouble(2));
			}
			return map;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	

}
