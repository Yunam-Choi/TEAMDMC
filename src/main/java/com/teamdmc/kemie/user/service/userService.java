package com.teamdmc.kemie.user.service;

import java.sql.SQLException;
import java.util.List;

import com.teamdmc.kemie.cmn.DTO;
import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.user.domain.UserVO;

public interface userService {
	
	/**
	 * 아이디 찾기
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public MessageVO doFindID(UserVO inVO) throws SQLException;
	
	/**
	 * 전화번호check
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public int pNumCheck(UserVO inVO) throws SQLException;
	
	/**
	 * 이름 check
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public int nameCheck(UserVO inVO) throws SQLException;
	
	/**
	 * 비밀번호 찾기
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public MessageVO doUpdatePW(UserVO inVO) throws SQLException;
	
	/**
	 * 이전 비밀번호와 같은지 비밀번호 체크
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public int passCheck(UserVO inVO) throws SQLException;
	
	/**
	 * 아이디 비번확인
	 * @param inVO
	 * @return MessageVO
	 * @throws SQLException
	 */	
	public MessageVO idPassCheck(UserVO inVO) throws SQLException;
	
	/**
	 * 비밀번호 변경
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public int doPassUpdate(UserVO inVO) throws SQLException;
	
	/**
	 * 닉네임 변경
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public int doNickUpdate(UserVO inVO) throws SQLException;
	
	/**
	 * 비밀번호 check
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	public int loginCheck(UserVO inVO) throws SQLException;
	
	/**
	 * nickname 중복 check
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	public int nickCheck(UserVO inVO) throws SQLException;
	
	/**
	 * id중복 check
	 * @param inVO
	 * @return
	 * @throws SQLException
	 */
	public int idCheck(UserVO inVO) throws SQLException;

	/**
	 * 목록 조회
	 * @param dto
	 * @return List<UserVO>
	 * @throws SQLException
	 */
	public List<UserVO> doRetrieve(DTO dto) throws SQLException;
	
	/**
	 * 사용자 삭제
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public MessageVO doDelete(UserVO inVO) throws SQLException;
	
	/**
	 * 사용자 수정 가능
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws SQLException
	 */
	public int doUpdate(UserVO inVO) throws SQLException;
	
	/**
	 * 사용자 등록
	 * @param inVO
	 * @return 1(성공)/ 0(실패)
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int doInsert(UserVO inVO) throws SQLException;

	/**
	 * 모든 데이터 삭제
	 * @throws SQLException
	 */
	public void deleteAll() throws SQLException;

	/**
	 * 조회 건수 출력
	 * @param inVO
	 * @return int
	 * @throws SQLException
	 */
	public int getCount(UserVO inVO) throws SQLException;
	
	/**
	 * 회원 다건 조회
	 * @return
	 */
	public List<UserVO> getAll(UserVO inVO);
	
	/**
	 * 회원 단건 조회
	 * @param inVO
	 * @return UserVO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public UserVO doSelectOne(UserVO inVO) throws SQLException;
}
