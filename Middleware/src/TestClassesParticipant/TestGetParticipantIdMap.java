package TestClassesParticipant;
import java.util.HashMap;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestGetParticipantIdMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParticipantDAO dao= new ParticipantDaoImpl();
		HashMap<String, Integer> participantIdName = new HashMap<String,Integer>();
		participantIdName=dao.getParticipantIdMap();
		System.out.println(participantIdName);
	}

}
