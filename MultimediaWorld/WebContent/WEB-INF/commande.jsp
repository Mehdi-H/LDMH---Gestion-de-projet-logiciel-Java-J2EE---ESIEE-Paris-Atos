<%@ page pageEncoding="UTF-8" %>

<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<main id="commande">
	<form action="" method="POST">
		<section id="commande-recap">
			<article id="client-info">
				<p>Nom d'utilisateur : ${ user.username }</p>
				<p>Nom : ${ user.nom }</p>
				<p>Prénom : ${ user.prenom }</p>
				<p>Adresse : ${ user.adresse }</p>
			</article>
			<article id="client-cb">
				<label>Votre numéro de CB</label>
				<input required="true" type="text" pattern="[0-9]{16}" placeholder="Numéro de CB (16 chiffres)"/>
			</article>
		</section>
		
		<table class="table-liste">
			<tr class="header-row">
				<th scope="col">Titres</th>
				<th scope="col">Artistes</th>
				<th scope="col">Prix unitaire</th>
				<th scope="col">Quantité</th>
			</tr>
			
			<c:forEach items="${ produits }" var="pdt">
				<%@ include file="__produit_commande.jsp" %>
			</c:forEach>
		</table>
		
		<p class="commande-frais-port">
			Frais de port : ${ commande.frais_port } €
		</p>
		
		<h3>Total</h3>
		<ul>
			<li class="recapitulatif"><img class="logo" src="assets/img/logo-multimedia-world-light.png"/>
				<p>
					${ nb_produits }
					<c:if test="${ nb_produits > 1 }">
						titres musicaux
					</c:if>
					<c:if test="${ nb_produits < 2 }">
						titre musical
					</c:if>
				</p>
				<p class="prix-total">${ total } €</p>
				<input type="submit" class="cta valider-panier" value="Payer" />
			</li>
		</ul>
	</form>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp" %>