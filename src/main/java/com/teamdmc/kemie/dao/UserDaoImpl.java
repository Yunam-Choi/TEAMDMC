package com.teamdmc.kemie.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.teamdmc.kemie.cmn.DTO;
import com.teamdmc.kemie.user.domain.UserVO;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	final Logger LOG = LogManager.getLogger(this.getClass());
	final String NAMESPACE = "com.teamdmc.kemie.user";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public UserDaoImpl() {}

	@Override
	public UserVO doFindID(UserVO inVO) throws SQLException {
		UserVO outVO = null;
		
		String statement = NAMESPACE+".doFindID";
		LOG.debug("==============================");
		LOG.debug("다오 임플 되는지 궁금");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");
		
		outVO = this.sqlSessionTemplate.selectOne(statement, inVO);		
		LOG.debug("==============================");
		LOG.debug("**outVO=" + outVO.toString());
		LOG.debug("==============================");
		
		return outVO;
	}

	@Override
	public int nameCheck(UserVO inVO) throws SQLException {
		String statement = NAMESPACE+".nameCheck";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("flag: "+ flag);
		return flag;
	}
	
	@Override
	public int pNumCheck(UserVO inVO) throws SQLException {
		String statement = NAMESPACE+".pNumCheck";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("flag: "+ flag);
		return flag;
	}
	
	@Override
	public int doUpdatePW(UserVO inVO) throws SQLException {
		String statement = NAMESPACE + ".doUpdatePW";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.update(statement, inVO);
		return flag;
	}

	@Override
	public int passCheck(UserVO inVO) throws SQLException {
		String statement = NAMESPACE+".passCheck";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("flag: "+ flag);
		return flag;
	}

	@Override
	public int doPassUpdate(UserVO inVO) throws SQLException {
		String statement = NAMESPACE+".doPassUpdate";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.update(statement, inVO);
		LOG.debug("flag: "+ flag);
		return flag;
	}
	
	@Override
	public int doNickUpdate(UserVO inVO) throws SQLException {
		String statement = NAMESPACE+".doNickUpdate";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");
		
		int flag = sqlSessionTemplate.update(statement, inVO);
		LOG.debug("flag: "+ flag);
		return flag;
	}

	@Override
	public int loginCheck(UserVO inVO) throws SQLException {
		String statement = NAMESPACE+".loginCheck";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");
		
		int count = sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("count: "+ count);
		
		return count;
	}

	@Override
	public int nickCheck(UserVO inVO) throws SQLException {
		String statement = NAMESPACE+".nickCheck";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");
		
		int count = sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("count: "+ count);
		
		return count;
	}
	
	@Override
	public int idCheck(UserVO inVO) throws SQLException {
		String statement = NAMESPACE+".idCheck";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" +statement);
		LOG.debug("==============================");
		
		int count = sqlSessionTemplate.selectOne(statement, inVO);
		LOG.debug("count: "+ count);
		
		return count;
	}

	@Override
	public List<UserVO> doRetrieve(DTO dto) throws SQLException {
		return null;
	}

	@Override
	public int doDelete(UserVO inVO) throws SQLException {
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
	public int doUpdate(UserVO inVO) throws SQLException {
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
	public int doInsert(UserVO inVO) throws SQLException {
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
	public int getCount(UserVO inVO) throws SQLException {
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
	public List<UserVO> getAll(UserVO inVO) {
		List<UserVO> list= null;
	    String statement = NAMESPACE+".getAll";
		LOG.debug("==============================");
		LOG.debug("param:" + inVO.toString());
		LOG.debug("statement:" + statement);
		LOG.debug("==============================");	
		
		list = sqlSessionTemplate.selectList(statement, inVO);
		
		//list = jdbcTemplate.query(sb.toString(),rowMapper,args);
		
		for(UserVO vo  :list) {
			LOG.debug("vo:"+vo.toString());
		}
		
		return list;
	}

	@Override
	public UserVO doSelectOne(UserVO inVO) throws SQLException {
		UserVO outVO = null;
		
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
