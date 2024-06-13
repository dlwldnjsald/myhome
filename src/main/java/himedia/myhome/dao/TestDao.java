package himedia.myhome.dao;

import java.util.Iterator;
import java.util.List;

import himedia.myhome.vo.UserVo;

public class TestDao {
	public static void main(String[] args) {
		
		//	목록 출력
		list();
		
		//	회원 정보 새로 입력
		insert("홍길동", "1234", "hong@hwalbin.org", "M");
		insert("이영희", "4321", "younghee@lee.name", "F");
		
		//	변경된 목록 출력
		list();
		
		//	특정 회원 정보 출력
		testGetUserByIdAndPassword("hong@hwalbin.org", "1234");
		testGetUserByIdAndPassword("gildong@dooly.net", "whocares?");
	}
	
	//특정 이메일과 비밀번호를 가진 사용자를 데이터베이스에서 조회
	private static void testGetUserByIdAndPassword(String email, String password) {
		
		//UsersDao 인터페이스를 구현한 UsersDaoOracleImpl 클래스의 객체를 생성
		UsersDao dao = new UsersDaoOracleImpl("himedia", "himedia");
		
		//메서드를 호출하여 해당 이메일과 비밀번호를 가진 사용자의 정보를 조회
		UserVo vo = dao.getUserByIdAndPassword(email, password);
		
		//null이면 "NOT FOUND", 아니면 "FOUND"를 출력
		System.out.println("USER " + (vo == null ? "NOT FOUND": "FOUND"));
		System.out.println("USER INFO:" + vo);
		
	}

	private static void insert(String name, String password, String email, String gender) {
		UsersDao dao = new UsersDaoOracleImpl("himedia", "himedia");
		
		UserVo vo = new UserVo(name, password, email, gender);
		
		boolean success = dao.insert(vo);
		
		System.out.println(name + " INSERT " + (success ? "SUCCESS": "FAILED"));
	}

	
	public static void list() {
		UsersDao dao = new UsersDaoOracleImpl("himedia", "himedia");
		
		List<UserVo> list = dao.getList();
		Iterator<UserVo> it = list.iterator();
		
		System.out.println("====================");
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("====================");
	}
	
	
}