package TestClassesParticipant;
import java.util.ArrayList;
import java.util.List;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.pojo.Participant;
import com.pojo.Security;

public class TestLoginInfoAdd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ParticipantDAO dao= new ParticipantDaoImpl();
		int participantId=102;
		String username="gs123";
		String password="hellogs";
		
		boolean inserted=dao.addLoginInfo(participantId, username, password);
		
		if(inserted)
		{
			System.out.println("Login info added successfully");
		}
		else
		{
			System.out.println("sorry something went wrong");
		}
	}

}
