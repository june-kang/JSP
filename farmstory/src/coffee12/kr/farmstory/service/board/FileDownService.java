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
		
		// ���� �ٿ�ε� ī��Ʈ ������Ʈ
		Connection conn = DBConfig.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(SQL.UPDATE_FILE);
		pstmt.setString(1,seq);
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
			

		// ������ ��ġ�� ��α��ϱ�
		String path = req.getServletContext().getRealPath("/data");  // �����
		File file = new File(path+"/"+newName); // data ���丮�� ��û�� ���ϰ�ü 
		String name = new String(oldName.getBytes("UTF-8"), "iso-8859-1");
		
		// ������ �ٿ�ε����ִ� response���� �غ� 
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename="+name);
		resp.setHeader("Content-Transfer-Encoding", "binary");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "private");
		
		// ��Ʈ�� ���� : ���� ------ response��ü
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); // �ӵ�����
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream()); // ������ ����� �ƴϾ �׳� OutputStream
		
		byte b[] = new byte[1024]; // ����ũ��
		
		int read = 0;
		
		while(true){
			// Input��Ʈ������ ������ �о���� 
			read = bis.read(b);
			if(read == -1){
				break;			
			}
			// Output��Ʈ������ �����;���
			bos.write(b,0,read);
		}
		
		bos.flush(); // Ȥ�� �����ִ� ������ ��������
		bos.close(); // �������
		bis.close();
		
		return "redirect:/farmstory/board/view.do";
	}
	
	

}
