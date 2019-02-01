package cf.chhak.service.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import cf.chhak.config.DBConfig;
import cf.chhak.config.SQL;
import cf.chhak.controller.CommonAction;
import cf.chhak.vo.MemberVO;

public class LoginService implements CommonAction {
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
	/*	String method = req.getMethod();
		System.out.println("method : " +method); �޼ҵ� ��ҹ��� Ȯ���غ���*/
		HttpSession session = req.getSession();
		
		if(req.getMethod().equals("POST")) { // ��ҹ��� �����ؾ���
			// POST ��û�� ���	
			String uid = req.getParameter("uid");
			String pass = req.getParameter("pass");
			String redirectUrl = ""; // null������ �ʱ�ȭ
			
			

			Connection conn = DBConfig.getConnection(); // �̱��� ��ü, ���ϳ��� �ν��Ͻ��� ���
				
			PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_lOGIN);
			
			pstmt.setString(1,uid);
			pstmt.setString(2,pass);
			
			ResultSet rs = pstmt.executeQuery();
				
			if(rs.next()){
				// ���̵�� ��й�ȣ�� ��ġ�ϴ� ȸ���� ���̺��� ���� ���
				// ���� ����
				MemberVO vo = new MemberVO();
				vo.setSeq(rs.getInt(1));
				vo.setUid(rs.getString(2));
				vo.setPass(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setNick(rs.getString(5));
				vo.setEmail(rs.getString(6));
				vo.setHp(rs.getString(7));
				vo.setGrade(rs.getInt(8));
				vo.setZip(rs.getString(9));
				vo.setAddr1(rs.getString(10));
				vo.setAddr2(rs.getString(11));
				vo.setRegip(rs.getString(12));
				vo.setRdate(rs.getString(13));
				
				session.setAttribute("member",vo);
				
				redirectUrl = "/board2/list.do";
			
				
			} else{
				// ���̵�� ��й�ȣ�� ��ġ�ϴ� ȸ���� �������
				redirectUrl = "/board2/member/login.do?result=fail"; // ����� �������
			}
						
			rs.close();
			pstmt.close();
			conn.close();
			
			return "redirect:"+redirectUrl;
		}else {
			
			// �α��λ��� üũ
			if(session.getAttribute("member") != null) {
				return "redirect:/board2/list.do";
			} else {
				return "/login.jsp";
			}
		
	}
	}
}