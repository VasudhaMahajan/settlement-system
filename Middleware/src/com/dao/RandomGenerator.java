package com.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; 
import java.util.Random;

import com.pojo.Transaction;

public class RandomGenerator {

	public static void main(String[] args) 
    { 
		
	  for(int i =0;i<100;i++)
	  {
		  List<String> listOfParticipants = new ArrayList<>(); 
	        List<String> listOfSecurities = new ArrayList<>(); 
	        
	        listOfParticipants.add("GS"); 
	        listOfParticipants.add("DB"); 
	        listOfParticipants.add("CITI"); 
	        listOfParticipants.add("JPMC"); 
	        listOfParticipants.add("NT");

	        listOfSecurities.add("APPLE");
	        listOfSecurities.add("GE");
	        listOfSecurities.add("FB");
	        listOfSecurities.add("WALMART");
	        listOfSecurities.add("LINKEDIN");
	        
	        HashMap<String,Integer> mapOfparticipants = new HashMap();
	        mapOfparticipants.put("CITI", 1);
	        mapOfparticipants.put("GS", 2);
	        mapOfparticipants.put("DB", 3);
	        mapOfparticipants.put("BARCLAYS", 4);
	        mapOfparticipants.put("UBS", 5);
	        
	        HashMap<String,Integer> mapOfsecurities = new HashMap();
	        mapOfsecurities.put("FB", 1);
	        mapOfsecurities.put("GE", 2);
	        mapOfsecurities.put("LINKEDIN", 3);
	        mapOfsecurities.put("WALMART", 4);
	        
	        Random rand = new Random(); 
	        
	        int ssinId = rand.nextInt(1000);
	        String sellerName = listOfParticipants.get(rand.nextInt(listOfParticipants.size()-1));
	        int sellerId = mapOfparticipants.get(sellerName);
	        String buyerName = listOfParticipants.get(rand.nextInt(listOfParticipants.size()-1));
	        int buyerId = mapOfparticipants.get(buyerName);
	        String securityName = listOfSecurities.get(rand.nextInt(listOfParticipants.size()-1));
	        int securityId = mapOfsecurities.get(securityName);
	        int securityQty = rand.nextInt(200);
	        float min = 78;
	        float max = 178;
	        float securityRate = (float) (min + Math.random() * (max - min));
	        securityRate = (float) (Math.round(securityRate * 100.0) / 100.0);
	        double transAmt = securityQty*securityRate;
	        transAmt = (Math.round(transAmt * 100.0) / 100.0);
	        
	        int rowAdded=0;
	        String INSERT_TRANSACTION="insert into transactions values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps;
			try {
				ps = Connect.connection.prepareStatement(INSERT_TRANSACTION);
				
				ps.setInt(1,i);
				ps.setInt(2, ssinId);
				ps.setInt(3, sellerId);
				ps.setString(4, sellerName);
				ps.setInt(5, buyerId);
				ps.setString(6, buyerName);
				ps.setInt(7, securityId);
				ps.setString(8, securityName);
				ps.setInt(9, securityQty);
				ps.setDouble(10, securityRate);
				
				rowAdded=ps.executeUpdate();
				
				System.out.println("insertion made:"+rowAdded);
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        
	       // Transaction transaction1 = new Transaction(i, ssinId, sellerId, sellerName, buyerId, buyerName, securityId, securityName, securityQty, securityRate, transAmt);
	  
	      //  System.out.println("Transaction:"+ transaction1.toString());
	  
	        
	        
	  }
          
        
    } 

}

    