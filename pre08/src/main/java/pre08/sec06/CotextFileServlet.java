package pre08.sec06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CotextParamServlet
 */
@WebServlet("/file")
public class CotextFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CotextFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		ServletContext context = getServletContext();
		InputStream is = context.getResourceAsStream("/WEB-INF/init.txt");
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));

		String menu = null;
		String menuMem = null;
		String menuOrd = null;
		String menuGood = null;

		while ((menu = buf.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(menu, ",");
			menuMem = token.nextToken();
			menuOrd = token.nextToken();
			menuGood = token.nextToken();

		}

		out.print("<html><body>");
		out.print( menuMem + "<br>");
		out.print(menuOrd + "<br>");
		out.print(menuGood + "<br>");
		out.print("</body></html>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
