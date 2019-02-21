package coffee12.kr.farmstory.config;

public class SQL {
	
	public static final String SELECT_TERMS = "SELECT * FROM `JSP_TERMS`";
	public static final String INSERT_REGISTER = "INSERT INTO `JSP_MEMBER` SET uid=?, pass=PASSWORD(?), name=?, nick=?, email=?, hp=?, zip=?, addr1=?, addr2=?, regip=?, rdate=NOW()";
	public static final String SELECT_UID_COUNT = "SELECT COUNT(*) FROM `JSP_MEMBER` WHERE uid=?";
	public static final String SELECT_NICK_COUNT = "SELECT COUNT(*) FROM `JSP_MEMBER` WHERE nick=?";
	public static final String SELECT_EMAIL_COUNT = "SELECT COUNT(*) FROM `JSP_MEMBER` WHERE email=?";
	public static final String SELECT_HP_COUNT = "SELECT COUNT(*) FROM `JSP_MEMBER` WHERE hp=?";
	public static final String SELECT_lOGIN = "SELECT * FROM `JSP_MEMBER` WHERE uid=? AND pass=PASSWORD(?)";
	public static final String SELECT_MAX_SEQ = "SELECT MAX(seq) FROM `JSP_BOARD`"; 
	public static final String INSERT_BOARD = "INSERT INTO `JSP_BOARD` SET "
											+ "cate=?,"
											+ "title=?,"
											+ "content=?,"
											+ "uid=?,"
											+ "file=?,"
											+ "regip=?,"
											+ "rdate=NOW()";
	public static final String INSERT_FILE = "INSERT INTO `JSP_FILE` (parent, oldName, newName, rdate) VALUES(?,?,?,NOW())";
	public static final String UPDATE_FILE = "UPDATE `JSP_FILE` SET download=download+1 WHERE parent=?";
	public static final String SELECT_COUNT = "SELECT COUNT(*) FROM `JSP_BOARD` WHERE parent=0 AND cate=?";
	public static final String SELECT_LIST = "SELECT B.*, M.nick FROM `JSP_BOARD` AS B JOIN `JSP_MEMBER` AS M ON B.uid = M.uid WHERE B.parent=0 AND B.cate=? ORDER BY B.seq DESC LIMIT ?, 10";
	public static final String SELECT_VIEW = "SELECT * FROM `JSP_BOARD` WHERE seq=?";
	public static final String SELECT_VIEW_WITH_FILE = "SELECT * FROM `JSP_BOARD` AS b LEFT JOIN `JSP_FILE` AS f ON b.seq = f.parent WHERE b.seq=?";
	public static final String UPDATE_HIT = "UPDATE `JSP_BOARD` SET hit=hit+1 WHERE seq=?";
	public static final String DELETE_VIEW = "DELETE FROM `JSP_BOARD` WHERE seq=?";
	public static final String UPDATE_BOARD = "UPDATE `JSP_BOARD` SET "
											+ "title=?, "
											+ "content=? "
											+ " WHERE seq=?";
	public static final String INSERT_COMMENT = "CALL insertComment(?,?,?,?)"; // 다중 쿼리문 실행 가능
	public static final String SELECT_COMMENT = "SELECT B.*, M.nick FROM `JSP_BOARD` AS B JOIN `JSP_MEMBER` AS M ON B.uid = M.uid WHERE B.parent=? ORDER BY B.seq ASC";
	public static final String SELECT_LATEST = "SELECT seq, cate, title, SUBSTRING(rdate, 3, 8) as rdate FROM `JSP_BOARD` WHERE cate=? ORDER BY seq DESC LIMIT 5";
	
	
}