package com.unittesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dao.SecurityDAO;
import com.dao.SecurityDAOImpl;
import com.pojo.Security;

class TestSecurityDaoImpl {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAllSecutityName_positive() {
		List<String> list=new ArrayList<>();
		SecurityDAO dao=new SecurityDAOImpl();
		list=dao.getAllSecurityName();
		assertEquals(3,list.size());
		
	}
	
	@Test
	void testGetSecurityIdMap_positive() {
		HashMap<String, Integer> securityIdName = new HashMap<String,Integer>();
		SecurityDAO dao=new SecurityDAOImpl();
		securityIdName=dao.getSecurityIdMap();
		assertEquals(3,securityIdName.size());
		
	}

	@Test
	void testGetAllCorporateAction() {
		HashMap<Integer,HashMap<Integer, Integer>> hashMap=new HashMap<>();
		SecurityDAO dao=new SecurityDAOImpl();
		hashMap=dao.getAllCorporateAction();
		assertEquals(4,hashMap.size());
	}

	@Test
	void testUpdateCorporateAction_positive() {
		SecurityDAO dao=new SecurityDAOImpl();
		Security s=new Security("db",2,34,0,1,0);
		boolean a=dao.updateCorporateAction(s);
		assertEquals(true,a);
		
	}
	@Test
	void testUpdateCorporateAction_negative() {
		SecurityDAO dao=new SecurityDAOImpl();
		Security s=new Security("db",2,34,0,1,0);
		boolean a=dao.updateCorporateAction(s);
		assertEquals(false,a);
		
	}

	@Test
	void testDeleteCorporateAction_positive() {
		
		SecurityDAO dao=new SecurityDAOImpl();
		boolean a=dao.deleteCorporateAction(1);
		assertEquals(true,a);
	}
	@Test
	void testDeleteCorporateAction_negative() {
		
		SecurityDAO dao=new SecurityDAOImpl();
		boolean a=dao.deleteCorporateAction(66);
		assertEquals(false,a);
	}

	@Test
	void testGetCorporateActionById_positive() {
		SecurityDAO dao=new SecurityDAOImpl();
		HashMap<Integer, Integer> hashMap=new HashMap();
		hashMap=dao.getCorporateActionById(1);
		assertEquals(true,hashMap.containsKey(2));
		
	}
	@Test
	void testGetCorporateActionById_negative() {
		SecurityDAO dao=new SecurityDAOImpl();
		HashMap<Integer, Integer> hashMap=new HashMap();
		hashMap=dao.getCorporateActionById(99);
		
	}

}
