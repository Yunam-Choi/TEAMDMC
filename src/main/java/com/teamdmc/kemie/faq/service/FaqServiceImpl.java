package com.teamdmc.kemie.faq.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamdmc.kemie.cmn.DTO;
import com.teamdmc.kemie.dao.FaqDao;
import com.teamdmc.kemie.faq.domain.FaqVO;

@Service("faqService")
public class FaqServiceImpl implements FaqService {

	final Logger LOG = LogManager.getLogger(getClass());
	
	@Autowired
	private FaqDao faqDao;

	@Override
	public List<FaqVO> doRetrieve(DTO dto) throws SQLException {
		return faqDao.doRetrieve(dto);
	}

	@Override
	public int doDelete(FaqVO inVO) throws SQLException {
		
		return faqDao.doDelete(inVO);
	}

	@Override
	public int doUpdate(FaqVO inVO) throws SQLException {
	
		return faqDao.doUpdate(inVO);
	}

	@Override
	public int doInsert(FaqVO inVO) throws SQLException {
		
		return faqDao.doInsert(inVO);
	}

	@Override
	public FaqVO doSelectOne(FaqVO inVO) throws SQLException {
		FaqVO outVO = faqDao.doSelectOne(inVO);
		
		if(null != outVO) {
			faqDao.updateReadCnt(outVO);
		}
		return outVO;
	}

}
