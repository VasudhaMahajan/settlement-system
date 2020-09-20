package TestClassesParticipant;
import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestUpdateFunds {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean isUpdated=dao.updateFundBalance(102, 200.0f);
		
		if(isUpdated)
		{
			System.out.println("participant funds updated " );
		}
		else
		{
			System.out.println("please try again updation failed");
		}
	}

}
