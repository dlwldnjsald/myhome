package himedia.myhome.controller;

import java.io.IOException;

import himedia.myhome.dao.UsersDao;
import himedia.myhome.dao.UsersDaoOracleImpl;
import himedia.myhome.vo.UserVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/users")

//BaseServlet 상속받기
public class UserServlet extends BaseServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// a = joinform -> 가입 폼 페이지로 forward
		// a = joinsuccess -> 가입 성공 페이지로 forward
		
		// a=: logout -> 세션지우고 /redirect할것 
		
		String actionName = req.getParameter("a");
		
		if("joinform".equals(actionName)) {
			//가입 폼으로 포워드
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/users/joinform.jsp");
			rd.forward(req, resp);
		
		} else if ("joinsuccess".equals(actionName)) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/users/joinsuccess.jsp");
			rd.forward(req, resp);
			
		//loginform 부분도 설정하기
		} else if ("loginform".equals(actionName)) {
			
			String result = req.getParameter("result");
			if("fail".equals(result)) {
				//에러 메세지를 요청 어트리뷰트에 추가
				req.setAttribute("errorMsg", "로그인에 실패했습니다");
			}
			
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/users/loginform.jsp");
			rd.forward(req, resp);
		
		// a=: logout -> 세션지우고 /redirect할것 	
		// 로그아웃	
		} else if("logout".equals(actionName)) {
			//로그아웃 세션 무효화시키기
			HttpSession session = req.getSession();
			session.removeAttribute("authUser"); //개별 속성 삭제
			session.invalidate(); //세션 무효화
			resp.sendRedirect(req.getContextPath());
			
			
		} else { //actionparameter가 없을경우
			//홈페이지로 리다이렉트 ->
			resp.sendRedirect(req.getContextPath()); // "/" 붙이지 않는 이유??
		}
		//super.doGet(req, resp); 지우주기 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// a : insert -> 회원가입
		// a : login -> 로그인을 수행해주기 
		String actionName = req.getParameter("a");
		
		if("join".equals(actionName)) {
			// 가입 처리
			//form 데이터 수신
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String gender = req.getParameter("gender");
			
			UserVo vo = new UserVo(name, password, email, gender);
			
			UsersDao dao = new UsersDaoOracleImpl(dbuser,dbpass);
			
			boolean success = dao.insert(vo);
			
			if(success) {
				resp.sendRedirect(req.getContextPath() + "/users?a=joinsuccess");
			} else {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "가입 실패");
			}
		
		//로그인 수행 위한 a: login -> 	
		} else if ("login".equals(actionName))	 {
			
			//파라미터 얻어오기
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			//사용자 정보 질의 
			UsersDao dao = new UsersDaoOracleImpl(dbuser, dbpass); //dao 불러오기
			UserVo vo = dao.getUserByIdAndPassword(email, password); //vo 받아오기
			
			if (vo != null) { // 사용자가 있는 경우 -> 성공
				//사용자 정보를 세션에 기록
					HttpSession session = req.getSession(); //세션얻어오기
					//세션에 기록
					session.setAttribute("authUser", vo);
					
					//홈화면으로 리다이렉트
					resp.sendRedirect(req.getContextPath());
			} else { //사용자가 없는 경우 -> 실패
				// 다시 로그인 창으로 리다이렉트 (result=fail)
				resp.sendRedirect(req.getContextPath() + "/users?a=loginform&result=fail");
			}
			
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND); //404 not found
		}
		
	}

  
	
		
	
}
