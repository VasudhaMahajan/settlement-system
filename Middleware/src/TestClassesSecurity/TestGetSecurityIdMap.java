package TestClassesSecurity;

import java.util.HashMap;

import com.dao.SecurityDAO;
import com.dao.SecurityDAOImpl;

public class TestGetSecurityIdMap {

	public static void main(String[] args) {
		HashMap<String, Integer> securityIdName = new HashMap<String,Integer>();
		SecurityDAO dao=new SecurityDAOImpl();
		securityIdName=dao.getSecurityIdMap();
		System.out.println(securityIdName);
		


	}

}
