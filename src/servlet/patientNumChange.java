package servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import entity.User;

/**
 * Servlet implementation class patientNumChange
 */
@WebServlet("/patientNumChange")
public class patientNumChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public patientNumChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String from=URLDecoder.decode(request.getParameter("datefrom"), "UTF-8");
		String to=URLDecoder.decode(request.getParameter("dateto"), "UTF-8");
		Integer[] data=new User("", "", "").getPatientNumChange(from,to);
		JSONArray jsonData=new JSONArray();
		for(int i=0;i<data.length;i++){
			jsonData.put(data[i]);
		}
		response.getWriter().println(jsonData.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
