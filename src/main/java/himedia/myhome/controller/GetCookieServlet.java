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

@WebServlet("/cookie/get")	
public class GetCookieServlet extends HttpServlet {

	//To-do :
	//doGet 메서드 호출 -> 쿠키확인
	//?a=delete -> 쿠키지우기 
	
	
	//doGet
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		
		//resp로부터 getWriter 받아오기
		PrintWriter out = resp.getWriter();
		
		//?a=delete -> 쿠키지우기
		String actionName = req.getParameter("a");
		
		if("delete".equals(actionName)) {
			//delete cookie
			// MaxAge -> 0 혹은 음수값으로 변경, 무효화
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("testCookie")) {
					//무효화
					cookie.setMaxAge(0); // 0이면 지속시간 -> 0
					resp.addCookie(cookie); //resp에 추가
 				}
			}
			out.println("<p>쿠키를 삭제했습니다.</p>");
			
		} else {
			//요청 정보에서 쿠키목록 확인 츨력
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
