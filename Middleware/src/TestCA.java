import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.logic.SettlementSystem;
import com.logic.SettlementSystemImpl;

public class TestCA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		TransactionDAO transactionDAO = new TransactionDAOImpl();
		ParticipantDAO participantDao = new ParticipantDaoImpl();
		SettlementSystem settlementSystem = new SettlementSystemImpl();
		settlementSystem.handleCorporateAction();
	}

}
