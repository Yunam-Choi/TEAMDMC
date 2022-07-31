package com.teamdmc.kemie.usernews.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamdmc.kemie.cmn.DTO;
import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.dao.UserNewsDao;
import com.teamdmc.kemie.usernews.domain.UserNewsVO;

@Service("userNewsServiceImpl")
public class UserNewsServiceImpl implements UserNewsService {
	final Logger LOG = LogManager.getLogger(this.getClass());
	
	@Autowired
	private UserNewsDao dao;
	
	@Override
	public int doDelete(UserNewsVO inVO) throws SQLException {
		return dao.doDelete(inVO);
	}

	@Override
	public UserNewsVO doSelectOne(UserNewsVO inVO) throws SQLException {
		return dao.doSelectOne(inVO);
	}

	@Override
	public int doInsert(UserNewsVO inVO) throws SQLException {
		return dao.doInsert(inVO);
	}
	

	@Override
	public int doUpdate(UserNewsVO inVO) throws SQLException {
		return dao.doUpdate(inVO);
	}

	@Override
	public int deleteAll() throws SQLException {
		return dao.deleteAll();
	}
	@Override
	public int getCount(UserNewsVO inVO) throws SQLException {
		return dao.getCount(inVO);
	}

	@Override
	public List<UserNewsVO> getAll(UserNewsVO inVO) {
		// TODO Auto-generated method stub
		return null;
	}
}
