<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<main id="liste-rubriques-wrap">
	<form action="" method="GET">
		<select name="username_input" required>
			<c:forEach items="${ clients }" var="c">
				<option value="${ c.username }" ${ c.username == client.username ? "selected" : "" }>
					[${ c.username }] ${ c.prenom } ${ c.nom }
				</option>
			</c:forEach>
		</select>
		
		<input type="submit" value="Consulter la fiche" />
	</form>
	
	<c:if test="${ client != null }">
		<section id="commande-recap">
			<article id="client-info">
				<div>
					<p>Nom d'utilisateur du client : <strong>${ client.username }</strong></p>
					<p>Nom : <strong>${ client.nom }</strong></p>
					<p>Prénom : <strong>${ client.prenom }</strong></p>
				</div>
				<div>
					<p>Adresse : <strong>${ client.adresse }</strong></p>
					<p>Rôle : <strong>${ client.role }</strong></p>
				</div>
			</article>
		</section>
		
		
		<h3>Commandes de ce client</h3>
		<ul id="all-commandes">
			<c:forEach items="${ commandes }" var="c">
				<li class="num-commande">
					<a href="<%= request.getContextPath() %>/admin/commandes?id=${ c.id }">
						[${ c.id }] le ${ c.date_commande }
					</a>
				</li>
			</c:forEach>
		</ul>
	</c:if>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp"%>