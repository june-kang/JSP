package coffee12.kr.farmstory.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coffee12.kr.farmstory.controller.CommonAction;

public class HelloService implements CommonAction {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		return "/introduction/hello.jsp";
	}

}
