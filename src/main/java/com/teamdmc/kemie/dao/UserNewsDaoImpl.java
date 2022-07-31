/**
* <pre>
* com.teamdmc.kemie.dao
* Class Name : UserNewsDaoImpl.java
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
package com.teamdmc.kemie.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamdmc.kemie.cmn.DTO;
import com.teamdmc.kemie.usernews.domain.UserNewsVO;

/**
 * @author Choi Jong Hee
 *
 */
@Repository("userNewsDao")
public class UserNewsDaoImpl implements UserNewsDao {
	final Logger LOG = LogManager.getLogger(this.getClass());
	final String NAMESPACE = "com.teamdmc.kemie.userNews";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public UserNewsDaoImpl() {}

	@Override
	public int doDelete(UserNewsVO inVO) throws SQLException {
		int flag = 0;
		
		String statement = this.NAMESPACE+".doDelete";		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");	
				
		flag = this.sqlSessionTemplate.delete(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}

	@Override
	public int doUpdate(UserNewsVO inVO) throws SQLException {
		int flag = 0;
        String statement =NAMESPACE+".doUpdate";	
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
	
		flag = sqlSessionTemplate.update(statement, inVO);
		
		LOG.debug("flag:" + flag);
		
		return flag;
	}

	@Override
	public int doInsert(UserNewsVO inVO) throws SQLException {
		int flag = 0;

		String statement = NAMESPACE+".doInsert";
		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		flag = this.sqlSessionTemplate.insert(statement, inVO);
		LOG.debug("flag:" + flag);
		
		return flag;
	}

	@Override
	public int deleteAll() throws SQLException {
		String statement = NAMESPACE+".deleteAll";
		
		LOG.debug("==============================");
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		sqlSessionTemplate.delete(statement);
		
		return 0;
	}

	@Override
	public int getCount(UserNewsVO inVO) throws SQLException {
		int count = 0;

		String statement = this.NAMESPACE+".getCount";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		count =this.sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("==============================");
		LOG.debug("count=" + count);
		LOG.debug("==============================");			
	
		return count;
	}

	@Override
	public List<UserNewsVO> getAll(UserNewsVO inVO) {
		List<UserNewsVO> list= null;
	    String statement = NAMESPACE+".getAll";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		list = sqlSessionTemplate.selectList(statement, inVO);
		
		//list = jdbcTemplate.query(sb.toString(),rowMapper,args);
		
		for(UserNewsVO vo  :list) {
			LOG.debug("vo:"+vo.toString());
		}
		
		return list;
	}

	@Override
	public UserNewsVO doSelectOne(UserNewsVO inVO) throws SQLException {
		UserNewsVO outVO = null;
		
		String statement = this.NAMESPACE +".doSelectOne";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);		
		LOG.debug("==============================");
		LOG.debug("**outVO=" + outVO.toString());
		LOG.debug("==============================");

		return outVO;
	}

}
