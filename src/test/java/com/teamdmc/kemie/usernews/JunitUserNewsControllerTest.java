package com.teamdmc.kemie.usernews;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.cmn.SearchVO;
import com.teamdmc.kemie.dao.UserNewsDao;
import com.teamdmc.kemie.usernews.domain.UserNewsVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) //JUnit기능을 스프링 프레임으로 확장!
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		                           "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
}) //applicationContext.xml loading
public class JunitUserNewsControllerTest {
	
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	//브라우저 대역(Mock)
	MockMvc mockMvc;
	
	  @Autowired
	  @Qualifier("userNewsDao")
	  UserNewsDao dao;
	  UserNewsVO  user01;
	  UserNewsVO  user02;
	  UserNewsVO  user03;
	  
	  SearchVO searchVO;
	
	  @Before
	  public void setUp()throws Exception{
		  LOG.debug("====================");
		  LOG.debug("=0.setUp()=");
		  LOG.debug("====================");
		  searchVO = new SearchVO(10, 1, "", "");
		  
		  user01 = new UserNewsVO(1,"1234", "날짜_사용_안함", "teamdmc01");
		  user02 = new UserNewsVO(2,"1234", "날짜_사용_안함", "teamdmc02");
		  user03 = new UserNewsVO(3,"1234", "날짜_사용_안함", "teamdmc03");
		  
		  mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		  LOG.debug("webApplicationContext : "+ webApplicationContext);
		  LOG.debug("mockMvc : "+ mockMvc);
		  
		  assertNotNull(webApplicationContext);
		  assertNotNull(mockMvc);
	  }
	  
	  @Test
	  @Ignore
	  public void idCheck() throws Exception{
		  //1. 기존데이터 삭제
		  //2. 한 건 입력
		  //3. idCheck()
		  
		  //1.
		  dao.doDelete(user01);
		  dao.doDelete(user02);
		  dao.doDelete(user03);
		  
		  //2.
		  dao.doInsert(user01);
		  assertEquals(1, dao.getCount(user01));
		  
		  //호출url, param, 호출방식(get/post)
		  //GET방식으로 : http://localhost:8081/ehr/user/doSelectOne.do?uId=p12
		  MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.
				  get("/idCheck.do").param("uId", user01.getuId());
		 //대역 객체 통해 호출
		  ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
		  String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		  
		  LOG.debug("====================");
		  LOG.debug("result : "+ result);
		  LOG.debug("====================");
		  
		  //jsonString to VO
		  //Gson gson = new Gson();
		  //MessageVO messageVO = gson.fromJson(result, MessageVO.class);
		  //LOG.debug("====================");
		  //LOG.debug("messageVO : "+ messageVO);
		  //LOG.debug("====================");
		  
	      //assertEquals("1", messageVO.getMsgId());
		  
	  }
	  
	  @Test
	  @Ignore
	  public void doRetrieve() throws Exception{
		  //호출url, param, 호출방식(get/post)
		  searchVO.setSearchDiv("10");
		  //searchVO.setSearchWord("p12");
		  
		  MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/doRetrieve.do")
				  .param("pageSize", String.valueOf(searchVO.getPageSize()))
				  .param("pageNum", String.valueOf(searchVO.getPageNum()))
				  .param("SearchDiv", searchVO.getSearchDiv())
				  .param("SearchWord", searchVO.getSearchWord());
						  
		  //대역 객체 통해 호출
		  ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
		  String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();		  
		 
		  LOG.debug("====================");
		  LOG.debug("result : "+ result);
		  LOG.debug("====================");	
		  
		  //jsonString to List<UserVO>
		  Gson gson = new Gson();
		  //gson  List<UserVO> jsonString -> List<UserVO>
		  List<UserNewsVO> list = gson.fromJson(result, new TypeToken<List<UserNewsVO>>() {}.getType());
		  
		  for(UserNewsVO vo :list) {
			  LOG.debug("vo : "+ vo);
		  }
	  }
	  
