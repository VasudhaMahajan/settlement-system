package TestClassesTransaction;

import java.util.HashMap;

import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;

public class TestGenerateNettingTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TransactionDAO dao=new TransactionDAOImpl();
		HashMap<Integer, HashMap<Integer, Integer>> hashMap=new HashMap<Integer, HashMap<Integer, Integer>>();
		HashMap<Integer, Integer> map=new HashMap<>();
		map.put(2, 200);
		map.put(1, 200);
		hashMap.put(101,map);
		map=new HashMap<>();
		map.put(3, 250);
		map.put(1, 150);
		hashMap.put(101, map);
		map=new HashMap<>();
		map.put(2, 190);
		map.put(3, 50);
		hashMap.put(103, map);
	
		boolean isGenerated=dao.generateNettingTable(hashMap);
		if(isGenerated)
		{
			System.out.println("netting shares table generated sucessfully");
		}
		else
		{
			System.out.println("please try again");
		}
		
	}

}
