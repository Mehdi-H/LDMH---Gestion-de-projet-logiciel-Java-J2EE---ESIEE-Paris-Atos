<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<main id="liste-produits-wrap">
	<ul id="liste-produits">
		<li class="produit produit-head">
			<p>Titres</p>
			<p>Artistes</p>
			<p>Extraits</p>
			<p>Prix</p>
			<p>Achat</p>
		</li>
		
		<c:forEach items="${ produits }" var="pdt">
			<li class="produit">
				<section class="produit-info">
					<p class="nom-musique">${ produit.nom_produit }</p>
					<p class="nom-artiste">
						"CHERCHER ARTISTES"
					</p>
				</section>
				
				<audio class="audio-controls" controls="controls">
					<source src="http://webcodetools.com/media/sound.ogg" type="audio/ogg"/>
					<source src="http://webcodetools.com/media/sound.mp3" type="audio/mpeg"/>
					<source src="http://webcodetools.com/media/sound.wav" type="audio/wav"/>
					<span>Votre navigateur ne supporte pas le player audio.</span>
				</audio>
				
				<p class="prix-produit">${ produit.prix }</p>
				<button class="ajouter-panier">+</button>
			</li>
		</c:forEach>
		
	</ul>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp" %>