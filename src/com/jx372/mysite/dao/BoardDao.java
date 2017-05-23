package com.jx372.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.jx372.mysite.vo.BoardVo;
import com.jx372.mysite.vo.GuestbookVo;

public class BoardDao {
	private Connection getConnection() throws SQLException {

		Connection conn = null;
		try {
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. Connection 하기
			String url = "jdbc:mysql://localhost:3306/webdb?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC Driver 를 찾을 수 없습니다.");
		}

		return conn;
	}
	public boolean delete(BoardVo bo){
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			String sql = "delete from board where no = ?;";

			pstmt = conn.prepareStatement( sql );
			pstmt.setLong(1,bo.getNo());
			
			int count = pstmt.executeUpdate();
			return count == 1;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {

				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public BoardVo get(BoardVo bo){
		BoardVo bvo = null;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;

		try{
			conn = getConnection();
			String sql = "select title, writer, hit, reg_date from board where no = ?;";

			pstmt = conn.prepareStatement( sql );
			pstmt.setLong( 1, bo.getNo() );

			rs = pstmt.executeQuery();

			if(rs.next()){
				bvo = new BoardVo();
				bvo.setTitle(rs.getString(1));
				bvo.setWriter(rs.getString(2));
				bvo.setHit(rs.getLong(3));
				bvo.setRegDate(rs.getString(4));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {

				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		return bvo;
	}
	public BoardVo getView(Long no){
		BoardVo bo = null;
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;

		try{
			conn = getConnection();
			String sql = "select title,content,no,user_no from board where no = ?;";

			pstmt = conn.prepareStatement( sql );
			pstmt.setLong( 1, no );

			rs = pstmt.executeQuery();

			if(rs.next()){
				bo = new BoardVo();
				bo.setTitle(rs.getString(1));
				bo.setContent(rs.getString(2));
				bo.setNo(rs.getLong(3));
				bo.setUserNo(rs.getLong(4));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {

				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


		return bo;
	}
	public boolean update(BoardVo bo){
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			String sql = "update board set title = ? , content = ? where no = ?";

			pstmt = conn.prepareStatement( sql );
			pstmt.setString(1,bo.getTitle());
			pstmt.setString(2,bo.getContent());
			pstmt.setLong(3,bo.getNo());
			
			int count = pstmt.executeUpdate();
			return count == 1;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {

				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean updateHit(Long no){
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;

		try{
			conn = getConnection();
			String sql = "update board set hit=hit+1 where no = ?;";

			pstmt = conn.prepareStatement( sql );
			pstmt.setLong(1,no);
			int count = pstmt.executeUpdate();
			return count == 1;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try {

				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	public List<BoardVo> getList() {
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "select board.no, title, name, hit, reg_date,user_no from board,user where board.user_no = user.no order by board.group_no desc;";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BoardVo bo = new BoardVo();

				bo.setNo(rs.getLong(1));
				bo.setTitle(rs.getString(2));
				bo.setWriter(rs.getString(3));
				bo.setHit(rs.getLong(4));
				bo.setRegDate(rs.getString(5));
				bo.setUserNo(rs.getLong(6));

				boardList.add(bo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return boardList;
	}

	public boolean insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into board values(null, ?, ?, 0, now(), (select ifnull(max(group_no),0)+ 1 from board a), 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUserNo());

			int count = pstmt.executeUpdate();

			return count == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
