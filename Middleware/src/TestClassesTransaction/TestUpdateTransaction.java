package TestClassesTransaction;

import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.pojo.Transaction;

public class TestUpdateTransaction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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
		
		if(isAdded)
		{
			System.out.println("transaction updated sucessfully");
		}
		else
		{
			System.out.println("please try again");
		}
		
	}

}
