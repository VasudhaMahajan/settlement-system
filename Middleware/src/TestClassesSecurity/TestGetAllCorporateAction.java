package TestClassesSecurity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.dao.SecurityDAO;
import com.dao.SecurityDAOImpl;

public class TestGetAllCorporateAction {

	public static void main(String[] args) {
		HashMap<Integer,HashMap<Integer, Integer>> hashMap=new HashMap<>();
		SecurityDAO dao=new SecurityDAOImpl();
		hashMap=dao.getAllCorporateAction();
		System.out.println(hashMap);
		
	}

}
