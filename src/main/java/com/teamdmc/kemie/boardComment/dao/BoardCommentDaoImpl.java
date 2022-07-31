package com.teamdmc.kemie.boardComment.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamdmc.kemie.boardComment.domain.BoardCommentVO;


@Repository
public class BoardCommentDaoImpl implements BoardCommentDao{

	final Logger LOG = LogManager.getLogger(this.getClass());
	final String NAMESPACE = "com.teamdmc.kemie.boardComment";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public BoardCommentDaoImpl() {}
	
	@Override
	public List<BoardCommentVO> getAll(int bSeq){
		return sqlSessionTemplate.selectList("com.teamdmc.kemie.boardComment.getAll", bSeq);
	}

	@Override
	public int commentInsert(BoardCommentVO inVO) throws SQLException {
		int flag = 0;
		
		String statement = this.NAMESPACE+".commentInsert";
		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		flag = this.sqlSessionTemplate.insert(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}

	@Override
	public int commentDelete(BoardCommentVO inVO) throws SQLException {
		int flag = 0;
		
		String statement = this.NAMESPACE+".commentDelete";		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");	
				
		flag = this.sqlSessionTemplate.delete(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}
	

	}
