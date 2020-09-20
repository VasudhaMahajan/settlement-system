package TestClassesTransaction;

import java.util.HashMap;

import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;

public class TestGenerateNettingFundsTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TransactionDAO dao=new TransactionDAOImpl();
		HashMap<Integer, Double> fundsNettingMap=new HashMap<>();
		fundsNettingMap.put(102, 200.0d);
		fundsNettingMap.put(101, 100.0d);
		fundsNettingMap.put(103, 100.0d);
		boolean isGenerated=dao.generateNettingFundsTable(fundsNettingMap);
		
		if(isGenerated)
		{
			System.out.println("netting funds table generated sucessfully");
		}
		else
		{
			System.out.println("please try again");
		}
	}

}
