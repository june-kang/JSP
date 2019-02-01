package cf.chhak.service.member;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cf.chhak.controller.CommonAction;
import cf.chhak.config.DBConfig;
import cf.chhak.config.SQL;

public class RegisterService implements CommonAction {
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equals("GET")) {
			// ȸ������ ��(GET)
			return "/register.jsp";
		} else {
			// ȸ������ ó��(POST)
			// �Ķ���� ����
			req.setCharacterEncoding("UTF-8"); 
			String uid	 = req.getParameter("uid");
			String pass	 = req.getParameter("pw1");
			String name	 = req.getParameter("name");
			String nick	 = req.getParameter("nick");
			String email = req.getParameter("email");
			String hp 	 = req.getParameter("hp");
			String zip 	 = req.getParameter("zip");
			String addr1 = req.getParameter("addr1");
			String addr2 = req.getParameter("addr2");
			String regip = req.getRemoteAddr();



			// 2�ܰ�
				Connection conn = DBConfig.getConnection();
			// 3�ܰ�
				PreparedStatement psmt = conn.prepareStatement(SQL.INSERT_REGISTER);
			   
			   psmt.setString(1,uid);
			   psmt.setString(2,pass);
			   psmt.setString(3,name);
			   psmt.setString(4,nick);
			   psmt.setString(5,email);
			   psmt.setString(6,hp);
			   psmt.setString(7,zip);
			   psmt.setString(8,addr1);
			   psmt.setString(9,addr2);
			   psmt.setString(10,regip);
			// 4�ܰ�
				psmt.executeUpdate();
				   
			// 5�ܰ�

			// 6�ܰ�
				psmt.close();
				conn.close();
			
			return "redirect:/board2/member/login.do"; // redirect ���ɹ� �����(Spring ���)
		}
		
		
	}
}