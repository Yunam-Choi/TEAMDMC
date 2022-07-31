package com.teamdmc.kemie.board.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.teamdmc.kemie.board.domain.BoardVO;
import com.teamdmc.kemie.board.service.BoardService;
import com.teamdmc.kemie.boardComment.domain.BoardCommentVO;
import com.teamdmc.kemie.boardComment.service.BoardCommentService;
import com.teamdmc.kemie.cmn.MessageVO;
import com.teamdmc.kemie.cmn.SearchVO;
import com.teamdmc.kemie.cmn.StringUtil;

@Controller("boardController")
@RequestMapping("board")
public class BoardController {

	final Logger LOG = LogManager.getLogger(getClass());

	@Autowired
	BoardService boardService;

	@Autowired
	BoardCommentService boardCommentService;

	public BoardController() {}
	
	
	@RequestMapping(value = "/commentDelete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String commentDelete(BoardCommentVO inVO) throws SQLException {

		LOG.debug("========================");
		LOG.debug("=inVO=" + inVO);

		int flag = boardCommentService.commentDelete(inVO);
		String resultMsg = "";
		if (1 == flag) {
			resultMsg = "삭제되었습니다.";
		} else {
			resultMsg = "삭제 실패";
		}

		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		String jsonString = new Gson().toJson(message);
		LOG.debug("=jsonString=" + jsonString);
		LOG.debug("========================");
		return jsonString;
	}

	@RequestMapping(value = "/commentInsert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String commentInsert(BoardCommentVO inVO) throws Exception {

		LOG.debug("comment write");

		int flag = boardCommentService.commentInsert(inVO);
		String resultMsg = "";
		if (1 == flag) {
			resultMsg = inVO.getBcContents() + "가 등록되었습니다.";
		} else {
			resultMsg = "등록 실패";
		}

		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		String jsonString = new Gson().toJson(message);

		LOG.debug("--------------------");
		LOG.debug("-jsonString- : " + jsonString);
		LOG.debug("--------------------");

		return jsonString;

	}

	@RequestMapping(value = "/doMod.do")
	public String doMod(BoardVO inVO, Model model) throws SQLException {

		LOG.debug("--------------------");
		LOG.debug("-inVO- : " + inVO);
		LOG.debug("--------------------");

		BoardVO outVO = boardService.doSelectOne(inVO);
		LOG.debug("--------------------");
		LOG.debug("-outVO- : " + outVO);
		LOG.debug("--------------------");

		model.addAttribute("vo", outVO);
		return "mod";
	}

	@RequestMapping(value = "/doUpdate.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doUpdate(BoardVO inVO) throws SQLException {
		LOG.debug("--------------------");
		LOG.debug("-inVO- : " + inVO);
		LOG.debug("--------------------");

		int flag = boardService.doUpdate(inVO);
		String resultMsg = "";
		if (1 == flag) {
			resultMsg = "수정되었습니다.";
		} else {
			resultMsg = "수정 실패";
		}

		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		String jsonString = new Gson().toJson(message);

		LOG.debug("--------------------");
		LOG.debug("-jsonString- : " + jsonString);
		LOG.debug("--------------------");

		return jsonString;

	}

	@RequestMapping(value = "/doDelete.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doDelete(BoardVO inVO) throws SQLException {
		LOG.debug("========================");
		LOG.debug("=inVO=" + inVO);

		int flag = boardService.doDelete(inVO);
		String resultMsg = "";
		if (1 == flag) {
			resultMsg = "삭제 되었습니다.";
		} else {
			resultMsg = "삭제 실패.";
		}

		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		String jsonString = new Gson().toJson(message);
		LOG.debug("=jsonString=" + jsonString);
		LOG.debug("========================");
		return jsonString;
	}

	@RequestMapping(value = "/doSelectOne.do", method = RequestMethod.GET)
	public String doSelectOne(BoardVO inVO, Model model) throws Exception {

		LOG.debug("--------------------");
		LOG.debug("-inVO- : " + inVO);
		LOG.debug("--------------------");

		BoardVO outVO = boardService.doSelectOne(inVO);
		LOG.debug("--------------------");
		LOG.debug("-outVO- : " + outVO);
		LOG.debug("--------------------");

		model.addAttribute("vo", outVO);

		List<BoardCommentVO> comList = boardCommentService.getAll(outVO.getbSeq());
		model.addAttribute("comList", comList);
		LOG.debug("comList");

		return "details";

	}

