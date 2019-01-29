package controller;

import java.io.FileInputStream;
import java.io.IOException;
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

import model.Greeting;
import model.Hello;
import model.Introduce;
import model.Welcome;

public class MainController extends HttpServlet {
	
	private static final long serialVersionUID = -6702672335405420505L; // servlet�� �����ϴ� ��ȣ
	private Map<String, Object> instances = new HashMap<>(); // �ڹ��� ������. �θ� Ÿ������ ��������
		
	@Override
	public void init(ServletConfig config) throws ServletException { // ���� ó�� tomcat�����Ҷ� hashmap ���� model ��ü �����ϱ� ��� new ����� ��������
		
		// properties ���ϰ�� ����
		ServletContext ctx = config.getServletContext();
		String path = ctx.getRealPath("/WEB-INF")+"/commandURI.properties";// full��� �ʿ���
		
		// properties ������ ���� ��ü ����. ������Ƽ���Ϸ� ������Ƽ��ü ����
		Properties prop = new Properties();
		FileInputStream fis = null;
		
		try {
			// commandURI.properties ���ϰ� ��Ʈ�� ����
			fis = new FileInputStream(path);
			
			// �Է½�Ʈ������ commandURI.properties ���� ������  �б�
			prop.load(fis);

			// ��Ʈ�� ����
			fis.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		// Model Ŭ���� ��ü�� �����ؼ� instances�� ����
		Iterator it = prop.keySet().iterator(); // Property�� key ���� set���� ����. set�� �ڷḦ ������ �������� ���� - iterator �ʿ�
		
		while(it.hasNext()) {
			String k = it.next().toString();
			String v = prop.getProperty(k);
			
			try {
				Class<?> obj = Class.forName(v); // value���� Ŭ������ ã��
				Object instance = obj.newInstance();
				
				instances.put(k, instance);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	} // init ��
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	
	private void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getContextPath(); //  �ּҿ��� /ch18/  �κ�
		String uri = req.getRequestURI(); // �������
		String action = uri.substring(path.length());
			
		String view = null;
		
		// properties�� �̿��� action �ּҿ� ��θ� HashMap���� ����. �Ʒ� ������ new�� ���̾��� �����ڿ��� ���� �Ҹ�.
		CommonAction instance = (CommonAction) instances.get(action);
		view = instance.requestProc(req, resp);
				
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, resp);
		
	}
	
}
