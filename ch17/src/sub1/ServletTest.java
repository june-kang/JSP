package sub1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletTest extends HttpServlet {
	
	@Override
	public void init() throws ServletException { // 서블릿을 최초로 실행했을때 실행되는 메서드
		System.out.println("서블릿 최초실행...");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		requestProc(req, resp);
	}
	
	public void requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf-8"); // response에 타입설정
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>서블릿 테스트</title>");
		out.println("<meta charset='utf-8'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>서블릿 실행완료!</h3>");
		out.println("</body>");
		out.println("</html>");
	}
}
