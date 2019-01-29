package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommonAction;

public class Welcome implements CommonAction {
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		return "/welcome.jsp";
	}
	
}
