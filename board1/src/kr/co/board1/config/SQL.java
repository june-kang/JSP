package kr.co.board1.config;

public class SQL {
	
	public static final String SELECT_TERMS = "SELECT * FROM `JSP_TERMS`";
	public static final String INSERT_REGISTER = "INSERT INTO `JSP_MEMBER` SET uid=?, pass=PASSWORD(?), name=?, nick=?, email=?, hp=?, zip=?, addr1=?, addr2=?, regip=?, rdate=NOW()";
	public static final String SELECT_lOGIN = "SELECT * FROM `JSP_MEMBER` WHERE uid=? AND pass=?";

}