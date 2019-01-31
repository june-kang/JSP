package cf.chhak.service.member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.chhak.controller.CommonAction;
import cf.chhak.config.DBConfig;
import cf.chhak.config.SQL;
import cf.chhak.vo.TermsVO;

public class TermsService implements CommonAction {
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		Connection conn = DBConfig.getConnection();
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(SQL.SELECT_TERMS);
		TermsVO vo = null;

		if(rs.next()){
			vo = new TermsVO();
			vo.setTerms(rs.getString(1));
			vo.setPrivacy(rs.getString(2));
		}
		
		rs.close();
		stmt.close();
		conn.close();
		
		// request객체 통한 데이터 공유 
		req.setAttribute("vo", vo);
		
		
		return "/terms.jsp";
	}
}
