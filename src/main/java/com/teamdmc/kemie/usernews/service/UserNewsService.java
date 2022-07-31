/**
* <pre>
* com.teamdmc.kemie.usernews.service
* Class Name : UserNewsService.java
* Description:
* Author: Choi Jong Hee
* Since: 2022/06/23
* Version 0.1
* Copyright (c) by H.R.KIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/23 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.teamdmc.kemie.usernews.service;

import java.sql.SQLException;
import java.util.List;

import com.teamdmc.kemie.cmn.DTO;
import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.usernews.domain.UserNewsVO;

/**
 * @author Choi Jong Hee
 *
 */
public interface UserNewsService {
	public int doDelete(UserNewsVO inVO) throws SQLException;
	
	/**
	 * 사용자 수정 가능
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public int doUpdate(UserNewsVO inVO) throws SQLException;
	
	/**
	 * 사용자 등록
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int doInsert(UserNewsVO inVO) throws SQLException;

	/**
	 * 모든 데이터 삭제
	 * @throws SQLException
	 */
	public int deleteAll() throws SQLException;

	/**
	 * 조회 건수 출력
	 * @param inVO
	 * @return int
	 * @throws SQLException
	 */
	public int getCount(UserNewsVO inVO) throws SQLException;
	
	/**
	 * 회원 다건 조회
	 * @return
	 */
	public List<UserNewsVO> getAll(UserNewsVO inVO);
	
	/**
	 * 회원 단건 조회
	 * @param inVO
	 * @return UserNewsVO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserNewsVO doSelectOne(UserNewsVO inVO) throws SQLException;
}