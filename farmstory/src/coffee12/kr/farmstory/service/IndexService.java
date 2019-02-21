package coffee12.kr.farmstory.service;

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

public class IndexService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println("IndexService ½ÇÇà!!!!!!!!!!!!!!!!!");
		
		req.setAttribute("latest1", getLatest("grow") );
		req.setAttribute("latest2", getLatest("school") );
		req.setAttribute("latest3", getLatest("story") );
		
		return "/index.jsp";
	}	
	
	private ArrayList<BoardVO> getLatest(String cate) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_LATEST);
		
		pstmt.setString(1, cate);

		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<BoardVO> list = new ArrayList<>();
		
		while(rs.next()) {
			BoardVO vo = new BoardVO();
			
			vo.setSeq(rs.getInt("seq"));
			vo.setCate(rs.getString("cate"));
			vo.setTitle(rs.getString("title"));
			vo.setRdate(rs.getString("rdate"));
			
			list.add(vo);
		}
		
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return list;
		
		
	}
}