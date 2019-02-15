package coffee12.kr.farmstory.service.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import coffee12.kr.farmstory.config.DBConfig;
import coffee12.kr.farmstory.config.SQL;
import coffee12.kr.farmstory.controller.CommonAction;

public class UserCheckService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		String uid = req.getParameter("uid");
		
		java.sql.Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_UID_COUNT);
		pstmt.setString(1, uid);
		ResultSet rs = pstmt.executeQuery();
		
		int count = 0;
		if(rs.next()){
			count = rs.getInt(1);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		// JSON format으로 변환
		JSONObject json = new JSONObject();
		json.put("result", count);
		
		String result = json.toString();
		
		System.out.println("result : " + result);
		
		return result; // 컨트롤러 result값으로 넘어감!
	}

}
