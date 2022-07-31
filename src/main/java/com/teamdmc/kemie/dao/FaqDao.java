package com.teamdmc.kemie.dao;

import com.teamdmc.kemie.cmn.DTO;
import com.teamdmc.kemie.faq.domain.FaqVO;

import java.sql.SQLException;
import java.util.List;

public interface FaqDao {
	
	/**
	 * 조회수 증가
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	int updateReadCnt(FaqVO inVO) throws SQLException;

	/**
	 * 목록 조회
	 * @param dto
	 * @return List<boardVO>
	 * @throws SQLException
	 */
	public List<FaqVO> doRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 게시물 삭제
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public int doDelete(FaqVO inVO) throws SQLException;
	
	/**
	 * 게시물 수정
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public int doUpdate(FaqVO inVO) throws SQLException;
	
	/**
	 * 게시물 등록
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int doInsert(FaqVO inVO) throws SQLException;

	/**
	 * 모든 게시물 삭제
	 * @throws SQLException
	 */
	public void deleteAll() throws SQLException;

	/**
	 * 조회 건수 출력
	 * @param inVO
	 * @return int
	 * @throws SQLException
	 */
	public int getCount(FaqVO inVO) throws SQLException;
	
	/**
	 * 게시물 다건 조회
	 * @return
	 */
	public List<FaqVO> getAll(FaqVO inVO);
	
	/**
	 * 게시물 단건 조회
	 * @param inVO
	 * @return UserVO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FaqVO doSelectOne(FaqVO inVO) throws SQLException;
	
}
