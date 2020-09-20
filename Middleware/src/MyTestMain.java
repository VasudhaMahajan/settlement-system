import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.dao.SecurityDAO;
import com.dao.TransactionDAO;
import com.dao.TransactionDAOImpl;
import com.logic.SettlementSystem;
import com.logic.SettlementSystemImpl;
import com.pojo.Security;
import com.pojo.Transaction;

public class MyTestMain {
	
	public static void main(String[] args) {
		
		TransactionDAO transactionDAO = new TransactionDAOImpl();
		ParticipantDAO participantDao = new ParticipantDaoImpl();
		SettlementSystem settlementSystem = new SettlementSystemImpl();
		//settlementSystem.handleCorporateAction();
	//	List<Transaction> lisOfTransaction =transactionDAO.getAllTransaction();
		//System.out.println("Displaying Today\'s transactions");
		//System.out.println(lisOfTransaction);
		//System.out.println("\n\n\n\n\n");		
		
	//	System.out.println("Displaying Netting Result for each participant");
		//this would be called on clicking of the button NETTINGANDOBLIGATION
		settlementSystem.performNettingProcedure(); //creates netting tables in database
		System.out.println("\n\n\n\n\n");	
			settlementSystem.performSettlement();
		System.out.println("generating CSR");
	}
}
