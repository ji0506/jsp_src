package pro11.sec02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	private PreparedStatement stmt;
	private Connection conn;
	private DataSource dataFac;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envctx = (Context) ctx.lookup("java:comp/env");
			dataFac = (DataSource) envctx.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<MemberVO> listMembers() {

		List<MemberVO> list = new ArrayList<>();

		try {
			conn = dataFac.getConnection();
			String sql = "select * from t_member order by joinData asc";
			System.out.println(sql);

			stmt = conn.prepareStatement(sql);
			ResultSet re = stmt.executeQuery();
			while (re.next()) {
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
	public List<MemberVO> listMembers(MemberVO vo) {

		List<MemberVO> list = new ArrayList<>();

		try {
			conn = dataFac.getConnection();
			String sql = "select * from t_member where name=?";
			System.out.println(sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getName());
			ResultSet re = stmt.executeQuery();
			while (re.next()) {
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
	

	public void addMember(MemberVO vo) {
		try {
			conn = dataFac.getConnection();

			String sql = "insert into t_member(id,pw,name,email) values(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPwd());
			stmt.setString(3, vo.getName());
			stmt.setString(4, vo.getEmail());

			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delMember(String id) {
		try {
			conn = dataFac.getConnection();

			String sql = "delete from t_member where id=?";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public boolean isExited(MemberVO vo)
	{
		
		
		boolean result = false;
		try {
			conn = dataFac.getConnection();
			String sql = "select　decode(count(*),1,'true','false') as result from t_member where id=? and pw=?";
			System.out.println(sql);

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getId());
			stmt.setString(2, vo.getPwd());
			ResultSet re = stmt.executeQuery();
			while (re.next()) {
				result = Boolean.parseBoolean(re.getString("result"));
			}

			re.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return result;
		
	}

}
