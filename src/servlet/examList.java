package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.bcel.internal.generic.NEW;

import entity.User;

/**
 * Servlet implementation class examList
 */
@WebServlet("/examList")
public class examList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public examList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		User user=new User("", "", "");
		JSONObject jsonData=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonData1=new JSONObject();
		String []examList=user.queryExamClass();

		try {
		for(String e:examList){
			jsonData1=new JSONObject();
			jsonData1.put("name", e);
			jsonArray.put(jsonData1);
		}
		jsonData.put("examClass", jsonArray);
		response.getWriter().println(jsonData);
		} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
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
