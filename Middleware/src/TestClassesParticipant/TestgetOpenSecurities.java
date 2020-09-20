package TestClassesParticipant;
import java.util.ArrayList;
import java.util.List;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.pojo.Security;

public class TestgetOpenSecurities {
	
	public static void main(String args[])
	{

		List<Security> result_list=new ArrayList<Security>();
		ParticipantDAO dao=new ParticipantDaoImpl();
		result_list=dao.getOpeningSecurities(102);
	 for(Security s:result_list)
	 {
		 s.toString(); 
		 System.out.println(s);
	 }

}
}
