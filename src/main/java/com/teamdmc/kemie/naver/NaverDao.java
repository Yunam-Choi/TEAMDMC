package com.teamdmc.kemie.naver;

import java.sql.SQLException;

public interface NaverDao {
	
	/**
	 * 목록조회 
	 * @param dto
	 * @return List<NaverVO>
	 * @throws SQLException
	 */
	NaverSearchVO doRetrieve() throws SQLException;

}
