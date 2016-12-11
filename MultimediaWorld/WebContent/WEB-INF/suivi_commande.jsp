<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<main id="suivi-commande">

	<form action="" method="GET">
		<select name="id" required>
			<c:forEach items="${ commandes }" var="comm">
				<option value="${ comm.id}">[${ comm.id}] le ${ comm.date_commande } par ${ comm.username }</option>
			</c:forEach>
		</select>
		
		<input type="submit" value="Gérer le suivi" />
	</form>

	<c:if test="${ commande != null }">
		<table class="table-liste">
			<tr class="header-row">
				<th scope="col">Nom d'utilisateur</th>
				<th scope="col">Numéro de commande</th>
				<th scope="col">Passée le</th>
				<th scope="col">Total</th>
				<th scope="col">Statut</th>
				<th scope="col">Valider</th>
			</tr>
			
			<form action="" method="POST">
				<tr>
					<td>${ commande.username }</td>
					<td>${ commande.id}</td>
					<td>${ commande.date_commande }</td>
					<td>${ total }</td>
					<td>
						<input id="etat-commande" type="range" name="etat_index" value="${ etatNumber }" min="1" max="3" step="1" /><br />
						<span id="etat-text">${ commande.etat }</span>
					</td>
					<td>
						<input type="submit" value="Valider" />
						<input type="hidden" name="id_commande" value="${ commande.id }" />
					</td>
				</tr>
			</form>
		</table>
	</c:if>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp"%>