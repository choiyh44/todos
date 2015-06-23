<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
파라미터 한글설정이 필요해요..
<p/>

todos.size(): ${todos.size()}
<p/>

<c:forEach var="todo" items="${todos}" varStatus="x">
  <p> id: ${todo.id}, name: ${todo.name}, completed: ${todo.completed} </p>
</c:forEach>
<p/>

신규등록id: ${id}
<p/>

</body>
</html>
