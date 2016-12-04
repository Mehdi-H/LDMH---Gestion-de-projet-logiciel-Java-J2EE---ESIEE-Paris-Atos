<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Présentation de Multimedia World</title>
		
		<link rel="stylesheet" href="assets/css/main.css">
	</head>
	
	<body>
		<%@ include file="header.jsp" %>
		
		<h1>Coucou</h1>
		<p>Hey !</p>
		
		<ul>
			<c:forEach var="user" items="${users}">
				<li>${user.prenom} ${user.nom}</li>
			</c:forEach>
		</ul>
	</body>
</html>