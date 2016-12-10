<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<main id="liste-rubriques-wrap">
	<table class="table-liste">
		<tr class="header-row">
			<th scope="col">Rubrique</th>
			<th scope="col">Position</th>
			<th scope="col">Renommer</th>
			<th scope="col">Valider</th>
			<th scope="col">Supprimer</th>
		</tr>
		
		<c:forEach items="${ rubriques }" var="rub">
			<%@ include file="__rubrique_liste.jsp" %>
		</c:forEach>
	</table>
	
	<h3>Ajouter une rubrique</h3>
	<table class="table-liste">
		<tr class="header-row">
			<th scope="col">Nom rubrique</th>
			<th scope="col">Ajouter</th>
		</tr>
		
		<tr>
			<form action="" method="POST">
				<td><input type="text" name="label_rubrique" placeholder="Nom rubrique" required /></td>
				<td><input type="submit" name="submit" value="Ajouter" /></td>
			</form>
		</tr>
	</table>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp"%>