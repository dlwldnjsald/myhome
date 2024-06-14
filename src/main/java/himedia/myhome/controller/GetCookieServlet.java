package himedia.myhome.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//서블릿이 "/cookie/get" URL에 매핑되도록 지정
//클라이언트가 해당 URL로 요청을 보내면 이 서블릿이 처리하게됨
@WebServlet("/cookie/get")	

//HttpServlet을 상속받아 쿠키를 다루는 기능 구현하는 연습
public class GetCookieServlet extends HttpServlet {

	//To-do :
	//doGet 메서드 호출 -> 쿠키확인
	//?a=delete -> 쿠키지우기 
	
	
	//doGet-HTTP GET 요청을 처리
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//응답의 캐릭터 인코딩과 컨텐츠 타입을 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		//응답을 위한 PrintWriter 객체 얻어오기
		PrintWriter out = resp.getWriter();
		
		//?a=delete -> 쿠키를 삭제하는 로직을 수행
		String actionName = req.getParameter("a");
		
		if("delete".equals(actionName)) {
			//delete cookie
			// MaxAge -> 0 혹은 음수값으로 변경, 무효화
			//req.getCookies()를 통해 모든 쿠키를 가져온 후, 각 쿠키의 이름, 값, 유효 시간을 출력
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("testCookie")) {
					//무효화
					cookie.setMaxAge(0); // 0이면 지속시간 -> 0 /쿠키의 유효 시간을 0으로 설정하여 만료
					resp.addCookie(cookie); //resp에 쿠키 추가
 				}
			}
			out.println("<p>쿠키를 삭제했습니다.</p>");
			
		} else {
			//그렇지 않은 경우 요청 정보에서 쿠키 목록을 출력하는 로직을 수행
			out.println("<h1>쿠키 목록</h>");
			out.println("<ul>");
			
				Cookie[] cookies = req.getCookies();
				for (Cookie cookie: cookies) {
					out.printf("<li>%s: %s - %d</li>", 
							cookie.getName(),
							URLDecoder.decode(cookie.getValue(), "UTF-8"), 
							cookie.getMaxAge());
				}
			
			out.println("</ul>");
		}
		
	}

	

	
	
}
