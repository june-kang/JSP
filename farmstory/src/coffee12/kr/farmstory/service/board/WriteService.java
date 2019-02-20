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
			
			String path = req.getServletContext().getRealPath("/data"); // ������ ����� ���
			int maxSize = 1024 * 1024* 10;  // ����ũ�� 10MB�� ����

			MultipartRequest mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
			String gr			= mr.getParameter("gr");
			String cate			= mr.getParameter("cate");
			String title 		= mr.getParameter("subject");
			String content		= mr.getParameter("content");
			String fileName 	= mr.getFilesystemName("file"); // ÷���� ���� �̸�
			String regip 		= req.getRemoteAddr();

			HttpSession session = req.getSession();
			MemberVO member = (MemberVO)session.getAttribute("member");
			String uid = member.getUid();



			int file = 0; // ���ϻ��º��� ����

			if(fileName != null){
				file = 1;
				// ���ϸ� ����(UUID)
				int idx = fileName.lastIndexOf("."); //Ȯ���� �� ��ġ���ϱ�
				String ext = fileName.substring(idx); // Ȯ���ڸ� ���� ex)".pptx"

				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss_");
				String now = sdf.format(date);

				String newFileName = now+uid+ext; // ex)180124105011+_abcd.pptx

				// ���ϸ� ���� -�ش����ϰ� ��Ʈ�� ����
				byte[] buf = new byte[1024];

				File oldFile = new File(path+"/"+fileName); // ���ϰ�ü �����
				File newFile = new File(path+"/"+newFileName);

				// oldFile.renameTo(newFile);  �ϸ� �ؿ� �ִ� �����̶� ���� 

				FileInputStream fis = new FileInputStream(oldFile);
				FileOutputStream fos = new FileOutputStream(newFile);

				// �����ϰ� ������ ����
				int read = 0;

				while(true){ // 
					read = fis.read(buf, 0, buf.length);
					if(read == -1){
						break; // �� �̻� ������ ���̳ʸ� �����Ͱ� �������
					}
					fos.write(buf, 0, read);
				}
				fis.close();
				fos.close();

				oldFile.delete(); // �������� ����

				// �� ���
				int seq = write(file, cate, title, content, uid, regip); // �Խù��� ���̺� �ֱ�, ���� ���� ���̺� �־�� seq�� �޾ƿ� �� ����
				// �������̺� INSERT
				fileInsert(seq, fileName, newFileName);

			} else{ // ����÷�� ���ϰ� �۸����
				write(file, cate, title, content, uid, regip); // �Խù��� ���̺� �ֱ�, ���� ���� ���̺� �־�� seq�� �޾ƿ� �� ����
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
	public int write(int file, String... args) throws Exception { // �����Ű�����

		Connection conn = DBConfig.getConnection();

		// Ʈ������� ����. �� �������� ���� �ٸ� ��ɽ���x
		conn.setAutoCommit(false); 


		/* �ٸ� ��� ������ �ΰ� ������ϰ� statement���� Ű�޾ƿ��¹�
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

		conn.commit(); // �ΰ��� ������ ���ÿ� ����. �߰��� ��ְ� �߻����� ��� rollback �ؼ� ���������� ���ư�

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