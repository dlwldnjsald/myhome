package himedia.myhome.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//이 클래스는 추상 클래스인 BaseDao입니다. 추상 클래스는 직접적으로 객체를 생성할 수 없으며, 
//그 대신에 다른 클래스들이 이 클래스를 확장(상속)하여 사용할 수 있습니다.
//데이터베이스와의 연결을 처리하는 기능을 제공하며, 
//이 클래스를 상속받는 다른 DAO 클래스들은 이를 사용하여 데이터베이스와 상호 작용할 수 있습니다.
public abstract class BaseDao {
	
	// 둘 다 null로 초기화
	private String dbUser = null;
	private String dbPass = null;
	
	//BaseDao 클래스의 생성자-두 개의 매개변수를 받아서 dbUser와 dbPass 변수를 초기화
	public BaseDao(String dbUser, String dbPass) {
		this.dbUser = dbUser;
		this.dbPass = dbPass;
	}
	
	//getConnection 메서드를 선언 :  데이터베이스와의 연결을 제공
	protected Connection getConnection() throws SQLException {
		//conn이라는 Connection 객체를 선언하고 초기화
		Connection conn = null;
		
		try { //데이터베이스에 연결하기 위한 정보
			Class.forName("oracle.jdbc.driver.OracleDriver"); // JDBC 드라이버를 로드
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe"; // 데이터베이스의 URL을 문자열로 저장
			conn = DriverManager.getConnection(dburl, dbUser, dbPass);
		
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver를 로드하지 못했습니다.");
			e.printStackTrace();
		}
		
		return conn;
	}
}