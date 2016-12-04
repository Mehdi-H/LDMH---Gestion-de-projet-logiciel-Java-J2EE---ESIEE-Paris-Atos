<%@ page pageEncoding="UTF-8" %>

<li class="produit">
	<section class="produit-info">
		<p class="nom-musique">${ pdt.nom_produit }</p>
		<p class="nom-artiste">
			<c:forEach items="${ pdt.artistes }" var="artiste" varStatus="status">
				${ artiste.nom }<c:if test="${ !status.last }">, </c:if>
			</c:forEach>
		</p>
	</section>
	
	<audio class="audio-controls" controls="controls">
		<source src="http://webcodetools.com/media/sound.ogg" type="audio/ogg"/>
		<source src="http://webcodetools.com/media/sound.mp3" type="audio/mpeg"/>
		<source src="http://webcodetools.com/media/sound.wav" type="audio/wav"/>
		<span>Votre navigateur ne supporte pas le player audio.</span>
	</audio>
	
	<p class="prix-produit">${ pdt.prix } €</p>
	<button class="ajouter-panier">+</button>
</li>