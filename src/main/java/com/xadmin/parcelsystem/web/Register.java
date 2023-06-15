package com.xadmin.parcelsystem.web;

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

/**
 * Servlet implementation class RegistartionServlet
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String umatric = request.getParameter("matric");
		String upwd = request.getParameter("pass");
		String Reupwd = request.getParameter("re_pass");
		RequestDispatcher dispatcher = null;
		Connection con = null;

		if (uname == null || uname.equals("")) {
			request.setAttribute("status", "invalidName");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if(uemail == null || uemail.equals("")){
			request.setAttribute("status","invalidEmail");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if (umatric == null || umatric.equals("")) {
			request.setAttribute("status", "invalidMatric");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		}
		if (upwd == null || upwd.equals("")) {
			request.setAttribute("status", "invalidUpwd");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);
		} else if (!upwd.equals(Reupwd)) {
			request.setAttribute("status", "invalidConfirmPassword");
			dispatcher = request.getRequestDispatcher("registration.jsp");
			dispatcher.forward(request, response);

		}

		 try {
	            con = GetConnection.getConnection();
	            UserDAO userDAO = new UserDAO(con);
	            boolean success = userDAO.insertUser(uname, uemail, umatric, upwd);

	            dispatcher = request.getRequestDispatcher("registration.jsp");

	            if (success) {
	                request.setAttribute("status", "success");
	            } else {
	                request.setAttribute("status", "failed");
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
