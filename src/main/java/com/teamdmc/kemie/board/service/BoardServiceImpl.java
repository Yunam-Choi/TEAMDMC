package com.teamdmc.kemie.board.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamdmc.kemie.board.domain.BoardVO;
import com.teamdmc.kemie.cmn.DTO;
import com.teamdmc.kemie.dao.BoardDao;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	private BoardDao boardDao;
	
	public BoardServiceImpl() {
		
	}

	@Override
	public List<BoardVO> doRetrieve(DTO dto) throws SQLException {
		return boardDao.doRetrieve(dto);
	}

	@Override
	public int doDelete(BoardVO inVO) throws SQLException {
		return boardDao.doDelete(inVO);
	}

	@Override
	public int doUpdate(BoardVO inVO) throws SQLException {
		return boardDao.doUpdate(inVO);
	}

	@Override
	public int doInsert(BoardVO inVO) throws SQLException {
		return boardDao.doInsert(inVO);
	}

	@Override
	public BoardVO doSelectOne(BoardVO inVO) throws SQLException {
		
		BoardVO outVO = boardDao.doSelectOne(inVO);
		
		if(null != outVO) {
			boardDao.updateReadCnt(outVO);
		}
		return outVO;
	}

}
