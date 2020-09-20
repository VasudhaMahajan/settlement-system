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
import java.util.Map;

import com.pojo.Transaction;

public class TransactionDAOImpl implements TransactionDAO {

	
	@Override
	public List<Transaction> getAllTransaction() {
		
		Connection connection = Connect.connection;
		List<Transaction> listOfTransactions = new ArrayList<Transaction>();
		
		try {
		Statement statement = connection.createStatement();
		
		ResultSet rs = statement.executeQuery("select* from TRANSACTIONS");
		
			while(rs.next())
			{
				int tranId = rs.getInt("transaction_id");
				int ssinId = rs.getInt("SSIN_id");
				int sellerId = rs.getInt("seller_id");
				String sellerName=rs.getString("seller_name");
				int buyerId = rs.getInt("buyer_id");
				String buyerName=rs.getString("buyer_name");
				int securityId = rs.getInt("security_id");
				String securityName=rs.getString("security_name");
				int securityQty = rs.getInt("security_quantity");
				double securityPrice = rs.getDouble("share_price");
				
				double transactionAmt = securityQty*securityPrice;
				
				Transaction transaction = new Transaction(tranId, ssinId, sellerId, sellerName, buyerId,buyerName, securityId,securityName, securityQty, securityPrice, transactionAmt);
				listOfTransactions.add(transaction);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
//		System.out.println(listOfTransactions.size());
		return listOfTransactions;
	}

	@Override
	public List<Transaction> getTransactionByParticipantId(int participantId) {
		
		Connection connection = Connect.connection;
		List<Transaction> listOfTransactionsByID = new ArrayList<Transaction>();
		
		try {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select* from TRANSACTIONS where seller_id="+participantId+"or buyer_id="+participantId);
	
		while(rs.next())
		{
			int tranId = rs.getInt("transaction_id");
			int ssinId = rs.getInt("SSIN_id");
			int sellerId = rs.getInt("seller_id");
			int buyerId = rs.getInt("buyer_id");
			int securityId = rs.getInt("security_id");
			int securityQty = rs.getInt("security_quantity");
			float securityPrice = rs.getFloat("share_price");
			String sellerName=rs.getString("seller_name");
			String buyerName=rs.getString("buyer_name");
			String securityName=rs.getString("security_name");
			double transactionAmt = securityQty*securityPrice;
			
			Transaction transaction = new Transaction(tranId, ssinId, sellerId, sellerName, buyerId,buyerName, securityId,securityName, securityQty, securityPrice, transactionAmt);
			listOfTransactionsByID.add(transaction);
		}
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}		
		return listOfTransactionsByID;
 }
		
	@Override
	public boolean generateNettingTable(HashMap<Integer, HashMap<Integer, Integer>> securitiesNettingMap) {
		
		//System.out.println("Netting share map:"+hashMap);
		ParticipantDAO dao=new ParticipantDaoImpl();
		HashMap<Integer, HashMap<Integer, Integer>> securitiesBalanceMap=dao.getAllSecurities();/////yahe pe gadbad hai
		boolean added = false;
		int participantId=-1;
		int securityId=-1;
		int securityNetVal=0;
		String INSERT_NETTING;
		Connection connection = Connect.connection;
	    PreparedStatement ps;   
	   // System.out.println("HashMap inside generateNettingTable: __________");
	  //  System.out.println(hashMap);
		for(Map.Entry participant: securitiesNettingMap.entrySet())
		{
			participantId =(int) participant.getKey();
			HashMap<Integer,Integer> nettingMap =(HashMap<Integer, Integer>) participant.getValue();
			HashMap<Integer,Integer> balanceMap =(HashMap<Integer, Integer>) securitiesBalanceMap.get(participantId);	
			System.out.println(nettingMap);
			System.out.println(balanceMap);
			System.out.println();

			for(Map.Entry balances: nettingMap.entrySet())
			{
				securityId =(int) balances.getKey();
				securityNetVal =(int) balances.getValue();
			INSERT_NETTING = "INSERT INTO securities_netting_result VALUES (?,?,?,?)";
			try {
				ps = connection.prepareStatement(INSERT_NETTING);
				ps.setInt(1, participantId);
				ps.setInt(2, securityId);
				ps.setInt(3, securityNetVal);
				if(balanceMap.containsKey(securityId)) {
					ps.setInt(4, securityNetVal+balanceMap.get(securityId));
				}else {
					ps.setInt(4, securityNetVal);
				}
				int rows_inserted =ps.executeUpdate();		    
			    //System.out.println("Rows added:"+rows_inserted);
			    if(rows_inserted>0)
			    {
			    	added = true;
			    }
				
			} 
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		
		return added;
	}
	
	
	@Override
	public boolean generateNettingFundsTable(HashMap<Integer, Double> fundsNettingMap) {
		// TODO Auto-generated method stub
		Connection connection = Connect.connection;
		ParticipantDAO dao=new ParticipantDaoImpl();
		HashMap<Integer, Double> fundsBalanceMap=dao.getAllFunds();
		System.out.println(fundsBalanceMap);
		String QUERY="insert into funds_netting_result values(? , ?, ?)";
		try {
			for (Map.Entry<Integer,Double> entry : fundsNettingMap.entrySet()) {
				//System.out.println("EntryKey:"+entry.getKey()+"   "+"EntryValue:"+entry.getValue());
				PreparedStatement statement=connection.prepareStatement(QUERY);
				statement.setInt(1, entry.getKey());
				statement.setDouble(2, entry.getValue());
				statement.setDouble(3, fundsBalanceMap.get(entry.getKey())+entry.getValue());
				statement.executeUpdate();
			}
			
			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}
		
	}

	@Override
	public boolean addTransaction(Transaction transaction) {
		
		boolean added = false;
		
		Connection connection = Connect.connection;
		String INSERT_TRANSACTION ="INSERT INTO transactions VALUES(?,?,?,?,?,?,?,?,?,?)";
	    PreparedStatement ps;    
		
try {
		ps = connection.prepareStatement(INSERT_TRANSACTION);
		
	    ps.setInt(1,transaction.getTransactionid());
	    ps.setInt(2,transaction.getSSIN_Id());
	    ps.setInt(3,transaction.getSellerId());
	    ps.setString(4, transaction.getSellerName());
	    ps.setInt(5,transaction.getBuyerId());
	    ps.setString(6, transaction.getBuyerName());
	    ps.setInt(7,transaction.getSecurityId());
	    ps.setString(8, transaction.getSecurityName());
	    ps.setInt(9,transaction.getSecurityQuantity());
	    ps.setDouble(10,transaction.getSecurityRate());
	    
	    
	    int rows_inserted =ps.executeUpdate();		    
	   // System.out.println("Rows added:"+rows_inserted);
	    
	    if(rows_inserted>0)
	    {
	    	added = true;
	    }
	    
}		
catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	 
		return added;
}

	
	@Override
	public boolean updateTransaction(Transaction transaction) {
		
		boolean updated = false;
		Connection connection = Connect.connection;
		String UPDATE_TRANSACTION ="UPDATE transactions SET security_quantity = ? WHERE transaction_id = ?";
	    PreparedStatement ps;    
	    
	    try {
			ps = connection.prepareStatement(UPDATE_TRANSACTION);
			
			ps.setInt(1,transaction.getSecurityQuantity());
		    ps.setInt(2,transaction.getTransactionid());
		  
		    int rows_updated =ps.executeUpdate();		    
		   // System.out.println("Rows updated:"+rows_updated);
		    
		    if(rows_updated>0)
		    {
		    	updated = true;
		    }
		    
	    }		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updated;
	}

	@Override
	public boolean deleteTransaction(int transactionId) {
		
		boolean deleted = false;
		
		Connection connection = Connect.connection;
		String DELETE_TRANSACTION ="DELETE FROM TRANSACTIONS WHERE transaction_id=?";
	    PreparedStatement ps;    
	    
	    try {
			ps = connection.prepareStatement(DELETE_TRANSACTION);
			ps.setInt(1,transactionId);
		  
		    int rows_deleted =ps.executeUpdate();		    
		    //System.out.println("Rows deleted:"+rows_deleted);
		    
		    if(rows_deleted>0)
		    {
		    	deleted = true;
		    }
		    
	    }		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deleted;
	}

	@Override
	public void truncateNettingTables() {
		Connection connection = Connect.connection;
		try {
			Statement statement = connection.createStatement();			
			 statement.executeUpdate("delete from funds_netting_result");
			 statement.executeUpdate("delete from securities_netting_result");
			 System.out.println("netting tables truncated");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
