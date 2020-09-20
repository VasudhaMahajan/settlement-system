package TestClassesSecurity;

import java.util.HashMap;

import com.dao.SecurityDAO;
import com.dao.SecurityDAOImpl;
import com.pojo.Security;

public class TestDeleteCorporateAction {
	
	public static void main(String[] args) {
		
	
		
			
			SecurityDAO dao=new SecurityDAOImpl();
			
			boolean a=dao.deleteCorporateAction(1);
		
}
}