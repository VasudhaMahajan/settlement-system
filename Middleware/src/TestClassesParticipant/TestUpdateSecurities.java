package TestClassesParticipant;
import java.util.ArrayList;
import java.util.List;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.pojo.Security;

public class TestUpdateSecurities {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ParticipantDAO dao= new ParticipantDaoImpl();
		int participantId=102;
		List<Security> list=new ArrayList<>();
		list.add(0, new Security("itc",3,250));
		list.add(1, new Security("apple",1,150));
		
		boolean isUpdated=dao.updateSecurityBalance(102, list);
		
		if(isUpdated)
		{
			System.out.println("participant shares updated " );
		}
		else
		{
			System.out.println("please try again updation failed");
		}
	
	}

}
