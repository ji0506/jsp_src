package pre08.sec01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FristServlet
 */
@WebServlet("/login.do")
public class FristServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FristServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("user_id");
		String pwd = request.getParameter("user_pwd");		
		String address = request.getParameter("user_address");
		String hp = request.getParameter("user_hp");		
		String email = request.getParameter("user_email");		

		
	    String data = new String("");
        data += "<html>";
        data += "<body>";
        data += "아이디:" + id + "<br>";
        data += "패스워드:" + pwd + "<br>";
        data += "주소:" + address + "<br>";
        data += "hp:" + hp + "<br>";
        data += "이메일:" + email + "<br>";
        
        address = URLEncoder.encode(address,"utf-8");
		//		response.sendRedirect("/second?name=lee");
        data += "</body>";
        data += "</html>";

        out.print(data);	
        out.print("<a href='/second?id=" + id + "&pwd="+ pwd +"&address="+ address + "'>두번째 서블릿으로</a>");


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
