package kr.co.board1.config;

public class SQL {
	
	public static final String SELECT_TERMS = "SELECT * FROM `JSP_TERMS`";
	public static final String INSERT_REGISTER = "INSERT INTO `JSP_MEMBER` SET uid=?, pass=PASSWORD(?), name=?, nick=?, email=?, hp=?, zip=?, addr1=?, addr2=?, regip=?, rdate=NOW()";
	public static final String SELECT_lOGIN = "SELECT * FROM `JSP_MEMBER` WHERE uid=? AND pass=PASSWORD(?)";
	public static final String INSERT_BOARD = "INSERT INTO `JSP_BOARD` SET "
											+ "cate='notice',"
											+ "title=?,"
											+ "content=?,"
											+ "uid=?,"
											+ "regip=?,"
											+ "rdate=NOW()";
	public static final String SELECT_LIST = "SELECT B.*, M.nick FROM JSP_BOARD AS B JOIN JSP_MEMBER AS M ON B.uid = M.uid ORDER BY B.seq DESC";
	public static final String SELECT_VIEW = "SELECT * FROM `JSP_BOARD` WHERE seq=?";
	public static final String UPDATE_HIT = "UPDATE `JSP_BOARD` SET hit=hit+1 WHERE seq=?";
	public static final String DELETE_VIEW = "DELETE FROM `JSP_BOARD` WHERE seq=?";
}