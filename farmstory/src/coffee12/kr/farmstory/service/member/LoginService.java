package coffee12.kr.farmstory.service.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coffee12.kr.farmstory.config.DBConfig;
import coffee12.kr.farmstory.config.SQL;
import coffee12.kr.farmstory.controller.CommonAction;
import coffee12.kr.farmstory.vo.MemberVO;

public class LoginService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
	/*	String method = req.getMethod();
		System.out.println("method : " +method); 메소드 대소문자 확인해보기*/
		HttpSession session = req.getSession();
		
		if(req.getMethod().equals("POST")) { // 대소문자 구분해야함
			// POST 요청일 경우	
			String uid = req.getParameter("uid");
			String pass = req.getParameter("pass");
			String redirectUrl = ""; // null값으로 초기화
			
			

			Connection conn = DBConfig.getConnection(); // 싱글톤 객체, 단하나의 인스턴스로 사용
				
			PreparedStatement pstmt = conn.prepareStatement(SQL.SELECT_lOGIN);
			
			pstmt.setString(1,uid);
			pstmt.setString(2,pass);
			
			ResultSet rs = pstmt.executeQuery();
				
			if(rs.next()){
				// 아이디와 비밀번호가 일치하는 회원이 테이블에 있을 경우
				// 세션 저장
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
				
				redirectUrl = "/farmstory/index.do";
			
				
			} else{
				// 아이디와 비밀번호가 일치하는 회원이 없을경우
				redirectUrl = "/farmstory/member/login.do?result=fail"; // 결과값 들고가라
			}
						
			rs.close();
			pstmt.close();
			conn.close();
			
			return "redirect:"+redirectUrl;
		}else {
			
			// 로그인상태 체크
			if(session.getAttribute("member") != null) {
				return "redirect:/farmstory/index.do";
			} else {
				return "/member/login.jsp";
			}
		
	}
	}
}
