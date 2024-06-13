package himedia.myhome.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import himedia.myhome.vo.UserVo;

public class UsersDaoOracleImpl implements UsersDao {
	
	private String dbuser;
	private String dbpass;
	
	
	public UsersDaoOracleImpl(String dbuser, String dbpass) {
		
		this.dbuser = dbuser;
		this.dbpass = dbpass;
	}
	
	// 데이터베이스 접속 정보 -> 컨텍스트 파라미터로부터 받아옴
	// Connection 공통 메서드
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	
	
	@Override
	public List<UserVo> getList() {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<UserVo> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM users ORDER BY created_at DESC";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				Date createdAt = rs.getDate("created_at");
				
				UserVo vo = new UserVo(no, name, password, email, gender, createdAt);
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs!= null) rs.close();
				if (stmt!= null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	
	@Override
	public boolean insert(UserVo uservo) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		
		try {
			conn = getConnection();
			String sql = "INSERT INTO users (no, name, password, email, gender, created_at) "
						+ "values (seq_users_pk.nexval, ?, ?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, uservo.getName());
			pstmt.setString(2, uservo.getPassword());
			pstmt.setString(3, uservo.getEmail());
			pstmt.setString(4, uservo.getGender());
			
			insertedCount = pstmt.executeUpdate();
		 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1 == insertedCount;
	}

	@Override
	public boolean update(UserVo uservo) {
		Connection conn = null;
	    PreparedStatement pstmt = null;
	    int updatedCount = 0;

	    try {
	        conn = getConnection();
	        String sql = "UPDATE users SET name=?, password=?, email=?, gender=? WHERE no=?";
	        pstmt = conn.prepareStatement(sql);

	        // PreparedStatement에 각 필드의 값을 설정
	        pstmt.setString(1, uservo.getName());
	        pstmt.setString(2, uservo.getPassword());
	        pstmt.setString(3, uservo.getEmail());
	        pstmt.setString(4, uservo.getGender());
	        pstmt.setLong(5, uservo.getNo()); // 해당하는 사용자의 번호를 설정
	        
	        // 업데이트 실행 및 업데이트된 행 수 반환
	        updatedCount = pstmt.executeUpdate(); 
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pstmt != null) pstmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    // 업데이트된 행 수가 1이면 
	    // 성공적으로 업데이트된 것으로 간주함
	    return updatedCount == 1;
	}

	@Override
	public boolean delete(Long no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;

		try {
			conn = getConnection();
			String sql = "DELETE FROM users WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);

			deletedCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1 == deletedCount;
	}


	@Override
	public UserVo getUserByIdAndPassword(String email, String password) {
		
		 	Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    UserVo user = null;

		    try {
		        conn = getConnection();
		        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
		 	    pstmt = conn.prepareStatement(sql);
		 	    
		 	   // PreparedStatement에 이메일과 비밀번호를 설정
		        pstmt.setString(1, email);
		        pstmt.setString(2, password);
		        
		        //결과를 ResultSet으로 가져옴
		        rs = pstmt.executeQuery();
		        
		        //get해오기
		        if (rs.next()) {
		            Long no = rs.getLong("no");
		            String name = rs.getString("name");
		            String gender = rs.getString("gender");
		            Date createdAt = rs.getDate("created_at");

		            user = new UserVo(no, name, password, email, gender, createdAt);
		        }
		        		
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) rs.close();
		            if (pstmt != null) pstmt.close();
		            if (conn != null) conn.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    return user;
		}
	
	
	
}
