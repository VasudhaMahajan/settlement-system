package TestClassesTransaction;

import java.util.ArrayList;
import java.util.List;

import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.pojo.Transaction;

public class TestGetAllTransactions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TransactionDAO dao=new TransactionDAOImpl();
		List<Transaction> listOfTransactions = new ArrayList<Transaction>();
		listOfTransactions=dao.getAllTransaction();
		int size=listOfTransactions.size();
		int i=0;
		while(i<size)
		{
			System.out.println(listOfTransactions.get(i)+"\n");
			i++;
		}
	}

}
