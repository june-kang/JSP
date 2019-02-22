package coffee12.kr.farmstory.service.board;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffee12.kr.farmstory.config.DBConfig;
import coffee12.kr.farmstory.config.SQL;
import coffee12.kr.farmstory.controller.CommonAction;

public class DeleteService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		String seq = req.getParameter("seq");
		String gr = req.getParameter("gr");
		String cate = req.getParameter("cate");

		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.DELETE_VIEW);
		pstmt.setString(1, seq);
		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
		
		return "redirect:/farmstory/board/list.do?gr="+gr+"&cate="+cate;
	}

}
