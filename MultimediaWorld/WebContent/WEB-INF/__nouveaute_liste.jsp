<%@ page pageEncoding="UTF-8" %>

<tr class="content-row">
	<form action="" method="POST">
	
		<%-- ID --%>
		<td>${ pdt.id }</td>
		
		<%-- Titre --%>
		<td>${ pdt.nom_produit }</td>
		
		<%-- Artistes --%>
		<td>
			<c:forEach items="${ pdt.artistes }" var="artiste" varStatus="status">
				${ artiste.nom }<c:if test="${ !status.last }">, </c:if>
			</c:forEach>
		</td>
		
		<%-- Enlever --%>
		<td>
			<input type="submit" name="submit" value="Enlever" />
		</td>
	
		<input type="hidden" name="id_produit" value="${ pdt.id }" />
	</form>
</tr>