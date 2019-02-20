package coffee12.kr.farmstory.service.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import coffee12.kr.farmstory.config.DBConfig;
import coffee12.kr.farmstory.config.SQL;
import coffee12.kr.farmstory.controller.CommonAction;

public class FileDownService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			
		req.setCharacterEncoding("UTF-8");
		String seq = req.getParameter("seq");
		String newName = req.getParameter("newName"); // 20180124~
		String oldName = req.getParameter("oldName"); // ~.pptx
		
		// 파일 다운로드 카운트 업데이트
		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.UPDATE_FILE);
		pstmt.setString(1,seq);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
			

		// 파일이 위치한 경로구하기
		String path = req.getServletContext().getRealPath("/data");  // 상대경로
		File file = new File(path+"/"+newName); // data 디렉토리의 요청된 파일객체 
		String name = new String(oldName.getBytes("UTF-8"), "iso-8859-1");
		
		// 파일을 다운로드해주는 response정보 준비 
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+name);
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		// 스트림 연결 : 파일 ------ response객체
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); // 속도개선
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream()); // 파일이 대상이 아니어서 그냥 OutputStream
		
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
		
		return "redirect:/farmstory/board/view.do";
	}
	
	

}
