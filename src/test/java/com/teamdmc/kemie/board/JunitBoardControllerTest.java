package com.teamdmc.kemie.board;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.teamdmc.kemie.board.domain.BoardVO;
import com.teamdmc.kemie.cmn.SearchVO;
import com.teamdmc.kemie.dao.BoardDao;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

public class JunitBoardControllerTest {
	

	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	WebApplicationContext webApplicationContext;
	
	MockMvc mockMvc;
	
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
		
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		LOG.debug("webApplicationContext : " + webApplicationContext);
		LOG.debug("mockMvc : " + mockMvc);
		LOG.debug("dao : " + dao);
		
		assertNotNull(webApplicationContext);
		assertNotNull(mockMvc);
		assertNotNull(dao);

	}

	@Test
	public void doRetrieve() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/board/doRetrieve.do")
				.param("pageSize", String.valueOf(searchVO.getPageSize()))
				.param("pageNum", String.valueOf(searchVO.getPageNum()))
				.param("searchDiv", searchVO.getSearchDiv())
				.param("searchWord", searchVO.getSearchWord());				
				
		ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
		String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		
		LOG.debug("----------------------------");
		LOG.debug("---result--- : " + result);
		LOG.debug("----------------------------");
		
		Gson gson = new Gson();
		List<BoardVO> list = gson.fromJson(result, new TypeToken<List<BoardVO>>() {}.getType());
		
		for(BoardVO vo:list) {
			LOG.debug("vo = " + vo);
		}
	}

}
