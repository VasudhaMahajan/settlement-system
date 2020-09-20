package com.dao;

import java.util.HashMap;
import java.util.List;

import com.pojo.Transaction;

public interface TransactionDAO {

	public List<Transaction> getAllTransaction();
	public List<Transaction> getTransactionByParticipantId(int participantId);
	public boolean generateNettingTable(HashMap<Integer,HashMap<Integer,Integer>> hashMap);
	boolean generateNettingFundsTable(HashMap<Integer, Double> fundsNettingMap);
	public boolean addTransaction(Transaction transaction); //add a new transaction to the table
	public boolean updateTransaction(Transaction transaction); //update existing transaction
	public boolean deleteTransaction(int transactionId);
	public void truncateNettingTables();
	

}