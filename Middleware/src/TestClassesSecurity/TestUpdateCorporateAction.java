package TestClassesSecurity;

import com.dao.SecurityDAO;
import com.dao.SecurityDAOImpl;
import com.pojo.Security;

public class TestUpdateCorporateAction {

	public static void main(String[] args) {
		
		SecurityDAO dao=new SecurityDAOImpl();
		Security s=new Security("db",2,34,0,1,0);
		boolean a=dao.updateCorporateAction(s);
	}

}
