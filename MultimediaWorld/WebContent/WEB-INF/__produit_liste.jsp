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
	
	<%-- Extrait --%>
	<td>
		<audio class="audio-controls" controls="controls">
			<source src="http://webcodetools.com/media/sound.ogg" type="audio/ogg"/>
			<source src="http://webcodetools.com/media/sound.mp3" type="audio/mpeg"/>
			<source src="http://webcodetools.com/media/sound.wav" type="audio/wav"/>
			<span>Votre navigateur ne supporte pas le player audio.</span>
		</audio>
	</td>
	
	<%-- Prix --%>
	<td>${ pdt.prix } €</td>
	
	<%-- Achat --%>
	<td>
		<button class="ajouter-panier" data-product-id="${ pdt.id }">+</button>
	</td>
</li>