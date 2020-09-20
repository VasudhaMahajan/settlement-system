package TestClassesParticipant;
import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.pojo.Participant;

public class TestParticipantUpdate {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean isUpdated=dao.updateParticipant(102, "gs1234@gs.com", "676432");
		
		if(isUpdated)
		{
			System.out.println("participant updated " );
		}
		else
		{
			System.out.println("please try again updation failed");
		}
	}

}
