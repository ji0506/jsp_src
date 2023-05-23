package pre08.sec01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FristServlet
 */
@WebServlet("/second")
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
//		String str = request.getParameter("name");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");		
		String address = request.getParameter("address");

		out.print("<html><body>");
		if(id != null && id.length() !=0) {
			out.print("이미 로그인된 상태입니다. <br><br>");
			out.print("id : "+ id + "<br>");
			out.print("pwd : "+ pwd + "<br>");
			out.print("address : "+ address + "<br>");
		} else {
			out.print("로그인 하지 않았습니다. <br><br>");
			out.print("다시 로그인하세요. <br><br>");
			out.print("<a href='/login.html'>로그인창으로 이동하기</a>");
			
		}


		out.print("</body></html>");
		
		
/*		out.print("<html><body>");
		out.print("sendDirect 실행 이름:" + str);
		out.print("</body></html>");
*/				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
