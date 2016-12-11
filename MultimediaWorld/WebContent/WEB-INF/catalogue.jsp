<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<main id="mise-a-jour-catalogue">
	<section id="upload-wrap">
		<form action="" method="POST" enctype="multipart/form-data">
			<p>
				<label for="csv">Déposez un fichier (csv)</label>
			</p>
			<p>
				<input type="file" name="csv" id="csv"/>
			</p>
			<p>
				<input type="submit" value="Importer" />
			</p>
		</form>
	</section>
	
	<c:if test="${ produits != null }">
		<hr/>
		<h3>Catalogue importé</h3>
		
		<section class="flash info">
			<p>Voici un aperçu du catalogue que vous venez d'importer.</p>
		</section>
		
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
	</c:if>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp"%>