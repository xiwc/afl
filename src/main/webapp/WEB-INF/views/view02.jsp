<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试页面</title>
</head>
<body>
<div>
<div><h3><c:out value="title"></c:out></h3></div>
<div>
<div>code: <c:out value="${code}"></c:out></div>
<div>state: <c:out value="${state}"></c:out></div>
</div>
<div></div>
</div>
</body>
</html>