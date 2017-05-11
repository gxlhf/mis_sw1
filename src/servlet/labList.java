package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.LineUnavailableException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.User;

/**
 * Servlet implementation class labList
 */
@WebServlet("/labList")
public class labList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public labList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("out");
		response.setContentType("text/html;charset=UTF-8");
		User user=new User("", "","");
		String[] labList=user.queryTestClass();
		JSONObject jsonData=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		for(String s:labList)
			if(s!=null)
			System.out.println(s);
		System.out.println("out");
		try {
			for(String s:labList)
				jsonArray.put(s);
			jsonData.put("labClass", jsonArray);
			response.getWriter().println(jsonData.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
