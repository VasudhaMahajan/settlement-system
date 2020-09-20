package TestClassesParticipant;
import java.util.ArrayList;
import java.util.List;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.pojo.Security;



public class TestgetNetSecurities {

	public static void main(String[] args) {
		List<Security> result_list=new ArrayList<Security>();
		ParticipantDAO dao1=new ParticipantDaoImpl();
		result_list=dao1.getNetSecurities(1);
		System.out.println(result_list);
		 for(Security s1:result_list)
		 {
			 s1.toString(); 
			 System.out.println(s1);
		 }

	}

}
