package com.unittesting;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dao.ParticipantDAO;
import com.dao.ParticipantDaoImpl;
import com.pojo.Participant;
import com.pojo.Security;

class TestParticipantDaoImpl {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetOpeningSecurities_positive() {
		
		List<Security> result_list=new ArrayList<Security>();
		ParticipantDAO dao=new ParticipantDaoImpl();
		result_list=dao.getOpeningSecurities(102);
		assertEquals(2,result_list.size());
		
	}
	
	@Test
	void testGetOpeningSecurities_negative() {
		List<Security> result_list=new ArrayList<Security>();
		ParticipantDAO dao=new ParticipantDaoImpl();
		result_list=dao.getOpeningSecurities(103);
		assertEquals(0,result_list.size());
	}

	
	
	
	//**************************************************************************************************
		

	@Test
	void testGetNetSecurities_positive() {
		
		List<Security> result_list=new ArrayList<Security>();
		ParticipantDAO dao=new ParticipantDaoImpl();
		result_list=dao.getNetSecurities(102);
		assertEquals(2,result_list.size());
		
	}
	
	@Test
    void testGetNetSecurities_negative() {
		
		List<Security> result_list=new ArrayList<Security>();
		ParticipantDAO dao=new ParticipantDaoImpl();
		result_list=dao.getNetSecurities(103);
		assertEquals(0,result_list.size());
		
	}
	
	
	
	//**************************************************************************************************
		

    @Test
	void testGetOpeningFunds_positive() {
		ParticipantDAO dao=new ParticipantDaoImpl();
		 double funds=dao.getOpeningFunds(102);
		 assertEquals(200,funds);
	}
	
	@Test
	void testGetOpeningFunds_negative() {
		ParticipantDAO dao=new ParticipantDaoImpl();
		 double funds=dao.getOpeningFunds(103);
		 assertEquals(0,funds);
	}
	
	
	
	//**************************************************************************************************
		

	@Test
	void testGetNetFunds_positive() {
		ParticipantDAO dao=new ParticipantDaoImpl();
		 double funds=dao.getNetFunds(102);
		 assertEquals(400,funds);
	}
	
	@Test
	void testGetNetFunds_negative() {
		ParticipantDAO dao=new ParticipantDaoImpl();
		 double funds=dao.getNetFunds(103);
		 assertEquals(0,funds);
	}
	
	
	
	//**************************************************************************************************
		

	@Test
	void testUpdateSecurityBalance_positive() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		List<Security> list=new ArrayList<>();
		list.add(0, new Security("itc",3,250));
		list.add(1, new Security("apple",1,150));
		boolean isUpdated=dao.updateSecurityBalance(102, list);
		
		assertEquals(true, isUpdated);
	}
	@Test
	void testUpdateSecurityBalance_negative() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		List<Security> list=new ArrayList<>();
		list.add(0, new Security("itc",3,250));
		list.add(1, new Security("apple",1,150));
		boolean isUpdated=dao.updateSecurityBalance(103, list);
		
		assertEquals(false, isUpdated);
	}
	
	
	//**************************************************************************************************
		

	@Test
	void testUpdateFundBalance_positive() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean isUpdated=dao.updateFundBalance(102, 200.0f);
		assertEquals(true, isUpdated);
	}
	
	@Test
	void testUpdateFundBalance_negative() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean isUpdated=dao.updateFundBalance(103, 200.0f);
		assertEquals(false, isUpdated);
	}
	
	
	//**************************************************************************************************
		

	@Test
	void testUpdateParticipant_positive() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean isUpdated=dao.updateParticipant(102, "gs1234@gs.com", "676432");
		assertEquals(true, isUpdated);
		
	}
	
	@Test
	void testUpdateParticipant_negative() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean isUpdated=dao.updateParticipant(103, "gs1234@gs.com", "676432");
		assertEquals(false, isUpdated);
		
	}

	
	
	
	//**************************************************************************************************
		

	@Test
	void testAddParticipant_positive() {
		
		List<Security> listOfSecurities=new ArrayList<>();
		listOfSecurities.add(0,new Security("itc",3,50));
		listOfSecurities.add(1,new Security("fb",2,190));
		Participant participant= new Participant(103,"jpmc","jpmc@citi.com","7833456",100,6.0f,listOfSecurities);
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean inserted=dao.addParticipant(participant);
		assertEquals(true, inserted);
		
	}
	@Test
	void testAddParticipant_negative() {
		
		List<Security> listOfSecurities=new ArrayList<>();
		listOfSecurities.add(0,new Security("itc",3,50));
		listOfSecurities.add(1,new Security("fb",2,190));
		Participant participant= new Participant(102,"gs","gs93@gs.com","7833456",100,6.0f,listOfSecurities);
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean inserted=dao.addParticipant(participant);
		assertEquals(false, inserted);
		
	}
	
	
	//**************************************************************************************************
		

	@Test
	void testAddLoginInfo_positive() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		int participantId=103;
		String username="jpmc123";
		String password="hellojp";
		
		boolean inserted=dao.addLoginInfo(participantId, username, password);
		assertEquals(true, inserted);
		
	}
	@Test
	void testAddLoginInfo_negative() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		int participantId=102;
		String username="gs123";
		String password="hellogs";
		
		boolean inserted=dao.addLoginInfo(participantId, username, password);
		assertEquals(false, inserted);
		
	}
	
	
	
	//**************************************************************************************************
		
	@Test
	void testUpdateLoginInfo_positive() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		int participantId=102;
		String password="hellogs1234";
		
		boolean updated=dao.updateLoginInfo(participantId, password);
		assertEquals(true, updated);
	}
	@Test
	void testUpdateLoginInfo_negative() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		int participantId=103;
		String password="hellogs1234";
		
		boolean updated=dao.updateLoginInfo(participantId, password);
		assertEquals(false, updated);
	}
	
	
	
	//**************************************************************************************************
		
	@Test
	void testGetParticipantById_positive() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		Participant participant=dao.getParticipantById(102);
		assertEquals("gs",participant.getParticipantName());
		
	}
	
	@Test
	void testGetParticipantById_negative() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		Participant participant=dao.getParticipantById(103);
		assertEquals(null,participant.getParticipantName());
		
	}
	
	
	
	//**************************************************************************************************
		

	@Test
	void testGetLoginInfo_positive() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		Integer obj= dao.getLoginInfo("gs123","hellogs1234");
		assertEquals(102,obj);
	}
	
	@Test
	void testGetLoginInfo_negative() {
		ParticipantDAO dao= new ParticipantDaoImpl();
		Integer obj= dao.getLoginInfo("gs12","hellogs1234");
		assertEquals(null,obj);
	}
	
	
	
	//**************************************************************************************************
		

	@Test
	void testGetParticipantNameById_positive() {
//		fail("Not yet implemented");
		ParticipantDAO dao=new ParticipantDaoImpl();
		String participantName=dao.getParticipantNameById(102);
		assertEquals("gs", participantName);
	}
	
	@Test
	void testGetParticipantNameById_negative() {
//		fail("Not yet implemented");
		ParticipantDAO dao=new ParticipantDaoImpl();
		String participantName=dao.getParticipantNameById(103);
		assertEquals(null, participantName);
	}
	
	
	
	//**************************************************************************************************
		

	@Test
	void testGetAllParticipantName_positive() {
//		fail("Not yet implemented");
		List<String> list=new ArrayList<>();
		ParticipantDAO dao=new ParticipantDaoImpl();
		list=dao.getAllParticipantName();
		
		assertEquals("gs", list.get(1));
	}
	
