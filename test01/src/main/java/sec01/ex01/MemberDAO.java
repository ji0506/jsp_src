package sec01.ex01;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

	private static final String driver="oracle.jdbc.OracleDriver";
	private static final String id = "hr";
	private static final String pwd = "12345";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	private Statement stmt;
	private Connection conn;
	
	public List<MemberVO> listMembers()
	{
		
		List<MemberVO> list = new ArrayList<>();
		
		try {
			connDB();
			
			String sql="select * from t_member";
			System.out.println(sql);
			ResultSet re = stmt.executeQuery(sql);
			while(re.next())
			{
				MemberVO vo = new MemberVO();
				vo.setId(re.getString("id")); 
				vo.setPwd(re.getString("pw")); 
				vo.setName(re.getString("name")); 
				vo.setEmail(re.getString("email")); 
				vo.setJoinData(re.getDate("joinData")); 
				list.add(vo);
			}
			re.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void connDB()
	{
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,id,pwd);
			stmt =conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
