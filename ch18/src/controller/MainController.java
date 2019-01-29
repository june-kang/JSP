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
	
	private static final long serialVersionUID = -6702672335405420505L; // servlet을 구분하는 번호
	private Map<String, Object> instances = new HashMap<>(); // 자바의 다형성. 부모 타입으로 참조가능
		
	@Override
	public void init(ServletConfig config) throws ServletException { // 제일 처음 tomcat실행할때 hashmap 만들어서 model 객체 저장하기 계속 new 만들면 서버터짐
		
		// properties 파일경로 추출
		ServletContext ctx = config.getServletContext();
		String path = ctx.getRealPath("/WEB-INF")+"/commandURI.properties";// full경로 필요함
		
		// properties 정보를 통한 객체 생성. 프로퍼티파일로 프로퍼티객체 만듬
		Properties prop = new Properties();
		FileInputStream fis = null;
		
		try {
			// commandURI.properties 파일과 스트림 연결
			fis = new FileInputStream(path);
			
			// 입력스트림으로 commandURI.properties 파일 데이터  읽기
			prop.load(fis);

			// 스트림 해제
			fis.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		// Model 클래스 객체를 생성해서 instances에 저장
		Iterator it = prop.keySet().iterator(); // Property의 key 값을 set으로 만듬. set은 자료를 꺼낼때 무작위로 꺼냄 - iterator 필요
		
		while(it.hasNext()) {
			String k = it.next().toString();
			String v = prop.getProperty(k);
			
			try {
				Class<?> obj = Class.forName(v); // value값의 클래스를 찾음
				Object instance = obj.newInstance();
				
				instances.put(k, instance);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	} // init 끝
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	
	private void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getContextPath(); //  주소에서 /ch18/  부분
		String uri = req.getRequestURI(); // 서버경로
		String action = uri.substring(path.length());
			
		String view = null;
		
		// properties를 이용해 action 주소와 경로를 HashMap으로 저장. 아래 귀찮음 new를 많이쓰면 서버자원을 많이 소모.
		CommonAction instance = (CommonAction) instances.get(action);
		view = instance.requestProc(req, resp);
				
		RequestDispatcher dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, resp);
		
	}
	
}
