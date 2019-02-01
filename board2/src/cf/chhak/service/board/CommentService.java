package cf.chhak.service.board;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import cf.chhak.config.DBConfig;
import cf.chhak.config.SQL;
import cf.chhak.controller.CommonAction;
import cf.chhak.vo.BoardVO;

public class CommentService implements CommonAction {
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String method = req.getMethod();
		
		
		
		if(method.equals("POST")) {
			
			String parent = req.getParameter("parent");
			String uid = req.getParameter("uid");
			String nick = req.getParameter("nick");
			String content = req.getParameter("content");
			String regip = req.getRemoteAddr();
			
	
			Connection conn = DBConfig.getConnection();
			CallableStatement call = conn.prepareCall(SQL.INSERT_COMMENT);
			call.setString(1, parent);
			call.setString(2, content);
			call.setString(3, uid);
			call.setString(4, regip);
			
			call.execute();
	
			call.close();
			conn.close();
			
			// 리턴할 데이터 구하기
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
			String date = sdf.format(new Date());
						
			JSONObject json = new JSONObject();
			json.put("content", content);
			json.put("nick", nick);
			json.put("date", date);

			
			return json.toString();
		} else {
			return null;
		}
	}
}
