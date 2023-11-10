	package controller;
	
	import java.io.IOException;
	
	import dao.IDaoLocal;
	import entities.Student;
	import jakarta.ejb.EJB;
	import jakarta.servlet.ServletException;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;
	
	/**
	 * Servlet implementation class StudentController
	 */
	public class StudentController extends HttpServlet {
		private static final long serialVersionUID = 1L;
	
		@EJB
		private IDaoLocal<Student> dao;
	
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public StudentController() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
	
			//dao.create(new Student("Mohamed", "naja"));
			
	
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
		

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String firstName = request.getParameter("fname");
		    String lastName = request.getParameter("lname");


		    // Create a new student
		    Student student = new Student(firstName, lastName);

		    // Persist the student using the DAO
		    dao.create(student);

		    // Optionally, you can redirect to a success page or display a success message
		    response.sendRedirect("webapp\\sucess.jsp");
			//doGet(request, response);
		}
	
	}
