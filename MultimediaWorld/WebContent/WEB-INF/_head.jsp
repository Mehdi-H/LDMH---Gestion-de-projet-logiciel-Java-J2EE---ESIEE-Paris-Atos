<%@ include file="_taglibs.jsp" %>

<!DOCTYPE html>
<html>
	<head>
		<%-- META --%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width">
		
		<%-- TITLE --%>
		<title>
			<c:out value="${ _title }" />
		</title>
		
		<%-- ICON --%>
		<link rel="icon" type="image/ico" href="<%= request.getContextPath() %>/assets/img/logo-multimedia-world.ico">
		
		<%-- CSS --%>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/main.css">
		<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/font-awesome-4.7.0/css/font-awesome.min.css">
		
	</head>
	<body>
		<div class="page-wrap">
		
			<%-- BODY HEADER --%>
			<%@ include file="_header.jsp" %>
			
			<div class="page-sections">
        		<main>
        			<header>
						<h2 id="rubrique-title"><span>
							<c:out value="${ _title }" />
						</span></h2>
					</header>

					<%-- CONTENU DE LA PAGE --%>