package coffee12.kr.farmstory.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {
	
	private static final long serialVersionUID = 8373294618728732262L;
	private Map<String, Object> instances = new HashMap<>(); 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		ServletContext ctx = config.getServletContext();
		String path = ctx.getRealPath("/WEB-INF")+"/commandURI.properties";
		
		Properties prop =  new Properties();
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(path);
			prop.load(fis);
			fis.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Iterator itr = prop.keySet().iterator();
		
		while(itr.hasNext()) {
			String k = itr.next().toString();
			String v = prop.getProperty(k);
			
			try {
				Class<?> obj = Class.forName(v); /*Ŭ������ ������ ���� ClassŬ���� �ν��Ͻ� ��ü�� ����*/
				Object instance = obj.newInstance(); /*Ŭ������ �ν��Ͻ� �����ؼ� �����ڸ� ȣ���� �ʱ�ȭ�ϰ� ������ �ν��Ͻ� ���� */
				
				instances.put(k,instance);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			requestProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			requestProc(req, resp);
	}
	
	private void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getContextPath(); /*project�� path�� ��ȯ - farmstory*/
		String uri = req.getRequestURI(); /*project+���ϰ�� ��ȯ*/
		String action = uri.substring(path.length());
		
		String result = null;
		CommonAction instance = (CommonAction) instances.get(action);
		
		try {
			result = instance.requestProc(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// �Ѿ���� result�������� ���̽�������
		if(result.startsWith("redirect:")) {// �����̷�Ʈ ��� �� ���
			String redirectAddr = result.substring(9);
			resp.sendRedirect(redirectAddr);
		} else if(result.startsWith("{")) { //json��. ��Ʈ�ѷ��� model���� ���� ���� view�� �Ȱ�ġ�� �������� ������ or instance instance of UserCheckService - �ν��Ͻ��� UserCheckService �̸�
			resp.setContentType("text/html;charset=UTF-8"); // ajax�� ��ü�� �ƴ϶� String ���·� �޴µ� ������ euc-kr�̶� ȣȯ�� �ȵ� ���� �����ؾ���
			PrintWriter out = resp.getWriter();
			out.print(result);
			
		} else {  // forward ��view�� ����
			RequestDispatcher dispatcher = req.getRequestDispatcher(result); // .jsp �� �������� : view������
			dispatcher.forward(req, resp);
		}
		
		
	} 
}
