package kr.co.board1.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.co.board1.config.DBConfig;
import kr.co.board1.config.SQL;
import kr.co.board1.vo.BoardVO;
import kr.co.board1.vo.MemberVO;


public class BoardService {
	
	private static BoardService service = new BoardService();
	
	public static BoardService getInstance() {
		return service;
	}
	
	
	private BoardService() {}
	
	public MemberVO getMember(HttpSession session) {
		MemberVO vo = (MemberVO)session.getAttribute("member");
		return vo;
		}
	
	
	public void insertBoard() throws Exception {}
	
	public int getTotal() throws Exception{
		
		int total = 0;
		Connection conn = DBConfig.getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(SQL.SELECT_COUNT);
		if(rs.next()) {
			total = rs.getInt(1);
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		return total;
	}
	
	public int getLimitStart(String pg) {
		int start = 0;
		
		if(pg == null){
			start = 1;
		}else{
			start = Integer.parseInt(pg);
		}
		
		return (start - 1) * 10;
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
	
	public int getPageCountStart(int total, int limit) {
		return total - limit;
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
		int currentPageGroup =(int) Math.ceil(currentPage/10.0);  // 실수로 나눠서 올림을 함
		int groupStart = (currentPageGroup-1)*10+1;
		int groupEnd = (currentPageGroup)*10;
		
		if(groupEnd > pageEnd){
			groupEnd = pageEnd;
		}
		groupStartEnd[0] = groupStart;
		groupStartEnd[1] = groupEnd;
		
		return groupStartEnd;
	}
	
	public ArrayList<BoardVO> list(int start) throws Exception {

		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_LIST);
		pstmt.setInt(1, start);
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
		
		return list;
		
	}
	
	
	public void updateHit(int seq) throws Exception {
		
				
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(SQL.UPDATE_HIT);
		pstmt.setInt(1,seq);
		
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	
	public BoardVO view(HttpServletRequest request) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");

		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_VIEW);
		pstmt.setString(1,seq);
		
		ResultSet rs = pstmt.executeQuery();
				
		BoardVO vo = new BoardVO();
		
		if(rs.next()){
			vo.setSeq(rs.getInt(1));
			vo.setParent(rs.getInt(2));
			vo.setComment(rs.getInt(3));
			vo.setCate(rs.getString(4));
			vo.setTitle(rs.getString(5));
			vo.setContent(rs.getString(6));
			vo.setFile(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			vo.setUid(rs.getString(9));
			vo.setRegip(rs.getString(10));
			vo.setRdate(rs.getString(11));		
		}
		
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return vo;
	}
	
	
	public String modify(HttpServletRequest request) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("subject");
		String content = request.getParameter("content");
		String seq = request.getParameter("seq");
		
		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.UPDATE_BOARD);
		pstmt.setString(1,title);
		pstmt.setString(2,content);
		pstmt.setString(3,seq);
		
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		
		return seq;
		
	}
	
	
	public String delete(HttpServletRequest request) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");
		String parent = request.getParameter("parent");

		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.DELETE_VIEW);
		pstmt.setString(1, seq);
		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
		
		return parent;
	}
	
	
	public String insertComment(HttpServletRequest request) throws Exception {
			
		request.setCharacterEncoding("UTF-8");
		String parent = request.getParameter("parent");
		String content = request.getParameter("comment");
		String uid = request.getParameter("uid");
		String regip = request.getRemoteAddr();

		Connection conn = DBConfig.getConnection();
		CallableStatement call = conn.prepareCall(SQL.INSERT_COMMENT);
		call.setString(1, parent);
		call.setString(2, content);
		call.setString(3, uid);
		call.setString(4, regip);
		call.execute();

		call.close();
		conn.close();
		
		return parent;
	}

	public ArrayList<BoardVO> listComment(String parent) throws Exception {
		
		
		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_COMMENT);
		pstmt.setString(1, parent);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<BoardVO> list = new ArrayList<>();
		while(rs.next()) {
			BoardVO vo = new BoardVO(); // list 배열안에 vo 객체 여러개가 담김 - new BoardVO()가 안에 있어야 여러개 생성 가능
			vo.setSeq(rs.getInt("seq"));
			vo.setParent(rs.getInt(2));
			vo.setComment(rs.getInt(3));
			vo.setContent(rs.getString(6));
			vo.setFile(rs.getInt(7));
			vo.setHit(rs.getInt(8));
			vo.setUid(rs.getString(9));
			vo.setRegip(rs.getString(10));
			vo.setRdate(rs.getString("rdate"));
			vo.setNick(rs.getString(12));
			
			list.add(vo);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
	}
	
	
	public void updateCommentCount() throws Exception{
		
		
	}
	
}
