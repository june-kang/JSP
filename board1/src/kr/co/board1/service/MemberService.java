package kr.co.board1.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.board1.config.DBConfig;
import kr.co.board1.config.SQL;
import kr.co.board1.vo.TermsVO;

public class MemberService {
	
	private static MemberService service = new MemberService(); // 자기자신을 인스턴스로 만듬. 서버자원낭비x. 클래스 영역에 딱 하나만 고정
	
	public static MemberService getInstance() { // 다른 페이지에서 service 객체 사용하기 위한 메소드, 싱글톤 객체 교재 440p 참조
		return service;
	}
	
	// 생성자는 무조건 public이지만 싱글톤으로 관리하기 위한 클래스는 private으로 생성. 그래야 new 생성 못함
	private MemberService() {}
	
	public void login() throws Exception {}
	public void logout() throws Exception {}
	
	public TermsVO terms() throws Exception {
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
		
		return vo;
	}
	public void register() throws Exception {}
	
	

}
