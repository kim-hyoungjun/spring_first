<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; carset=UTF-8" />
	<title>Servlet Filter</title>
</head>
<body>
<h1>
	Servlet Filter!  
</h1>

<P>  <%= request.getParameter("param") %> </P>
</body>
</html>
