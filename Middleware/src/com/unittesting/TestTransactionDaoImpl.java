package com.unittesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.pojo.Transaction;

class TestTransactionDaoImpl {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllTransaction_positive() {
//		fail("Not yet implemented");
		TransactionDAO dao=new TransactionDAOImpl();
		List<Transaction> listOfTransactions = new ArrayList<Transaction>();
		listOfTransactions=dao.getAllTransaction();
		assertEquals(21, listOfTransactions.get(0).getTransactionId());
	}
	
//	@Test
//	void testGetAllTransaction_negative() {
////		fail("Not yet implemented");
//		TransactionDAO dao=new TransactionDAOImpl();
//		List<Transaction> listOfTransactions = new ArrayList<Transaction>();
//		listOfTransactions=dao.getAllTransaction();
//		assertEquals(21, listOfTransactions.get(0).getTransactionId());
//	}
	
	
	//************************************************************************************************

	@Test
	void testGetTransactionByParticipantId_positive() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		List<Transaction> listOfTransactions = new ArrayList<Transaction>();
		listOfTransactions=dao.getTransactionByParticipantId(102);
		assertEquals(21, listOfTransactions.get(0).getTransactionId());
	}
	
	@Test
	void testGetTransactionByParticipantId_negative() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		List<Transaction> listOfTransactions = new ArrayList<Transaction>();
		listOfTransactions=dao.getTransactionByParticipantId(105);
		assertEquals(0, listOfTransactions.size());
	}
	
	//*****************************************************************************************************

	@Test
	void testGenerateNettingTable_positive() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		HashMap<Integer, HashMap<Integer, Integer>> hashMap=new HashMap<Integer, HashMap<Integer, Integer>>();
		HashMap<Integer, Integer> map=new HashMap<>();
		map.put(2, 200);
		map.put(1, 200);
		hashMap.put(101,map);
		map=new HashMap<>();
		map.put(3, 250);
		map.put(1, 150);
		hashMap.put(102, map);
		map=new HashMap<>();
		map.put(2, 190);
		map.put(3, 50);
		hashMap.put(103, map);
		boolean isGenerated=dao.generateNettingTable(hashMap);
		assertEquals(true, hashMap.containsKey(101));
		
	}
	
	@Test
	void testGenerateNettingTable_negative() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		HashMap<Integer, HashMap<Integer, Integer>> hashMap=new HashMap<Integer, HashMap<Integer, Integer>>();
		HashMap<Integer, Integer> map=new HashMap<>();
		map.put(2, 200);
		map.put(1, 200);
		hashMap.put(101,map);
		boolean isGenerated=dao.generateNettingTable(hashMap);
		assertEquals(false, isGenerated);
		
	}
	
	
	//*****************************************************************************************************

	@Test
	void testGenerateNettingFundsTable_positive() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		HashMap<Integer, Double> fundsNettingMap=new HashMap<>();
		fundsNettingMap.put(102, 200.0d);
		fundsNettingMap.put(101, 100.0d);
		fundsNettingMap.put(103, 100.0d);
		boolean isGenerated=dao.generateNettingFundsTable(fundsNettingMap);
		assertEquals(true, isGenerated);
	}
	
	@Test
	void testGenerateNettingFundsTable_negative() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		HashMap<Integer, Double> fundsNettingMap=new HashMap<>();
		fundsNettingMap.put(102, 200.0d);
		boolean isGenerated=dao.generateNettingFundsTable(fundsNettingMap);
		assertEquals(false, isGenerated);
	}
	
	
	//**********************************************************************************************************

	@Test
	void testAddTransaction_positive() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		int tranId = 24;
		int ssinId = 96;
		int sellerId = 103;
		int buyerId = 102;
		int securityId = 3;
		int securityQty = 52;
		double securityPrice = 101.0d;
		double transactionAmt = securityQty*securityPrice;
		
		Transaction transaction = new Transaction(tranId, ssinId, sellerId, buyerId, securityId, securityQty, securityPrice, transactionAmt);
		boolean isAdded=dao.addTransaction(transaction);
		assertEquals(true, isAdded);
	}
	@Test
	void testAddTransaction_negative() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		int tranId = 24;
		int ssinId = 96;
		int sellerId = 103;
		int buyerId = 102;
		int securityId = 3;
		int securityQty = 52;
		double securityPrice = 101.0d;
		double transactionAmt = securityQty*securityPrice;
		
		Transaction transaction = new Transaction(tranId, ssinId, sellerId, buyerId, securityId, securityQty, securityPrice, transactionAmt);
		boolean isAdded=dao.addTransaction(transaction);
		assertEquals(false, isAdded);
	}
	
	//******************************************************************************************************

	@Test
	void testUpdateTransaction_positive() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		int tranId = 24;
		int ssinId = 96;
		int sellerId = 103;
		int buyerId = 102;
		int securityId = 3;
		int securityQty = 60;
		double securityPrice = 101.0d;
		double transactionAmt = securityQty*securityPrice;
		
		Transaction transaction = new Transaction(tranId, ssinId, sellerId, buyerId, securityId, securityQty, securityPrice, transactionAmt);
		boolean isAdded=dao.updateTransaction(transaction);
		assertEquals(true, isAdded);
		
	}
	
	@Test
	void testUpdateTransaction_negative() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		int tranId = 28;
		int ssinId = 96;
		int sellerId = 101;
		int buyerId = 102;
		int securityId = 3;
		int securityQty = 60;
		double securityPrice = 101.0d;
		double transactionAmt = securityQty*securityPrice;
		
		Transaction transaction = new Transaction(tranId, ssinId, sellerId, buyerId, securityId, securityQty, securityPrice, transactionAmt);
		boolean isAdded=dao.updateTransaction(transaction);
		assertEquals(false, isAdded);
		
	}
	
	
	//***********************************************************************************************

	@Test
	void testDeleteTransaction_positive() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		boolean isDeleted=dao.deleteTransaction(24);
		assertEquals(true, isDeleted);
	}
	
	@Test
	void testDeleteTransaction_negative() {
//		fail("Not yet implemented");
		
		TransactionDAO dao=new TransactionDAOImpl();
		boolean isDeleted=dao.deleteTransaction(24);
		assertEquals(false, isDeleted);
	}

}
