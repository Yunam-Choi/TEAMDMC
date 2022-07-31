/**
* <pre>
* com.teamdmc.kemie.userinterested
* Class Name : UserinterestedService.java
* Description:
* Author: Choi Jong Hee
* Since: 2022/07/05
* Version 0.1
* Copyright (c) by H.R.KIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/07/05 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.teamdmc.kemie.userinterested;

import java.sql.SQLException;
import java.util.List;

import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.userinterested.domain.UserInterestedVO;

/**
 * @author Choi Jong Hee
 *
 */
public interface UserinterestedService {
	

	/**
	 * 관심 코인 조회 후 있으면 delete, 없으면 insert
	 * @param inVO
	 * @return MessageVO
	 * @throws SQLException
	 */
	public MessageVO addOrDelete(UserInterestedVO inVO) throws SQLException;
	
	/**
	 * 관심 코인 다건 조회
	 * @return List<UserInterestedVO>
	 */
	public List<UserInterestedVO> getAll(UserInterestedVO inVO);
}
