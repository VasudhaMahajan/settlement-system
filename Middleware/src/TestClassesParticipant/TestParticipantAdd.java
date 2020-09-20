package TestClassesParticipant;
import java.util.ArrayList;
import java.util.List;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.pojo.Participant;
import com.pojo.Security;

public class TestParticipantAdd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParticipantDAO dao= new ParticipantDaoImpl();
		int participantID=102;
		String participantName="gs";
		String emailID="gs93@gs.com";
		String contactNumber="7833456";
		float funds=100;
		float feeForSettlement=6.0f;
		List<Security> listOfSecurities=new ArrayList<>();
		listOfSecurities.add(0,new Security("itc",3,50));
		listOfSecurities.add(1,new Security("fb",2,190));
		
		
		Participant participant=new Participant(participantID, participantName, 
				emailID, contactNumber, funds, feeForSettlement, listOfSecurities);
		boolean inserted=dao.addParticipant(participant);
		
		if(inserted)
		{
			System.out.println("participant added successfully");
		}
		else
		{
			System.out.println("sorry something went wrong");
		}
	}

}