//	@Test
//	void testGetAllParticipantName_negative() {
////		fail("Not yet implemented");
//		List<String> list=new ArrayList<>();
//		ParticipantDAO dao=new ParticipantDaoImpl();
//		list=dao.getAllParticipantName();
//		
//		assertEquals("gs", list.get(1));
//	}
	
	
	
	//**************************************************************************************************
		

	@Test
	void testGetParticipantIdMap_positive() {
//		fail("Not yet implemented");
		ParticipantDAO dao= new ParticipantDaoImpl();
		HashMap<String, Integer> participantIdName = new HashMap<String,Integer>();
		participantIdName=dao.getParticipantIdMap();
		assertEquals(true, participantIdName.containsKey("citi"));
	}
	
	@Test
	void testGetParticipantIdMap_negative() {
//		fail("Not yet implemented");
		ParticipantDAO dao= new ParticipantDaoImpl();
		HashMap<String, Integer> participantIdName = new HashMap<String,Integer>();
		participantIdName=dao.getParticipantIdMap();
		assertEquals(false, participantIdName.containsKey("jpmc"));
	}
	
	
	
	
//**************************************************************************************************
	
	@Test
	void testPerfomJoinBalanceSecurity_positive() {
		ParticipantDAO dao=new ParticipantDaoImpl();
		List<List<Integer>> joinedList = new ArrayList<List<Integer>>();
		joinedList=dao.perfomJoinBalanceSecurity();
		assertEquals(465, joinedList.get(1).get(4));
	}

	
//	@Test
//	void testPerfomJoinBalanceSecurity_negative() {
//		ParticipantDAO dao=new ParticipantDaoImpl();
//		List<List<Integer>> joinedList = new ArrayList<List<Integer>>();
//		joinedList=dao.perfomJoinBalanceSecurity();
//		assertEquals(false, joinedList.get());
	
//**************************************************************************************************
	
	
	@Test
	void testGetCorporateActionByParticipantId_positive() {
//		fail("Not yet implemented");
		
		ParticipantDAO dao= new ParticipantDaoImpl();
		Map<Integer, List<Integer>> corporateActionSecId = new HashMap<>();
		corporateActionSecId=dao.getCorporateActionByParticipantId(102);
		assertEquals(true, corporateActionSecId.containsKey(1));
	}
	
	
	@Test
	void testGetCorporateActionByParticipantId_negative() {
//		fail("Not yet implemented");
		

		ParticipantDAO dao= new ParticipantDaoImpl();
		Map<Integer, List<Integer>> corporateActionSecId = new HashMap<>();
		corporateActionSecId=dao.getCorporateActionByParticipantId(102);
		assertEquals(false, corporateActionSecId.containsKey(2));
	}
	
//**************************************************************************************************

	@Test
	void testUpdateParticipantBalance_positive() {
//		fail("Not yet implemented");
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean actual=dao.updateParticipantBalance(102, 3, 465);
		assertEquals(true, actual);
	}
	
	@Test
	void testUpdateParticipantBalance_negative() {
//		fail("Not yet implemented");
		ParticipantDAO dao= new ParticipantDaoImpl();
		boolean actual=dao.updateParticipantBalance(101, 3, 465);
		assertEquals(false, actual);
	}

}
