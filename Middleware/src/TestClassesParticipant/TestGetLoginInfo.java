package TestClassesParticipant;
import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestGetLoginInfo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username="gs123";
		String password="hellogs1234";
		
		ParticipantDAO dao= new ParticipantDaoImpl();
		Integer obj= dao.getLoginInfo(username, password);
		
		if(obj!=null)
		{
			String s= Integer.toString(obj);
			int participantId=Integer.parseInt(s);
			System.out.println("participant id="+participantId);
		}
		else
		{
			System.out.println("error");
		}
		
		
	}

}