	@RequestMapping(value = "/doInsert.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doInsert(BoardVO inVO) throws IOException, Exception {

		LOG.debug("--------------------");
		LOG.debug("-kkkinVO- : " + inVO);
		LOG.debug("--------------------");
		
		int flag = boardService.doInsert(inVO);
		String resultMsg = "";

		if (1 == flag) {
			resultMsg = inVO.getbTitle() + "가 등록되었습니다.";
		} else {
			resultMsg = "등록 실패";
		}

		MessageVO message = new MessageVO(String.valueOf(flag), resultMsg);
		String jsonString = new Gson().toJson(message);

		LOG.debug("--------------------");
		LOG.debug("-jsonString- : " + jsonString);
		LOG.debug("--------------------");

		return jsonString;

	}
	
	@RequestMapping(value = "/moveToReg.do", method = RequestMethod.GET)
	public String moveToReg(SearchVO inVO, Model model) throws SQLException {
		String viewName = "write";
		LOG.debug("--------------------");
		LOG.debug("---moveToReg---");
		LOG.debug("-inVO- : " + inVO);
		LOG.debug("--------------------");

		model.addAttribute("vo", inVO);
		return viewName;
	}

	@RequestMapping(value = "/doRetrieve.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doRetrieve(SearchVO inVO) throws SQLException {

		String jsonString = "";

		LOG.debug("--------------------");
		LOG.debug("---doRetrieve---");
		LOG.debug("-inVO- : " + inVO);
		LOG.debug("--------------------");

		// 페이지 사이즈 null 처리
		if (0 == inVO.getPageSize()) {
			inVO.setPageSize(10);
		}

		// 페이지 넘버 null 처리
		if (0 == inVO.getPageNum()) {
			inVO.setPageNum(1);
		}

		// 검색 구분 null 처리
		if (null == inVO.getSearchDiv()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv(), ""));
		}

		// 검색어 null 처리
		if (null == inVO.getSearchWord()) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord(), ""));
		}

		LOG.debug("---------------------------");
		LOG.debug("-inVO- : " + inVO);
		LOG.debug("---------------------------");

		List<BoardVO> list = boardService.doRetrieve(inVO);
		Gson gson = new Gson();

		jsonString = gson.toJson(list);
		LOG.debug("---------------------------");
		LOG.debug("-jsonString- : " + jsonString);
		LOG.debug("---------------------------");
		return jsonString;

	}

	@RequestMapping(value = "/boardView.do", method = RequestMethod.GET)
	public String boardView(Model model, SearchVO inVO) throws SQLException {
		LOG.debug("inVO : " + inVO);
		LOG.debug("--------------------");
		LOG.debug("---boardView---");
		LOG.debug("--------------------");
		
		LOG.debug("searchVO" + inVO);

		// 페이지 사이즈 null 처리
		if (0 == inVO.getPageSize()) {
			inVO.setPageSize(20);
		}

		// 페이지 넘버 null 처리
		if (0 == inVO.getPageNum()) {
			inVO.setPageNum(1);
		}

		// 검색 구분 null 처리
		if (null == inVO.getSearchDiv()) {
			inVO.setSearchDiv(StringUtil.nvl(inVO.getSearchDiv(), ""));
		}

		// 검색어 null 처리
		if (null == inVO.getSearchWord()) {
			inVO.setSearchWord(StringUtil.nvl(inVO.getSearchWord(), ""));
		}

		LOG.debug("---------------------------");
		LOG.debug("-inVO- : " + inVO);
		LOG.debug("---------------------------");

		List<BoardVO> list = boardService.doRetrieve(inVO);

		int totalCnt = 0; // 총글수
		double pageTotal = 0; // 총 페이지 (총글수/pageSize ?+1: 총글수/pageSize;)

		if (null != list && list.size() > 0) {
			totalCnt = list.get(0).getTotalCnt();
			pageTotal = Math.ceil(totalCnt / (inVO.getPageSize() * 1.0));

			LOG.debug("---------------------------");
			LOG.debug("-pageTotal- : " + pageTotal);
			LOG.debug("---------------------------");

		}

		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageTotal", pageTotal);
		model.addAttribute("list", list);
		model.addAttribute("vo", inVO);

		return "board";
	}

}
