package TestClassesParticipant;
import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestLoginInfoUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ParticipantDAO dao= new ParticipantDaoImpl();
		int participantId=102;
		String password="hellogs1234";
		
		boolean updated=dao.updateLoginInfo(participantId, password);
		
		if(updated)
		{
			System.out.println("Login info updated successfully");
		}
		else
		{
			System.out.println("sorry something went wrong");
		}
	}

}
