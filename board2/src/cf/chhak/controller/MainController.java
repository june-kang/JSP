package cf.chhak.controller;

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
				Class<?> obj = Class.forName(v);
				Object instance = obj.newInstance();
				
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
		
		String path = req.getContextPath();
		String uri = req.getRequestURI();
		String action = uri.substring(path.length());
		
		String result = null;
		CommonAction instance = (CommonAction) instances.get(action);
		
		try {
			result = instance.requestProc(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 넘어오는 result값에따라 케이스나누기
		if(result.startsWith("redirect:")) {// 리다이렉트 명령 일 경우
			String redirectAddr = result.substring(9);
			resp.sendRedirect(redirectAddr);
		} else if(result.startsWith("{")) { //json값. 컨트롤러가 model에서 받은 값을 view를 안거치고 브라우저로 내보냄 or instance instance of UserCheckService - 인스턴스가 UserCheckService 이면
			PrintWriter out = resp.getWriter();
			out.print(result);
			
		} else {  // forward 는view만 가능
			RequestDispatcher dispatcher = req.getRequestDispatcher(result); // .jsp 로 끝나야함 : view페이지
			dispatcher.forward(req, resp);
		}
		
		
	} 
}
