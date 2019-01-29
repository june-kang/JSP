package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CommonAction;

public class Greeting implements CommonAction {
	
	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) {
		
		return "/greeting.jsp";
	}
	
}
