package com.teamdmc.kemie.boardComment.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamdmc.kemie.boardComment.dao.BoardCommentDao;
import com.teamdmc.kemie.boardComment.domain.BoardCommentVO;

@Service("boardCommentService")
public class BoardCommentServiceImpl implements BoardCommentService {

	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	private BoardCommentDao boardCommentDao;
	
	@Override
	public List<BoardCommentVO> getAll(int bSeq) throws Exception {
		return boardCommentDao.getAll(bSeq);
	}

	@Override
	public int commentInsert(BoardCommentVO inVO) throws SQLException {
		return boardCommentDao.commentInsert(inVO);
	}

	@Override
	public int commentDelete(BoardCommentVO inVO) throws SQLException {
		return boardCommentDao.commentDelete(inVO);
	}

}
