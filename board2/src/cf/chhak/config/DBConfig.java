package cf.chhak.config; // 패키지 이름은 도메인 거꾸로

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
	
	// 데이터베이스 정보
	private static final String HOST = "jdbc:mysql://192.168.0.126:3306/ksw";
	private static final String USER = "ksw";
	private static final String PASS = "1234";
	
	
	public static Connection getConnection() throws Exception { // 단하나의 인스턴스로 만들어서 효율성을 높임
			
			// 1단계
			Class.forName("com.mysql.jdbc.Driver");
			// 2단계
			Connection conn = DriverManager.getConnection(HOST,USER,PASS);
		
			return conn;
	}
	
}