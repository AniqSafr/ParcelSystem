package com.xadmin.parcelsystem.web;

//import com.xadmin.parcelsystem.*;
import com.xadmin.parcelsystem.connection.GetConnection;
import com.xadmin.parcelsystem.dao.UserDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String umatric = request.getParameter("matric");
		String upwd = request.getParameter("pass");
		HttpSession session = request.getSession();
		Connection con = null;
		RequestDispatcher dispatcher = null;
		
		if(umatric == null || umatric.equals("")){
			request.setAttribute("status","invalidMatric");
			//
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}if(upwd == null || upwd.equals("")){
			request.setAttribute("status","invalidUpwd");
			//
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		try {
		    con = GetConnection.getConnection();
		    UserDAO userDAO = new UserDAO(con);
		    boolean isValidUser = userDAO.validateUser(umatric, upwd);

		    if (isValidUser) {
		        session.setAttribute("matric", umatric);
		        dispatcher = request.getRequestDispatcher("dashboard.jsp");
		    } else {
		        request.setAttribute("status", "failed");
		        dispatcher = request.getRequestDispatcher("login.jsp");
		    }
		    dispatcher.forward(request, response);
		} finally {
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
	}
}