<%@ page pageEncoding="UTF-8" %>

<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<main id="liste-produits-wrap">
	<ul id="liste-produits">
		<li class="produit produit-head">
			<p>Titres</p>
			<p>Artistes</p>
			<p>Extraits</p>
			<p>Prix</p>
			<p>Achat</p>
		</li>
		
		<c:forEach items="${ produits }" var="pdt">
			<%@ include file="__produit_liste.jsp" %>
		</c:forEach>
	</ul>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp" %>