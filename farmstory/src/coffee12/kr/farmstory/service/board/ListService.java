package coffee12.kr.farmstory.service.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffee12.kr.farmstory.config.DBConfig;
import coffee12.kr.farmstory.config.SQL;
import coffee12.kr.farmstory.controller.CommonAction;
import coffee12.kr.farmstory.vo.BoardVO;

public class ListService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		String pg = req.getParameter("pg");
		String gr = req.getParameter("gr");
		String cate = req.getParameter("cate");

		// Limit용 start 계산
		int start = getLimitStart(pg);

		// 페이지번호 계산
		int total = getTotal(cate);
		int pageEnd = getPageEnd(total);

		// 글 카운터번호 계산
		int count = getPageCountStart(total, start);

		// 페이지 그룹 계산
		int[] GroupStartEnd = getPageGroupStartEnd(pg, pageEnd);
		int groupStart = GroupStartEnd[0];  
		int groupEnd = GroupStartEnd[1];



		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_LIST);
		pstmt.setString(1, cate);
		pstmt.setInt(2, start);
		ResultSet rs = pstmt.executeQuery();


		ArrayList<BoardVO> list = new ArrayList<>();

		while(rs.next()){
			BoardVO board = new BoardVO();
			board.setSeq(rs.getInt(1));
			board.setParent(rs.getInt(2));
			board.setComment(rs.getInt(3));
			board.setCate(rs.getString(4));
			board.setTitle(rs.getString(5));
			board.setContent(rs.getString(6));
			board.setFile(rs.getInt(7));
			board.setHit(rs.getInt(8));
			board.setUid(rs.getString(9));
			board.setRegip(rs.getString(10));
			board.setRdate(rs.getString(11));
			board.setNick(rs.getString(12));

			list.add(board);
		}
		rs.close();
		pstmt.close();
		conn.close();

		req.setAttribute("list", list); // m-v-c request 영역. request객체를 공유하기때문에 이 객체에 list를 넣어서 보냄
		req.setAttribute("count", count);
		req.setAttribute("groupStart", groupStart);
		req.setAttribute("groupEnd", groupEnd);
		req.setAttribute("page", pageEnd);
		req.setAttribute("gr", gr);
		req.setAttribute("cate", cate);

		return "/board/list.jsp";
	}// requestProc 끝


	public int getLimitStart(String pg) {
		int start = 0;

		if(pg == null){
			start = 1;
		}else{
			start = Integer.parseInt(pg);
		}

		return (start - 1) * 10;
	}

	public int getTotal(String cate) throws Exception{

		int total = 0;
		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_COUNT);
		pstmt.setString(1, cate);

		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			total = rs.getInt(1);
		}

		rs.close();
		pstmt.close();
		conn.close();

		return total;
	}

	public int getPageEnd(int total) {
		int pageEnd = 0;

		if(total % 10 == 0){
			pageEnd = total / 10;
		}else{
			pageEnd = (total / 10) + 1;
		}
		return pageEnd;
	}

	public int[] getPageGroupStartEnd(String pg, int pageEnd) {
		int[] groupStartEnd = new int[2];
		int current = 0;

		if(pg==null) {
			current = 1;
		}else {
			current = Integer.parseInt(pg);
		}

		int currentPage = current;
		int currentPageGroup =(int) Math.ceil(currentPage/10.0);  // 실수로 나눠서 올림함
		int groupStart = (currentPageGroup-1)*10+1;
		int groupEnd = (currentPageGroup)*10;

		if(groupEnd > pageEnd){
			groupEnd = pageEnd;
		}
		groupStartEnd[0] = groupStart;
		groupStartEnd[1] = groupEnd;

		return groupStartEnd;
	}

	public int getPageCountStart(int total, int limit) {
		return total - limit;
	}
}