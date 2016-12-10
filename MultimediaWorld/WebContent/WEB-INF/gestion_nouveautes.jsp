<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<main id="liste-rubriques-wrap">
	<table class="table-liste">
		<tr class="header-row">
			<th scope="col">ID</th>
			<th scope="col">Titre</th>
			<th scope="col">Artistes</th>
			<th scope="col">Enlever</th>
		</tr>
		
		<c:forEach items="${ produits }" var="pdt">
			<%@ include file="__nouveaute_liste.jsp" %>
		</c:forEach>
	</table>
	
	<h3>Ajouter un produit dans la page des nouveautés</h3>
	<table class="table-liste">
		<tr class="header-row">
			<th scope="col">ID du produit</th>
			<th scope="col">Ajouter</th>
		</tr>
		
		<tr>
			<form action="" method="POST">
				<td>
					<select name="id_produit" required>
						<c:forEach items="${ not_new_produits }" var="pdt">
							<option value="${ pdt.id }">[${ pdt.id }] ${ pdt.nom_produit }</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<input type="submit" name="submit" value="Ajouter" />
				</td>
			</form>
		</tr>
	</table>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp"%>