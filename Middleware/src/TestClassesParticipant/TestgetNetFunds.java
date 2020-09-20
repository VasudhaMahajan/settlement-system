package TestClassesParticipant;
import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestgetNetFunds {

	public static void main(String[] args) {
		
		ParticipantDAO dao3=new ParticipantDaoImpl();
		 double funds1=dao3.getNetFunds(102);
		 System.out.println(funds1);

	}

}
