	package com.xadmin.parcelsystem.web;
	
	import java.io.IOException;
	import java.sql.SQLException;
	import java.util.List;
	import java.text.ParseException;
	
	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletConfig;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;
	
	import com.xadmin.parcelsystem.bean.Parcel;
	import com.xadmin.parcelsystem.dao.ParcelDao;
	
	/**
	 * Servlet implementation class ParcelServlet
	 */
	@WebServlet("/")
	public class ParcelServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		
		private ParcelDao ParcelDao;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public ParcelServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	
		/**
		 * @see Servlet#init(ServletConfig)	
		 */
		public void init() {
			ParcelDao = new ParcelDao();
			
		}
	
		
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
		
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String action = request.getServletPath();
			
			try {
				switch (action) {
				case "/new":
					showNewForm(request, response);
					break;
				case "/insert":
					insertUser(request, response);
					break;
				case "/delete":
					deleteUser(request, response);
					break;
				case "/edit":
					showEditForm(request, response);
					break;
				case "/update":
					updateUser(request, response);
					break;
				case "/search":
					searchList(request, response);
					break;
				case "/searchAvailable":
					searchAvailable(request, response);
					break;
				case "/available":
					listAvailable(request, response);
					break;
				case "/logout":
					logout(request, response);
					break;
				case "/report":
	                generateReport(request, response);
	                break;
				default:
					listUser(request, response);
					break;
				}
			} catch (SQLException | ParseException ex) {
				throw new ServletException(ex);
			}
		}
		
		private void listUser(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<Parcel> listUser = ParcelDao.selectAllUsers();
			request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
			dispatcher.forward(request, response);
		}
		
		private void listAvailable(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, IOException, ServletException {
			List<Parcel> listUser = ParcelDao.selectAvailable();
			request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("available-list.jsp");
			dispatcher.forward(request, response);
		}
	
		private void showNewForm(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
			dispatcher.forward(request, response);
		}
	
		private void showEditForm(HttpServletRequest request, HttpServletResponse response)
				throws SQLException, ServletException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			Parcel existingUser = ParcelDao.selectUser(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("checkout-form.jsp");
			request.setAttribute("parcel", existingUser);
			dispatcher.forward(request, response);
		}
	
		private void insertUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException, ParseException {
			String name = request.getParameter("name");
			String noPhone = request.getParameter("noPhone");
			String noMatric = request.getParameter("noMatric");
			
			Parcel newUser = new Parcel(name, noPhone, noMatric);
			ParcelDao.insertUser(newUser);
			response.sendRedirect("available");
		}
	
		private void updateUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException, ParseException {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String noPhone = request.getParameter("noPhone");
			String noMatric = request.getParameter("noMatric");
	
			Parcel book = new Parcel(id, name, noPhone, noMatric);
			ParcelDao.updateUser(book);
			response.sendRedirect("available");
		}
	
		private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
				throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			ParcelDao.deleteUser(id);
			response.sendRedirect("list");
	
		}
		
		private void searchList(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException {
		    String dateIn = request.getParameter("dateIn"); // Assuming the search parameter is named "dateIn"
		    List<Parcel> listUser = ParcelDao.selectAllsearch(dateIn);
		    request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
			dispatcher.forward(request, response);
		}
		
		private void searchAvailable(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException {
		    String dateIn = request.getParameter("searchTerm"); // Assuming the search parameter is named "dateIn"
		    List<Parcel> listUser = ParcelDao.selectAvailablesearch(dateIn);
		    request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("available-list.jsp");
			dispatcher.forward(request, response);
		}
		
		private void logout(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			  
		    HttpSession session = request.getSession();
		    session.invalidate();
		    response.sendRedirect("login.jsp");
		  }
		
		private void generateReport(HttpServletRequest request, HttpServletResponse response)
		        throws SQLException, IOException, ServletException {
		    String available = request.getParameter("available");
		    int count = ParcelDao.getCountOfDateIn(available);
		    
		    String all = request.getParameter("available");
		    int count_all = ParcelDao.getAllCountOfDateIn(all);
		    
		    int count_out = count_all - count;
		    
		    double total = (count_all - count) * 0.5; // Calculate the profit

		    // Set the count and money as request attributes
		    request.setAttribute("countInDateIn", count);
		    request.setAttribute("countAllDateIn", count_all);
		    request.setAttribute("countOutDateIn", count_out);
		    request.setAttribute("total", total);

		    RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
		    dispatcher.forward(request, response);
		}

	
	
	
	
	}
