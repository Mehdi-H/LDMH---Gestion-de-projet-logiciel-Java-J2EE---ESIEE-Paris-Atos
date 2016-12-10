<%@ page pageEncoding="UTF-8" %>

<tr class="content-row">
	<%-- Nom produit --%>
	<td>${ pdt.nom_produit }</td>
	
	<%-- Nom(s) artiste(s) --%>
	<td>
		<c:forEach items="${ pdt.artistes }" var="artiste" varStatus="status">
			${ artiste.nom }<c:if test="${ !status.last }">, </c:if>
		</c:forEach>
	</td>
	
	<%-- Prix --%>
	<td>${ pdt.prix } €</td>
	
	<%-- Quantité --%>
	<td>${ pdt.quantity }</td>
</tr>