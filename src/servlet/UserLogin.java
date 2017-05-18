package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;

public class UserLogin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserLogin() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processRequest(request, response);
	}

	/**
	 * The doPost method of the ser vlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);

	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String userName = request.getParameter("inputUser");
		String password = request.getParameter("inputPassword");
		String message = null;
		HttpSession session = request.getSession();
		User user = new User(userName, password, null);
		System.out.println("input: " + userName + " " + password);
		System.out.println("check: " + user.getUser() + " " + user.getPassword());
		int result = new UserDao().verifyUser(user);
		switch(result){
		case 0:break;
		case 1:message = "Error.Please repeat Password";
				break;
		case 2:message = "Error.Password Invalid";
				break;
		case -1:message="Error.User Not Found";
				break;
		case -2:message="Error.User Invalid";
				break;
		}
		request.setAttribute("error", message);
		if(message==null)
			request.getRequestDispatcher("/index.html").forward(request, response);
		else
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}