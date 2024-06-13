<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- /header --> 
 <div id="header"> 
 	<h1>My Homepage</h1>
 	
	 	<h3>Params</h3>
	 	<ul>
	 		<li>param 1 :<%= request.getParameter("param1") %></li>
	 		<li>param 2 :<%= request.getParameter("param2") %></li>
	 	</ul>
	 
  </div> 
   