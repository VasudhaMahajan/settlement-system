package TestClassesSecurity;

import java.util.ArrayList;
import java.util.List;


import com.dao.SecurityDAO;
import com.dao.SecurityDAOImpl;

public class TestGetAllSecurityName {

	public static void main(String[] args) {
		List<String> list=new ArrayList<>();
		SecurityDAO dao=new SecurityDAOImpl();
		list=dao.getAllSecurityName();
		System.out.println(list);

	}

}
