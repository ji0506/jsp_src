package pre08.sec05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class SecondServlet
 */
@WebServlet(description = "MemberServlet", urlPatterns = { "/member" })
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("second servlet init");

	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		MemberDAO dao = new MemberDAO();
		
		String str = request.getParameter("command");

		
		if(str != null && str.equals("addMember"))
		{
			MemberVO vo = new MemberVO();
			vo.setId(request.getParameter("id"));
			vo.setPwd(request.getParameter("pwd"));
			vo.setEmail(request.getParameter("email"));
			vo.setName(request.getParameter("name"));
			dao.addMember(vo);
		}else if(str != null && str.equals("delMember")){
			dao.delMember(request.getParameter("id"));
		}
		
		List<MemberVO> memList = dao.listMembers();
		request.setAttribute("memList", memList);

		RequestDispatcher dis = request.getRequestDispatcher("/viewMember");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
