<%@ page pageEncoding="UTF-8" %>

<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<main id="liste-produits-wrap">
	<table class="table-liste">
		<tr class="header-row">
			<th scope="col">Titres</th>
			<th scope="col">Artistes</th>
			<th scope="col">Extraits</th>
			<th scope="col">Prix</th>
			<th scope="col">Panier</th>
		</tr>
		
		<c:forEach items="${ produits }" var="pdt">
			<%@ include file="__produit_liste.jsp" %>
		</c:forEach>
	</table>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp" %>