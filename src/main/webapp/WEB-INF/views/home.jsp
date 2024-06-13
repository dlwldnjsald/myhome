<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Homepage</title>
<!-- TODO: 현재 페이지에 적절한 CSS를 임포트하십시오. -->
<link type="text/css" 
	rel="stylesheet" 
	href="<%= request.getContextPath() %>/css/home.css"/>
</head>
<body>
  <div id="container">
  
    <%--  div id="header"> <h1>My Homepage</h1> </div> 
    <!-- /header/// header.jsp로 이동함--%>
    
    <jsp:include page="/WEB-INF/views/includes/header.jsp">
    	<jsp:param name="param1" value="value1" />
    	<jsp:param name="param2" value="value2" />
    </jsp:include>
    
    <%-- div id="navigation">
      <ul>
        <li><a href="<%= request.getContextPath() %>/">My Home</a></li>
        <li><a href="<%= request.getContextPath() %>/guestbook">방명록</a></li>
        <li><a href="<%= request.getContextPath() %>/board">게시판</a></li>
      </ul>
	</div--%>
	
	<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>
	
	<div id="wrapper">
      <div id="content">
			<!-- Content 영역 -->
      </div>
	</div>
	
	<%--div id="footer">
      <p>Copyright(c) 2019 남승균 All rights reserved. </p>
	</div--%>
	
	<%-- 아래의 두개 파일중에서 선택해서 사용해도 된다 액션태그를 사용할지 또는 include지시어 사용하면됨
	<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include> --%>
	<%@ include file="/WEB-INF/views/includes/footer.jsp" %> 
	
	
  </div>
</body>
</html>