package TestClassesSecurity;

import java.util.HashMap;

import com.dao.SecurityDAO;
import com.dao.SecurityDAOImpl;

public class TestGetCorporateActionById {

	public static void main(String[] args) {
		
		SecurityDAO dao=new SecurityDAOImpl();
		HashMap<Integer, Integer> hashMap=new HashMap();
		hashMap=dao.getCorporateActionById(1);
		System.out.println(hashMap);

	}

}
