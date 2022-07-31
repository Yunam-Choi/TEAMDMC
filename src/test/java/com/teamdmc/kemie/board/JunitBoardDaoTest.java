package com.teamdmc.kemie.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.teamdmc.kemie.board.domain.BoardVO;
import com.teamdmc.kemie.cmn.SearchVO;
import com.teamdmc.kemie.dao.BoardDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
								   "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

public class JunitBoardDaoTest {

final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired // 컨텍스트 프레임워크는 변수 타입과 일치하는 컨텍스트 내의 빈을 찾고 변수에 주입함
	ApplicationContext context;
	
	@Autowired
	BoardDao dao;
	
	BoardVO board01;
	BoardVO board02;
	BoardVO board03;
	
	SearchVO searchVO;
	
	@Before
	public void setUp() throws Exception {
		LOG.debug("-----------------");
		LOG.debug("--- 0.setUp() ---");
		LOG.debug("-----------------");
		searchVO = new SearchVO(10, 1, "", "");
		
		board01 = new BoardVO(1, "테스트1", "테스트테스트", 0, "테스트", "날짜사용안함", "테스트", "날짜사용안함");
		board02 = new BoardVO(2, "테스트10", "테스트테스트", 0, "테스트", "날짜사용안함", "테스트", "날짜사용안함");
		board03 = new BoardVO(3, "테스트100", "테스트테스트", 0, "테스트", "날짜사용안함", "테스트", "날짜사용안함");
		
		
		LOG.debug("context : " + context);
		LOG.debug("dao : " + dao);
		
		assertNotNull(context);
		assertNotNull(dao);
	}
	
	@Test
	public void updateReadCnt() throws SQLException {
		LOG.debug("--------------------");
		LOG.debug("---1.updateReadCnt---");
		LOG.debug("--------------------");
		
		// 1. 데이터 삭제
		dao.deleteAll();
		assertEquals(0, dao.getCount(board01));
		
		// 2. 한 건 입력
		dao.doInsert(board01);
		assertEquals(1, dao.getCount(board01));
		
		// 3. 단 건 조회
		dao.doSelectOne(board01);
		
		// 4. 조회 카운트 증가
		dao.updateReadCnt(board01);
		
		// 5. 단 건 조회 비교
		BoardVO vsVO = dao.doSelectOne(board01);
		assertEquals(1, vsVO.getbReadCnt());
	}

	@Test
	@Ignore
	public void addAndGet() throws SQLException {
	
		dao.doInsert(board01);
		dao.doDelete(board01);
		
	}
	
	@Test
	@Ignore
	public void doUpdate() throws SQLException{
	
		// 1. 전체 삭제
		dao.deleteAll();
		assertEquals(0, dao.getCount(board01));
		
		// 2. 신규 등록 : user01
		dao.doInsert(board01);
		assertEquals(1, dao.getCount(board01));
		
		// 3. 한건 조회 : user01
		BoardVO vsVO = dao.doSelectOne(board01);
		isSameData(vsVO, board01);
		
		// 4. user01 데이터 수정
		String upStr = "_U";
		
		board01.setbTitle(board01.getbTitle()+upStr);
		board01.setbContents(board01.getbContents()+upStr);
		
		// 5. 수정
		dao.doUpdate(board01);
		
		// 6. 한건 조회 비교
		vsVO = dao.doSelectOne(board01);
		isSameData(vsVO, board01);
				
	}
	
	@Test
	public void doRetrieve() throws SQLException{
		List<BoardVO> list = dao.doRetrieve(searchVO);
		for(BoardVO vo:list) {
			LOG.debug("vo=" + vo);
		}
	}

	// 리턴된 값이 같은지 확인하기 위해 생성한 메서드!
	private void isSameData(BoardVO vsVO, BoardVO orgVO) {
		assertEquals(vsVO.getbTitle(), orgVO.getbTitle());
		assertEquals(vsVO.getbContents(), orgVO.getbContents());
		assertEquals(vsVO.getbReadCnt(), orgVO.getbReadCnt());
		assertEquals(vsVO.getuNick(), orgVO.getuNick());
		assertEquals(vsVO.getuId(), orgVO.getuId());
	}

}
