package com.laptrinhjavaweb.controller.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns= {"/admin-building"})
public class BuildingController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7665624487288931965L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException,IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/views/building/list.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException,IOException{
		
	}
}
