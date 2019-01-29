package sub1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>¿ŒªÁ</title></head>");
		out.println("<body>");
		out.println("æ»≥Á«œººø‰, ");
		out.println(req.getParameter("name"));
		out.println("¥‘");
		out.println("</body>");
		out.println("</html>");
	}
	
	
	

}