	  @Test
	  @Ignore
	  public void doUpdate() throws Exception{
		  //1. 기존 데이터 삭제
		  //2. 단건입력
		  //3. 수정
		  //4. 비교
		  
		  //1.
		  doDelete(user01);
		  doDelete(user02);
		  doDelete(user03);
//		  dao.deleteAll();
		  
		  //2.
		  add(user01);
		  assertEquals(1, this.dao.getCount(user01));
		  
		  //3.
		  UserNewsVO outVO01 = doSelectOne(user01);
		  isSameUser(outVO01, user01);
		  
		  String modifyStr = "_U";
		
		  user01.setUnContents(user01.getUnContents()+modifyStr);
		  
		//호출url, param, 호출방식(get/post)
		  MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/doUpdate.do")
				  .param("uId",user01.getuId());		  
		//대역 객체 통해 호출
		  ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
		  String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();		  
		 
		  LOG.debug("====================");
		  LOG.debug("result : "+ result);
		  LOG.debug("====================");	
		  
		  //jsonString to VO
		 // Gson gson = new Gson();
		 // MessageVO messageVO = gson.fromJson(result, MessageVO.class);
		 // LOG.debug("====================");
		 // LOG.debug("messageVO : "+ messageVO);
		 // LOG.debug("====================");
		  
		//  assertEquals("1", messageVO.getMsgId());
		  
		//  UserNewsVO vsVO01 = doSelectOne(user01);
		//  isSameUser(vsVO01, user01);
	  }
	  
	  @Test
	  public void addAndGet() throws Exception{
		//1. 기존데이터 삭제
		//2. 신규데이터 등록
	    //3. 단건데이터 조회
		//4. 등록데이터와 비교
		  
		  //1.
//		  doDelete(user01);
//		  doDelete(user02);
//		  doDelete(user03);
		  dao.deleteAll();
		  
		  //2.
		  add(user01);
		  assertEquals(1, this.dao.getCount(user01));
		  
		  add(user02);
		  assertEquals(1, this.dao.getCount(user02));
		  
		  add(user03);
		  assertEquals(1, this.dao.getCount(user03));
		  
		  //3.
		  UserNewsVO outVO01 = doSelectOne(user01);
		  isSameUser(outVO01, user01);
		  
		  UserNewsVO outVO02 = doSelectOne(user02);
		  isSameUser(outVO02, user02);
		  
		  UserNewsVO outVO03 = doSelectOne(user03);
		  isSameUser(outVO03, user03);
	  }
	  
	  private void isSameUser(UserNewsVO vsVO, UserNewsVO orgVO) {
		  assertEquals(vsVO.getUnContents(), orgVO.getUnContents());
		  assertEquals(vsVO.getuId(), orgVO.getuId());
	  }
	  
	  //@Test
	  //public void add() throws Exception{
	  public void add(UserNewsVO user01) throws Exception{
		  //호출url, param, 호출방식(get/post)
		  //GET방식으로 : http://localhost:8081/ehr/user/add.do?uId=p12
		  MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/add.do")	  
				  .param("unContents",user01.getUnContents())		   
				  .param("uId",user01.getuId());	  
		  //대역 객체 통해 호출
		  ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
		  String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		  
		  LOG.debug("====================");
		  LOG.debug("result : "+ result);
		  LOG.debug("====================");	
		  
		  //jsonString to VO
		  Gson gson = new Gson();
		  MessageVO messageVO = gson.fromJson(result, MessageVO.class);
		  LOG.debug("====================");
		  LOG.debug("messageVO : "+ messageVO);
		  LOG.debug("====================");	  
		  
	  }
	  
	  //@Test
	  //@Ignore
	  //public void doDelete() throws Exception{
	  public void doDelete(UserNewsVO user) throws Exception{
		  //호출url, param, 호출방식(get/post)
		  //GET방식으로 : http://localhost:8081/ehr/user/doDelete.do?uId=p12
		  MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doDelete.do")
				  .param("uId",user.getuId());
		  
		  //대역 객체 통해 호출
		  ResultActions resultActions = mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
		  
		  String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		  
		  LOG.debug("====================");
		  LOG.debug("result : "+ result);
		  LOG.debug("====================");
		  
//		  jsonString to VO
		  Gson gson = new Gson();
		  MessageVO messageVO = gson.fromJson(result, MessageVO.class);
		  
		  LOG.debug("====================");
		  LOG.debug("messageVO : "+ messageVO);
		  LOG.debug("====================");
	  }
	  
	  //@Test
	  //@Ignore
	  //public void doSelectOne() throws Exception {
	  public UserNewsVO doSelectOne(UserNewsVO user) throws Exception {
		  //호출url, param, 호출방식(get/post)
		  //GET방식으로 : http://localhost:8081/ehr/user/doSelectOne.do?uId=p12
		  MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/doSelectOne.do")
				  .param("uId", user.getuId());
		  
		  ResultActions resultActions =  mockMvc.perform(requestBuilder)
				  .andExpect(status().isOk());
		  
		  String result = resultActions.andDo(print()).andReturn().getResponse().getContentAsString();
		  LOG.debug("====================");
		  LOG.debug("result : "+ result);
		  LOG.debug("====================");
		  
		  Gson gson = new Gson();
		  //gson string to UserVO
		  UserNewsVO outVO = gson.fromJson(result, UserNewsVO.class);
		  
		  return outVO;
		  
	  }	
	
}
