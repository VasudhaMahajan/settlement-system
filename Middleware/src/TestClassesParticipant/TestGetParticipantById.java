package TestClassesParticipant;
import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.pojo.Participant;

public class TestGetParticipantById {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		ParticipantDAO dao= new ParticipantDaoImpl();
		Participant participant=null;
		participant=dao.getParticipantById(1);
//		System.out.println("participant name:"+ participant.getParticipantName());
//		System.out.println(participant.toString());
		

	}

}
