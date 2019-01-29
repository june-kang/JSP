package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommonAction {
	
	public abstract String requestProc(HttpServletRequest req, HttpServletResponse resp);

}
