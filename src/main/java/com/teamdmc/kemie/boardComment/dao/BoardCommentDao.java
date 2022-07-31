package com.teamdmc.kemie.boardComment.dao;

import java.sql.SQLException;
import java.util.List;

import com.teamdmc.kemie.boardComment.domain.BoardCommentVO;

public interface BoardCommentDao {

	public int commentDelete(BoardCommentVO inVO) throws SQLException;
	
	public int commentInsert(BoardCommentVO inVO) throws SQLException;
	
	public List<BoardCommentVO> getAll(int bSeq);
	


}