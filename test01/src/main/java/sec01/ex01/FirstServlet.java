package sec01.ex01;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "Login", urlPatterns = { "/login.do" })
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init 메서도 호출");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet 메서도 호출");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String str = request.getParameter("user_id");
		String str2 = request.getParameter("user_pwd");
		System.out.println("아이디 : " + str + ", 패스워드 : " + str2);
		
		String[] str3 = request.getParameterValues("subject");
		System.out.println("아이디 : " + str + ", 패스워드 : " + str2 + " " + Arrays.toString(str3));


		response.getWriter().append("id: " + str + " pwd: " + str2 + " check : " + Arrays.toString(str3));
	  /*  String data = new String("");
        data += "<html>";
        data += "<body>";
        data += "아이디:" + id + "<br>";
        data += "패스워드:" + pw + "<br>";
        data += "주소:" + adress;
        data += "</body>";
        data += "</html>";*/
//		response.sendRedirect("test.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		doGet(request, response);
	}

}
