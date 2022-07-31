package com.teamdmc.kemie.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamdmc.kemie.cmn.SearchVO;
import com.teamdmc.kemie.board.domain.BoardVO;
import com.teamdmc.kemie.cmn.DTO;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao {

	final Logger LOG = LogManager.getLogger(this.getClass());
	final String NAMESPACE = "com.teamdmc.kemie.board";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public BoardDaoImpl() {
	
	}

	@Override
	public List<BoardVO> doRetrieve(DTO dto) throws SQLException {
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		String statement = NAMESPACE + ".doRetrieve";
		SearchVO inVO = (SearchVO)dto;
		
		LOG.debug("==============================");
		LOG.debug("param: " + inVO.toString());
		LOG.debug("statement : " + statement);
		LOG.debug("==============================");
		
		list = sqlSessionTemplate.selectList(statement,inVO);
		
		for(BoardVO vo : list) {
			LOG.debug("LLLL"+vo);
		}
		return list;
	}

	@Override
	public int doDelete(BoardVO inVO) throws SQLException {
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
	public int doUpdate(BoardVO inVO) throws SQLException {
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
	public int doInsert(BoardVO inVO) throws SQLException {
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
	public void deleteAll() throws SQLException {
		String statement = NAMESPACE+".deleteAll";
		
		LOG.debug("==============================");
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");
		
		sqlSessionTemplate.delete(statement);
	}

	@Override
	public int getCount(BoardVO inVO) throws SQLException {
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
	public List<BoardVO> getAll(BoardVO inVO) {
		List<BoardVO> list= null;
	    String statement = NAMESPACE+".getAll";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		list = sqlSessionTemplate.selectList(statement, inVO);
		
		for(BoardVO vo  :list) {
			LOG.debug("vo:"+vo.toString());
		}
		
		return list;
	}

	@Override
	public BoardVO doSelectOne(BoardVO inVO) throws SQLException {
		BoardVO outVO = null;
		
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

	@Override
	public int updateReadCnt(BoardVO inVO) throws SQLException {
		int flag = 0;
		
		String statement = NAMESPACE+".updateReadCnt";
		
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement: " + statement);
		LOG.debug("==============================");
		
		flag = this.sqlSessionTemplate.update(statement, inVO);

		LOG.debug("flag:" + flag);
		return flag;
	}

}
