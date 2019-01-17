package kr.co.board1.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import kr.co.board1.config.DBConfig;
import kr.co.board1.config.SQL;
import kr.co.board1.vo.TermsVO;

public class MemberService {
	
	private static MemberService service = new MemberService(); // �ڱ��ڽ��� �ν��Ͻ��� ����. �����ڿ�����x. Ŭ���� ������ �� �ϳ��� ����
	
	public static MemberService getInstance() { // �ٸ� ���������� service ��ü ����ϱ� ���� �޼ҵ�, �̱��� ��ü ���� 440p ����
		return service;
	}
	
	// �����ڴ� ������ public������ �̱������� �����ϱ� ���� Ŭ������ private���� ����. �׷��� new ���� ����
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
