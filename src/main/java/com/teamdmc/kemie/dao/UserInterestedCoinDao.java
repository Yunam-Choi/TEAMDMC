package com.teamdmc.kemie.dao;

import java.sql.SQLException;
import java.util.List;

import com.teamdmc.kemie.userinterested.domain.UserInterestedVO;

public interface UserInterestedCoinDao {
	
	/**
	 * 관심코인 존재여부 확인
	 * @param inVO
	 * @return int
	 * @throws SQLException
	 */
	public int uICCheck(UserInterestedVO inVO) throws SQLException;
	
	/**
	 * 사용자 삭제
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public int doDelete(UserInterestedVO inVO) throws SQLException;
	
	/**
	 * 사용자 등록
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int doInsert(UserInterestedVO inVO) throws SQLException;
	
	/**
	 * 회원 다건 조회
	 * @return
	 */
	public List<UserInterestedVO> getAll(UserInterestedVO inVO);
	
	/**
	 * 모든 데이터 삭제
	 * @throws SQLException
	 */
	public void deleteAll() throws SQLException;
}
