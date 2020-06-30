package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

public class BoardDao {
	// 필드
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// ------------리스트------------
	public List<BoardVo> BoardList() {
		List<BoardVo> BoardList = new ArrayList<BoardVo>();

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " SELECT  b.no no, ";
			query += "         b.title title, ";
			query += "         b.content content, ";
			query += "         b.hit hit, ";
			query += "         to_char(b.reg_date,'YY-MM-DD HH:MI') reg_date , ";
			query += "         b.user_no user_no, ";
			query += "         s.name name ";
			query += " FROM board b,";
			query += " 		users s";
			query += " where b.user_no = s.no";
			query += " order by no desc";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				int user_no = rs.getInt("user_no");
				String name = rs.getString("name");
				BoardVo boardVo = new BoardVo(no, title, content, hit, reg_date, user_no, name);
				BoardList.add(boardVo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return BoardList;
	}

	// ------------읽기------------
	public BoardVo BoardRead(int num) {

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			String query = "";
			query += " SELECT  b.no no, ";
			query += "         b.title title, ";
			query += "         b.content content, ";
			query += "         b.hit hit, ";
			query += "         b.reg_date reg_date , ";
			query += "         b.user_no user_no, ";
			query += "         s.name name ";
			query += " FROM board b,";
			query += " 		users s";
			query += " where b.user_no = s.no";
			query += " and 	 b.no = ?";
			query += " order by no desc";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String reg_date = rs.getString("reg_date");
				int user_no = rs.getInt("user_no");
				String name = rs.getString("name");
				BoardVo boardVo = new BoardVo(no, title, content, hit, reg_date, user_no, name);

				return boardVo;
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return null;
	}
	// ------------조회수+------------
	public int BoardHit(int no) {
		int count = 0;
		getConnection();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " update board";
			query += " set hit= hit+1 ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, no);

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			//System.out.println(count + "건 처리되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}
	// ------------삭제하기------------

	public void BoardDelete(int no) {
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " DELETE FROM board ";
			query += " WHERE no = ? ";
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setInt(1, no);

			pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
	}

	// ------------수정하기------------
	public int BoardUpdate(BoardVo vo) {
		int count = 0;
		getConnection();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " update board";
			query += " set title= ? ,";
			query += "     content= ?";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			//System.out.println(count + "건 처리되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	// ------------추가하기------------
	public int BoardInsert(BoardVo vo) {
		int count = 0;
		getConnection();
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " INSERT INTO board VALUES (seq_board_no.NEXTVAL, ? , ? , 0 , sysdate, ?) ";
			
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());

			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println(count + "건 처리되었습니다.");
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}
}
