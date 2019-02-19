package coffee12.kr.farmstory.service.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffee12.kr.farmstory.controller.CommonAction;

public class ListService implements CommonAction {
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			
		String cate = req.getParameter("cate");
		String gr = req.getParameter("gr");
		
		req.setAttribute("cate", cate);
		req.setAttribute("gr", gr);
		
		return "/board/list.jsp";
	}
}