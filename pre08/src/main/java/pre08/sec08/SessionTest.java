package pre08.sec08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTest
 */
@WebServlet("/sess")
public class SessionTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionTest() {
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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.print("세션 아이디 : " +session.getId() + "<br>");
		out.print("최초 세션 생성 시각 : " + new Date(session.getCreationTime()) + "<br>");
		out.print("최근 세션 접근 시각 : " + new Date(session.getLastAccessedTime()) + "<br>");
		out.print("세션 유효 시간 : " + new Date(session.getMaxInactiveInterval()) + "<br>");
		session.setMaxInactiveInterval(500);
		out.print("세션 유효 시간 : " + new Date(session.getMaxInactiveInterval()) + "<br>");

		if(session.isNew())
		{
			out.print("세 세션이 만들어졌습니다.");
		}
		
		session.invalidate();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
