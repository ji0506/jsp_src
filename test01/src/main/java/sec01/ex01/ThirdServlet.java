package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThirdServlet
 */
@WebServlet("/calc")
public class ThirdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static float USD_RATE = 1124.70F;
	private static float JPY_RATE = 10.113F;
	private static float CNY_RATE = 163.30F;
	private static float GBP_RATE = 1444.35F;
	private static float EUR_RATE = 1295.97F;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThirdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("ThirdServlet init 메소드 호출");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("ThirdServlet Served at: ").append(request.getContextPath());
		System.out.println("test");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		String str = request.getParameter("command");
		String str2 = request.getParameter("won");		
		String str3 = request.getParameter("operator");
		PrintWriter out = response.getWriter();
		
		if(str != null && str.equals("cal"))
		{
			String re =cal(Float.parseFloat(str2),str3);
			out.print("<html><font size=10>변환 결과</font><br>");
			out.print("<html><font size=10>"+ re +"</font><br>");
			out.print("<a href='/calc'>환율 계산기</a>");
			return;
		}

		out.print("<html><title>환율 계산기</title>");
		out.print("<font size=5>환율 계산기</font><br>");
		out.print("<form name='frmCal' method='get' action='/calc'>");
		out.print("원화: <input type='text' name='won' size=10 />");
		out.print("<select name='operator'>");
		out.print("<option value='dollar'>달러</option>");
		out.print("<option value='en'>엔화</option>");
		out.print("<option value='wian'>위안</option>");
		out.print("<option value='pound'>파운드</option>");
		out.print("<option value='euro'>유로</option>");
		out.print("</select>");
		out.print("<input type='hidden' name='command' value='cal' />");
		out.print("<input type='submit' value='변환'");
		out.print("</html>");
	}

	public String cal(float str2, String str3)
	{
		String re = null;
		if(str3.equals("dollar"))
			re = String.format("%.6f", str2/USD_RATE);
		else if(str3.equals("en"))
			re = String.format("%.6f", str2/JPY_RATE);
		else if(str3.equals("wian"))
			re = String.format("%.6f", str2/CNY_RATE);
		else if(str3.equals("pound"))
			re = String.format("%.6f", str2/EUR_RATE);
		else if(str3.equals("euro"))
			re = String.format("%.6f", str2/GBP_RATE);
		
		return re;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
