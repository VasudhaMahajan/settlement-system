package TestClassesTransaction;

import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.pojo.Transaction;

public class TestDeleteTransaction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TransactionDAO dao=new TransactionDAOImpl();
		
		boolean isDeleted=dao.deleteTransaction(24);
		
		if(isDeleted)
		{
			System.out.println("transaction deleted sucessfully");
		}
		else
		{
			System.out.println("please try again");
		}
		
	}

}
