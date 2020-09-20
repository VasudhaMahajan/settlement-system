package TestClassesParticipant;
import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestUpdateParticipantBalance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean isUpdated=dao.updateParticipantBalance(102, 3, 560);
		
		if(isUpdated)
		{
			System.out.println("participant share updated " );
		}
		else
		{
			System.out.println("please try again updation failed");
		}
	}

}
