<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="kr.co.board1.service.BoardService"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="kr.co.board1.vo.MemberVO"%>
<%@page import="kr.co.board1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.co.board1.config.DBConfig"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getServletContext().getRealPath("/data");
	int maxSize = 1024 * 1024* 10;  // 10MB

	MultipartRequest mr = new MultipartRequest(request, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	String title 		= mr.getParameter("subject");
	String content		= mr.getParameter("content");
	String fileName 	= mr.getFilesystemName("file"); // 첨부한 파일 이름
	String regip 		= request.getRemoteAddr();
	
	BoardService service = BoardService.getInstance();
	MemberVO member = (MemberVO)session.getAttribute("member");
	String uid = member.getUid();
	
	
		
	int file = 0; // 파일상태변수 선언
	
	if(fileName != null){
		file = 1;
		// 파일명 생성(UUID)
		int idx = fileName.lastIndexOf("."); //확장자 점 위치구하기
		String ext = fileName.substring(idx); // 확장자만 추출 ex)".pptx"
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss_");
		String now = sdf.format(date);
		
		String newFileName = now+uid+ext; // ex)180124105011+_abcd.pptx
		
		// 파일명 변경 -해당파일과 스트림 연걸
		byte[] buf = new byte[1024];
		
		File oldFile = new File(path+"/"+fileName); // 파일객체 만들기
		File newFile = new File(path+"/"+newFileName);
		
		// oldFile.renameTo(newFile);  하면 밑에 있는 동작이랑 같음 
		
		FileInputStream fis = new FileInputStream(oldFile);
		FileOutputStream fos = new FileOutputStream(newFile);
		
		// 복사하고 원본은 지움
		int read = 0;
		
		while(true){ // 
			read = fis.read(buf, 0, buf.length);
			if(read == -1){
				break; // 더 이상 가져올 바이너리 데이터가 없을경우
			}
			fos.write(buf, 0, read);
		}
		fis.close();
		fos.close();
		
		oldFile.delete(); // 원본파일 제거
		
		// 글 등록
		int seq = service.write(file, title, content, uid, regip); // 게시물을 테이블에 넣기, 글을 먼저 테이블에 넣어야 seq를 받아올 수 있음
		// 파일테이블 INSERT
		service.fileInsert(seq, fileName, newFileName);
		
	} else{ // 파일첨부 안하고 글만등록
		service.write(file, title, content, uid, regip); // 게시물을 테이블에 넣기, 글을 먼저 테이블에 넣어야 seq를 받아올 수 있음
	}
	
		
	
	response.sendRedirect("../list.jsp");
	
%>
