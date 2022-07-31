package com.teamdmc.kemie.user.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamdmc.kemie.cmn.DTO;
import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.dao.UserDao;
import com.teamdmc.kemie.user.domain.UserVO;

@Service("userService")
public class userServiceImpl implements userService {

	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;
	
	public userServiceImpl() {}


	@Override
	public MessageVO doDelete(UserVO inVO) throws SQLException {
		// msgId 상태 값
		// 1. 비번 확인 필요: 10
		// 2. 삭제: 20
		MessageVO message = new MessageVO();
		
		LOG.debug("doDelete");
		
		int flag = userDao.passCheck(inVO);
		
		if(flag != 1) {
			LOG.debug("비밀번호가 일치하지 않습니다.");
			message.setMsgId("10");
			message.setMsgContents("비밀번호가 일치하지 않습니다. 다시 확인해 주세요.");
			
			return message;
		}
		userDao.doDelete(inVO);
		message.setMsgId("20");
		message.setMsgContents("아이디가 정상적으로 삭제되었습니다.");
		
		return message;
	}
	
	@Override
	public MessageVO doFindID(UserVO inVO) throws SQLException {
		//msgId;//메시지 ID
		//1. ID확인 : 10
		//2. 비번확인: 20
		//3. id/비번 통과: 30
		MessageVO  message=new MessageVO();
 
		LOG.debug("doFindID serveiceimpl");
		
		int flag = userDao.nameCheck(inVO);
		if(1 != flag) {
			LOG.debug("일치 이름 x");
			message.setMsgId("10");
			message.setMsgContents("일치하는 이름이 없습니다.\n사용자 입력 값: "+inVO.getName());
			
			return message;
		}		
		
		flag = userDao.pNumCheck(inVO);
		if(1 != flag) {
			LOG.debug("일치 전화번호 x");
			message.setMsgId("20");
			message.setMsgContents("일치하는 전화번호가  없습니다.\n사용자 입력 값: "+inVO.getpNum());
			
			return message;
		}	
		
		inVO = userDao.doFindID(inVO);
		LOG.debug("===============================");
		LOG.debug("userServiceImpl()");
		LOG.debug("=inVO="+inVO);
		LOG.debug("===============================");
		message.setMsgId("30");
		message.setMsgContents("사용자의 아이디는 ["+inVO.getuId()+"] 입니다.");
		
		LOG.debug(message.getMsgId()+message.getMsgContents());
		
		return message;
	}

	@Override
	public MessageVO doUpdatePW(UserVO inVO) throws SQLException {
		//msgId;//메시지 ID
		//1. ID확인 : 10
		//2. 비번확인: 20
		//3. id/비번 통과: 30
		MessageVO  message=new MessageVO();
		int flag = userDao.idCheck(inVO);
		if(1 != flag) {
			message.setMsgId("10");
			message.setMsgContents("아이디를  확인 하세요.\n사용자 입력 값: "+inVO.getuId());
			return message;
		}
		
		flag = userDao.nameCheck(inVO);
		if(1 != flag) {
			message.setMsgId("20");
			message.setMsgContents("이름을  확인 하세요.\n사용자 입력 값: "+inVO.getName());
			
			return message;
		}		
		
		flag = userDao.pNumCheck(inVO);
		if(1 != flag) {
			message.setMsgId("30");
			message.setMsgContents("전화번호를  확인 하세요.\n사용자 입력 값: "+inVO.getpNum());
			
			return message;
		}	
		
		flag = userDao.passCheck(inVO);
		if(1 == flag) {
			message.setMsgId("40");
			message.setMsgContents("이전과 동일한 비밀번호입니다. 다른 비밀번호를 입력해주세요.");
			
			return message;
		}
		
		userDao.doUpdatePW(inVO);
		message.setMsgId("50");
		message.setMsgContents("비밀번호가 성공적으로 변경되었습니다.");
		
		return message;
	}

	@Override
	public int pNumCheck(UserVO inVO) throws SQLException {
		return this.userDao.pNumCheck(inVO);
	}

	@Override
	public int nameCheck(UserVO inVO) throws SQLException {
		return this.userDao.nameCheck(inVO);
	}

	@Override
	public int passCheck(UserVO inVO) throws SQLException {
		return this.userDao.passCheck(inVO);
	}

	@Override
	public int doPassUpdate(UserVO inVO) throws SQLException {
		return this.userDao.doPassUpdate(inVO);
	}

	@Override
	public int doNickUpdate(UserVO inVO) throws SQLException {
		return this.userDao.doNickUpdate(inVO);
	}

	@Override
	public int loginCheck(UserVO inVO) throws SQLException {
		return this.userDao.loginCheck(inVO);
	}

	@Override
	public int nickCheck(UserVO inVO) throws SQLException {
		return this.userDao.nickCheck(inVO);
	}

	@Override
	public int idCheck(UserVO inVO) throws SQLException {
		return userDao.idCheck(inVO);
	}

	@Override
	public List<UserVO> doRetrieve(DTO dto) throws SQLException {
		return userDao.doRetrieve(dto);
	}

	@Override
	public int doUpdate(UserVO inVO) throws SQLException {
		return userDao.doUpdate(inVO);
	}

	@Override
	public int doInsert(UserVO inVO) throws SQLException {
		return userDao.doInsert(inVO);
	}

	@Override
	public void deleteAll() throws SQLException {
		
	}

	@Override
	public int getCount(UserVO inVO) throws SQLException {
		return 0;
	}

	@Override
	public List<UserVO> getAll(UserVO inVO) {
		return null;
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException {
		return userDao.doSelectOne(inVO);
	}

	@Override
	public MessageVO idPassCheck(UserVO inVO) throws SQLException {
		 //msgId;//메시지 ID
		//1. ID확인 : 10
		//2. 비번확인: 20
		//3. id/비번 통과: 30
		MessageVO  message=new MessageVO();
		int flag = userDao.idCheck(inVO);
		if(1 != flag) {
			message.setMsgId("10");
			message.setMsgContents("아이디를  확인 하세요.\n"+inVO.getuId());
			return message;
		}
		
		flag = userDao.passCheck(inVO);
		if(1 != flag) {
			message.setMsgId("20");
			message.setMsgContents(inVO.getuId()+ "의 비번을 확인 하세요.");
			
			return message;
		}		
		
		message.setMsgId("30");
		message.setMsgContents(inVO.getuId()+ "의 아이디, 비번이 확인 되었습니다.");
		
		return message;
	}
}
