package com.teamdmc.kemie.navernews;

import java.sql.SQLException;
import java.util.List;

import com.teamdmc.kemie.cmn.SearchVO;

public interface NaverService {
	
	/**
	 * 목록조회 
	 * @param dto
	 * @return List<BoardVO>
	 * @throws SQLException
	 */
	List<Item> doRetrieve() throws SQLException;

}
