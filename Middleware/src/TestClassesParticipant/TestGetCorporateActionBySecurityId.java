package TestClassesParticipant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;

public class TestGetCorporateActionBySecurityId {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ParticipantDAO dao= new ParticipantDaoImpl();
		JSONArray corporateActionSecId = new JSONArray();
		corporateActionSecId=dao.getCorporateActionByParticipantId(1);
//		System.out.println(corporateActionSecId);
	}

}
