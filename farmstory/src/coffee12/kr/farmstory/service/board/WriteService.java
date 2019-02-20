package coffee12.kr.farmstory.service.board;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import coffee12.kr.farmstory.config.DBConfig;
import coffee12.kr.farmstory.config.SQL;
import coffee12.kr.farmstory.controller.CommonAction;
import coffee12.kr.farmstory.vo.MemberVO;

public class WriteService implements CommonAction {
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		if(req.getMethod().equals("POST")) {
			
			String path = req.getServletContext().getRealPath("/data"); // 파일이 저장될 경로
			int maxSize = 1024 * 1024* 10;  // 파일크기 10MB로 제한

			MultipartRequest mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			String gr			= mr.getParameter("gr");
			String cate			= mr.getParameter("cate");
			String title 		= mr.getParameter("subject");
			String content		= mr.getParameter("content");
			String fileName 	= mr.getFilesystemName("file"); // 첨부한 파일 이름
			String regip 		= req.getRemoteAddr();

			HttpSession session = req.getSession();
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
				int seq = write(file, cate, title, content, uid, regip); // 게시물을 테이블에 넣기, 글을 먼저 테이블에 넣어야 seq를 받아올 수 있음
				// 파일테이블 INSERT
				fileInsert(seq, fileName, newFileName);

			} else{ // 파일첨부 안하고 글만등록
				write(file, cate, title, content, uid, regip); // 게시물을 테이블에 넣기, 글을 먼저 테이블에 넣어야 seq를 받아올 수 있음
			}


			return "redirect:/farmstory/board/list.do?gr="+gr+"&cate="+cate;

		}else {
			
			String cate = req.getParameter("cate");
			String gr = req.getParameter("gr");
			
			req.setAttribute("cate", cate);
			req.setAttribute("gr", gr);

			return "/board/write.jsp?gr="+gr+"&cate="+cate;

		}

	}
	public int write(int file, String... args) throws Exception { // 가변매개변수

		Connection conn = DBConfig.getConnection();

		// 트랜잭션을 시작. 한 동작으로 만들어서 다른 명령실행x
		conn.setAutoCommit(false); 


		/* 다른 방법 쿼리문 두개 실행안하고 statement에서 키받아오는법
		 * int key = 0;
		 * PreparedStatement pstmt = conn.prepareStatement(SQL.INSERT_BOARD, Statement.RETURN_GENERATED_KEYS);
		 * pstmt.setString(1, args[0]);
		 * pstmt.setString(2, args[1]);
		 * pstmt.setString(3, args[2]);
		 * pstmt.setInt(4, file);
		 * pstmt.setString(5, args[3]);
		 * pstmt.executeUpdate();
		 * ResultSet rs = pstmt.getGeneratedKeys();
		 * if (rs!=null && rs.next()){
		 * key = rs.getInt(1);
		 * }
		 */
		PreparedStatement pstmt = conn.prepareStatement(SQL.INSERT_BOARD);
		pstmt.setString(1, args[0]);
		pstmt.setString(2, args[1]);
		pstmt.setString(3, args[2]);
		pstmt.setString(4, args[3]);
		pstmt.setInt(5, file);
		pstmt.setString(6, args[4]);

		Statement stmt = conn.createStatement();

		pstmt.executeUpdate();
		ResultSet rs = stmt.executeQuery(SQL.SELECT_MAX_SEQ);

		conn.commit(); // 두개의 쿼리문 동시에 실행. 중간에 장애가 발생했을 경우 rollback 해서 시작점으로 돌아감

		int seq = 0;
		if(rs.next()) {
			seq = rs.getInt(1);
		}

		rs.close();
		pstmt.close();
		stmt.close();
		conn.close();

		return seq;

	}
	
	public void fileInsert(int parent, String oldName, String newFileName) throws Exception{

		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.INSERT_FILE);
		pstmt.setInt(1, parent);
		pstmt.setString(2, oldName);
		pstmt.setString(3, newFileName);

		pstmt.executeUpdate();

		pstmt.close();
		conn.close();
	}


}