/**
* <pre>
* com.teamdmc.kemie.userinterested
* Class Name : UserinterestedServiceImpl.java
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.dao.UserInterestedCoinDao;
import com.teamdmc.kemie.userinterested.domain.UserInterestedVO;

/**
 * @author Choi Jong Hee
 *
 */
@Service("UserinterestedService")
public class UserinterestedServiceImpl implements UserinterestedService {

	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	private UserInterestedCoinDao userInterestedCoinDao;

	@Override
	public MessageVO addOrDelete(UserInterestedVO inVO) throws SQLException {
		LOG.debug("============================");
		LOG.debug("=addOrDelete()=");
		LOG.debug("=inVO=" + inVO);
		LOG.debug("============================");
		// msgId 상태 값
		// 1. 삭제 완료: 10
		// 2. 삭제 실패: 20
		// 3. 등록 성공: 30
		// 4. 등록 실패: 40
		int flag = userInterestedCoinDao.uICCheck(inVO);
		LOG.debug("==================flag: " + flag);
		if (flag == 1) {
			flag = userInterestedCoinDao.doDelete(inVO);

			if (flag == 1)
				return new MessageVO("10", "관심 코인 " + inVO.getUicMarket() + "이(가) 삭제 되었습니다.");
			else
				return new MessageVO("20", "관심 코인 " + inVO.getUicMarket() + "이(가) 삭제 실패 하였습니다.");
		} else {
			flag = userInterestedCoinDao.doInsert(inVO);

			if (flag == 1)
				return new MessageVO("30", "관심 코인 " + inVO.getUicMarket() + "이(가) 등록 되었습니다.");
			else
				return new MessageVO("40", "관심 코인 " + inVO.getUicMarket() + "이(가) 등록 실패 하였습니다.");
		}
//		return userInterestedCoinDao.doInsert(inVO);
	}

	@Override
	public List<UserInterestedVO> getAll(UserInterestedVO inVO) {
		LOG.debug("============================");
		LOG.debug("=getAll()=");
		LOG.debug("============================");

		return userInterestedCoinDao.getAll(inVO);
	}
}
