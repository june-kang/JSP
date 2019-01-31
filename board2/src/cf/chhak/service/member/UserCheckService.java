package cf.chhak.service.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

import cf.chhak.config.DBConfig;
import cf.chhak.config.SQL;
import cf.chhak.controller.CommonAction;

public class UserCheckService implements CommonAction {
	// Model �� CommonAction interface ���� - ��Ʈ�ѷ� ����

	@Override // ��ӹ��� Ŭ������ �������̵� �޼��� �����ؾ���
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		

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
		
		// JSON format���� ��ȯ
		JSONObject json = new JSONObject();
		json.put("result", count);
		
		String result = json.toString();
		
		System.out.println("result : " + result);
		
		return result; // ��Ʈ�ѷ� result������ �Ѿ!
	}
}
