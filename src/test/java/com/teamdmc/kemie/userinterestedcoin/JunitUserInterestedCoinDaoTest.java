///**
//* <pre>
//* com.pcwk.ehr.uic
//* Class Name : JunituicDaoTest.java
//* Description:
//* Author: ITSC
//* Since: 2022/06/24
//* Version 0.1
//* Copyright (c) by H.R.KIM All right reserved.
//* Modification Information
//* 수정일   수정자    수정내용
//*-----------------------------------------------------
//*2022/06/24 최초생성
//*-----------------------------------------------------
//* </pre>
//*/
//package com.teamdmc.kemie.userinterestedcoin;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.teamdmc.kemie.cmn.SearchVO;
//import com.teamdmc.kemie.dao.UserInterestedCoinDao;
//import com.teamdmc.kemie.userinterested.domain.UserInterestedVO;
//
//@RunWith(SpringJUnit4ClassRunner.class) // Junit의 기능을 Spring으로 확대시킴.
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
//"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"}) // ContextConfiguration을 통해 applicationContext.xml을 로딩하여 공유
//public class JunitUserInterestedCoinDaoTest {
//	final Logger LOG = LogManager.getLogger(this.getClass());	
//	
//	@Autowired
//	ApplicationContext context;
//
//	@Autowired
//	UserInterestedCoinDao dao;
//
//	UserInterestedVO uic01;
//	UserInterestedVO uic02;
//	UserInterestedVO uic03;
//	
//	SearchVO searchVO;
//	
//	@Before
//	public void setUp() throws Exception {
//		LOG.debug("============================");
//		LOG.debug("=0. setUp()=");
//		LOG.debug("============================");
//		
//		searchVO = new SearchVO(10, 1, "", "");
//		
//		uic01 = new UserInterestedVO(1, "한글_암호화폐_이름01","","", "영어_암호화폐_이름01", "userId01");
//		uic02 = new UserInterestedVO(2, "한글_암호화폐_이름02","","", "영어_암호화폐_이름02", "userId011");
//		uic03 = new UserInterestedVO(3, "한글_암호화폐_이름03","","", "영어_암호화폐_이름03", "userId012");
//		
//		LOG.debug("dao: "+dao);
//		LOG.debug("context: "+context);
//		
//		assertNotNull(context);
//		assertNotNull(dao);
//	}
//
//	@Test
//	@Ignore
//	public void doUpdate() throws SQLException{
//		/* doUpdate() 테스트 시나리오
//		 * 1. 데이터 삭제 
//		 * 2. 데이터 등록
//		 * 3. 데이터 수정
//		 * 4. 데이터 확인
//		 */
//		LOG.debug("============================");
//		LOG.debug("=1. doUpdate()=");
//		LOG.debug("============================");
//		
//		// [1] 데이터 삭제
//		dao.doDelete(uic01);
//		dao.doDelete(uic02);
//		dao.doDelete(uic03);
//		
//		// [2] 데이터  등록
//		dao.doInsert(uic01);
//		dao.doInsert(uic02);
//		dao.doInsert(uic03);
//		
//		// [3] 데이터 수정
//		String modiStr = "_U";
//		uic01.setUicMarket(uic01.getUicMarket()+modiStr);
//		uic01.setUicNowPrice(uic01.getUicNowPrice()+modiStr);
//		uic01.setUicToFixed(uic01.getUicToFixed()+modiStr);
//		uic01.setUicPrice24h(uic01.getUicPrice24h()+modiStr);
//		uic01.setuId(uic01.getuId()+modiStr);
//
//		
//		// [4] 데이터 확인
//		UserInterestedVO vsVO = dao.doSelectOne(uic01);
//		isSameuic(vsVO, uic01);
//	}
//	
//	@Test
//	@Ignore
//	public void doRetrieve() throws SQLException{
////		searchVO.setSearchDiv("30");
////		searchVO.setSearchWord("tubus");
//	}
//	
//	@Test
//	public void addAndGet() throws SQLException {
//		/* addAndGet() 테스트 시나리오
//		 * 1. 데이터 삭제 
//		 * 2. 데이터 등록
//		 * 3. 데이터 조회
//		 * 4. 데이터 확인
//		 */
//		LOG.debug("============================");
//		LOG.debug("=1. addAndGet()=");
//		LOG.debug("============================");
//		
//		// [1] 데이터 삭제
//		dao.doDelete(uic01);
//		dao.doDelete(uic02);
//		dao.doDelete(uic03);
//		
//		// [2] 데이터  등록
//		dao.doInsert(uic01);
//		dao.doInsert(uic02);
//		dao.doInsert(uic03);
//		
//		// [3] 데이터 조회
//		UserInterestedVO vsuic01 = dao.doSelectOne(uic01);
//		UserInterestedVO vsuic02 = dao.doSelectOne(uic02);
//		UserInterestedVO vsuic03 = dao.doSelectOne(uic03);
//		
//		// [4] 데이터 확인
//		isSameuic(vsuic01, uic01);
//		isSameuic(vsuic02, uic02);
//		isSameuic(vsuic03, uic03);
//	}
//	
//	@Test
//	@Ignore
//	public void insert() throws SQLException {
//		/* insert() 테스트 시나리오
//		 * 1. 데이터 삭제 
//		 * 2. 데이터 등록 등록
//		 * 3. 한 건 조회: user01
//		 * 4. 데이터 삭제
//		 */
//		LOG.debug("============================");
//		LOG.debug("=1. insert()=");
//		LOG.debug("============================");
//		
//		// [1] 데이터 삭제
//		dao.doDelete(uic01);
//		dao.doDelete(uic02);
//		dao.doDelete(uic03);
//		
//		// [2] 데이터 입력
//		dao.doInsert(uic01);
//		dao.doInsert(uic02);
//		dao.doInsert(uic03);
//	}
//	
//	private void isSameuic(UserInterestedVO vsVO, UserInterestedVO orgVO) {
//		assertEquals(vsVO.getUicMarket(), orgVO.getUicMarket());
//		assertEquals(vsVO.getUicNowPrice(), orgVO.getUicNowPrice());
//		assertEquals(vsVO.getUicToFixed(), orgVO.getUicToFixed());
//		assertEquals(vsVO.getUicPrice24h(), orgVO.getUicPrice24h());
//		assertEquals(vsVO.getuId(), orgVO.getuId());
//	}
//}