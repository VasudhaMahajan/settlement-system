package TestClassesParticipant;
import java.util.ArrayList;
import java.util.List;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestGetAllParticipantsName {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list=new ArrayList<>();
		ParticipantDAO dao=new ParticipantDaoImpl();
		list=dao.getAllParticipantName();
		System.out.println(list);
		
	}

}
