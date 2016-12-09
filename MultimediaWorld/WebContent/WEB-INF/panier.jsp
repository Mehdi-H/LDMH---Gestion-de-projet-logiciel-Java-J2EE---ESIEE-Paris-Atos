<%@ page pageEncoding="UTF-8" %>

<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<main id="panier">
	<table class="table-liste">
		<tr class="header-row">
			<th scope="col">Titres</th>
			<th scope="col">Artistes</th>
			<th scope="col">Extraits</th>
			<th scope="col">Prix unitaire</th>
			<th scope="col">Quantité</th>
			<th scope="col">Supprimer</th>
		</tr>
		
		<c:forEach items="${ produits }" var="pdt">
			<%@ include file="__produit_panier.jsp" %>
		</c:forEach>
	</table>
	
	<section class="validation">
		<button class="cta valider-panier">
			<a href="<%= request.getContextPath() %>/commande">Valider</a>
		</button>
	</section>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp" %>