package kr.co.board1.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import kr.co.board1.config.DBConfig;
import kr.co.board1.config.SQL;
import kr.co.board1.vo.BoardVO;


public class BoardService {
	
	private static BoardService service = new BoardService();
	
	public static BoardService getInstance() {
		return service;
	}
	
	
	private BoardService() {}
	
	
	public void insertBoard() throws Exception {}
	
	
	public void list() throws Exception {}
	
	
	public void updateHit(String seq) throws Exception {
		
				
		Connection conn = DBConfig.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(SQL.UPDATE_HIT);
		pstmt.setString(1,seq);
		
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	
	public BoardVO view(HttpServletRequest request) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");
		
		// Á¶È¸¼ö +1
		updateHit(seq);

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
	
	
	public void update() throws Exception {}
	public void delete(HttpServletRequest request) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String seq = request.getParameter("seq");

		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.DELETE_VIEW);
		pstmt.setString(1, seq);
		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}
	
	
	public void insertComment() throws Exception {}
	public void listComment() throws Exception {}
}
