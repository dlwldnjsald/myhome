<%-- <%@page import="himedia.myhome.vo.UserVo"%> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page session="true" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--   
<%
	//세션객체로부터 사용자 정보 겟
	UserVo authUser = (UserVo)session.getAttribute("authUser");

%> 
--%>   

   <!-- /header --> 
 <div id="header"> 
 
 	<h1>My Homepage</h1>
 	<p>authUser : ${sessionScope.authUser }</p>
 	
 	<%-- 
 	<%
 		if (authUser != null) { //로그인함
 	%>
 	--%>
 	
 	<c:choose>
 		<c:when test="${not empty authUser }">
	 	
	 		<ul>
				<li>${authUser.name }님 환영합니다</li>
				<%-- <li>${sessionScope.authUser.name }님 환영합니다</li> --%>
				<%--   <li><%= authUser.getName() %>님 환영합니다</li> --%>
				<li><a href="<%= request.getContextPath() %>/users?a=logout">로그아웃</a></li>
			 	<!--  로그인 한 사용자 -->
			 	<!--  로그인 한 사용자-> 웰컴메세지, 로그아웃 링크 추가하기 -->
			</ul>
			
		</c:when>
	 	<%--<% } else { %>--%>
	 	
	 	<c:otherwise>
			
			<ul>
				<!--  로그인 안 한 사용자 -->
				<!--  로그인 안 한 사용자 -> 가입링크, 로그인 폼 추가하기-->
				<li><a href="<%= request.getContextPath() %>/users?a=joinform">회원가입</a></li>
				<li><a href="<%= request.getContextPath() %>/users?a=loginform">로그인</a></li>
			
			</ul>	 	
	 
	 	<%--<% } %>--%>
 		</c:otherwise>
 		
 	</c:choose>
 
 	  </div> 
   
	 	<%-- h3>Params</h3>
	 	<ul>
	 		<li>param 1 :<%= request.getParameter("param1") %></li>
	 		<li>param 2 :<%= request.getParameter("param2") %></li>
	 	</ul>  --%>
	 
