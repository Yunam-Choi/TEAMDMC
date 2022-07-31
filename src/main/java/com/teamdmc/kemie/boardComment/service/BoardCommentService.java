package com.teamdmc.kemie.boardComment.service;

import java.sql.SQLException;
import java.util.List;

import com.teamdmc.kemie.boardComment.domain.BoardCommentVO;

public interface BoardCommentService {

	public List<BoardCommentVO> getAll(int bSeq) throws Exception;
	
	public int commentInsert(BoardCommentVO inVO) throws SQLException;
	
	public int commentDelete(BoardCommentVO inVO) throws SQLException;
	
}
