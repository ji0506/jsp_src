package pre08.sec08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pre08.sec05.MemberDAO;
import pre08.sec05.MemberVO;

/**
 * Servlet implementation class SessionTest2
 */
@WebServlet("/login2")
public class SessionTest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionTest2() {
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
		String pw = request.getParameter("user_pwd");	
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pw);

		MemberDAO dao = new MemberDAO();
		boolean re = dao.isExited(vo);
		
		if(re)
		{
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("login_id", id);
			session.setAttribute("login_pwd", pw);
			out.print("<html><body>");
			out.print("안녕하세요 " + id  + "님<br>");
			out.println("<a href='show'>회원정보 보기 </a>");
			out.print("</body></html>");			
		}else{
			out.print("<html><body><center>회원 아이디가 틀립니다.");
			out.print("<a href='login2.html'>다시 로그인하기 </a>");
			out.print("</body></html>");			

		}
		/*
		if(session.isNew()) {
			if(id != null){
				session.setAttribute("user_id", id);
				String url = response.encodeURL("login2");
				out.println("<a href='" + url +"'>로그인 상태 확인 </a>");
			}else {
				out.println("<a href='login2.html'>다시 로그인하세요 </a>");
				session.invalidate();
			} 
		} else {
			id = (String) session.getAttribute("user_id");
			if(id != null && id.length() !=0)
				out.print("안녕하세요" + id + "님");
			else {
				out.print("<a href='login2.html'>다시 로그인 하세요</a>");
				session.invalidate();
			}
			
		}
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
