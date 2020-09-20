package TestClassesParticipant;
import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestGetParticipantNameById {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ParticipantDAO dao=new ParticipantDaoImpl();
		String participantName=dao.getParticipantNameById(102);
		System.out.println(participantName);
	}

}
