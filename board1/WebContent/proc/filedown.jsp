<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%		
	request.setCharacterEncoding("UTF-8");
	String seq = request.getParameter("seq");
	String newName = request.getParameter("newName"); // 20180124~
	String oldName = request.getParameter("oldName"); // ~.pptx
	
	// 파일 다운로드 카운트 업데이트
	Connection conn = DBConfig.getConnection();
	PreparedStatement pstmt = conn.prepareStatement(SQL.UPDATE_FILE);
	pstmt.setString(1,seq);
	pstmt.executeUpdate();
	
	pstmt.close();
	conn.close();
		

	// 파일이 위치한 경로구하기
	String path = request.getServletContext().getRealPath("/data");  // 상대경로
	File file = new File(path+"/"+newName); // data 디렉토리의 요청된 파일객체 
	String name = new String(oldName.getBytes("UTF-8"), "iso-8859-1");
	
	// 파일을 다운로드해주는 response정보 준비 
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename="+name);
	response.setHeader("Content-Transfer-Encoding", "binary");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "private");
	
	// 스트림 연결 : 파일 ------ response객체
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); // 속도개선
	out.clear();
	BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream()); // 파일이 대상이 아니어서 그냥 OutputStream
	
	byte b[] = new byte[1024]; // 버퍼크기
	
	int read = 0;
	
	while(true){
		// Input스트림으로 데이터 읽어오기 
		read = bis.read(b);
		if(read == -1){
			break;			
		}
		// Output스트림으로 데이터쓰기
		bos.write(b,0,read);
	}
	
	bos.flush(); // 혹시 남아있는 데이터 내보내기
	bos.close(); // 연결끊기
	bis.close();
%>