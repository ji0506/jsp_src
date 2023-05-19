package pre08.sec06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CotextParamServlet
 */
@WebServlet("/context")
public class CotextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CotextParamServlet() {
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
		
		ServletContext context = getServletContext();
		
		String menuMem = context.getInitParameter("menu_mem");
		String menuOrd = context.getInitParameter("menu_ord");
		String menuGood = context.getInitParameter("menu_good");
		
		out.print("<html><body>");
		out.print("<table border=1 cellspcing=0><tr>메뉴이름</tr>");
		out.print("<tr><td>" + menuMem  +"</td></tr>");
		out.print("<tr><td>" + menuOrd  +"</td></tr>");
		out.print("<tr><td>" + menuGood  +"</td></tr>");
		out.print("</tr></table></body></html>");


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
