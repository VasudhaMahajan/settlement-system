package TestClassesParticipant;
import java.util.ArrayList;
import java.util.List;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestPerformJoinBalance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParticipantDAO dao=new ParticipantDaoImpl();
		List<List<Integer>> joinedList = new ArrayList<List<Integer>>();
		joinedList=dao.perfomJoinBalanceSecurity();
		System.out.println(joinedList);
	}

}
