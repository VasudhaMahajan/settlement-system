package TestClassesParticipant;
import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestgetOpenFunds {

	public static void main(String[] args) {
		
		ParticipantDAO dao=new ParticipantDaoImpl();
		 double funds=dao.getOpeningFunds(102);
		 System.out.println(funds);

	}

}
