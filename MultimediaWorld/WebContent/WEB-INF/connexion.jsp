<%-- HEAD & HEADER --%>
<%@ include file="_head.jsp" %>

<c:if test="${ flash_error != null }">
	<section class="flash error">
		<p><c:out value="${ flash_error }"/></p>
	</section>
</c:if>

<c:if test="${ flash_success != null }">
	<section class="flash success">
		<p><c:out value="${ flash_success }"/></p>
	</section>
</c:if>
	
<main id="connexion">
	<h1>Connexion</h1>
	<form name="connexion" id="modal" action="" method="POST">
		<article class="modal-part">
			<input type="text" name="username" placeholder="Nom d'utilisateur..."/>
		</article>
		
		<article class="modal-part">
			<input type="password" name="password" placeholder="Mot de passe..."/>
		</article>
		
		<article class="modal-part">
			<button type="submit">Se connecter</button>
		</article>
		
		<input type="hidden" name="formName" value="connexion" />
	</form>
</main>

<main id="inscription">
	<h1>Nouvel utilisateur</h1>
	<form id="modal" action="" method="POST">
		<article class="modal-part">
			<input name="username" type="text" placeholder="Nom d'utilisateur..."/>
		</article>
		
		<article class="modal-part">
			<input name="nom" type="text" placeholder="Nom..."/>
		</article>
		
		<article class="modal-part">
			<input name="prenom" type="text" placeholder="Prénom..."/>
		</article>
		
		<article class="modal-part">
			<input name="adresse" type="text" placeholder="Adresse..."/>
		</article>
		
		<article class="modal-part">
			<label>Vous êtes un...</label>
			<select name="role">
				<option value="particulier">Particulier</option>
				<option value="professionel">Professionnel</option>
			</select>
		</article>
		
		<article class="modal-part">
			<input name="password" type="password" placeholder="Mot de passe..."/>
		</article>
		
		<article class="modal-part">
			<button type="submit">S'inscrire</button>
		</article>
		
		<input type="hidden" name="formName" value="inscription" />
	</form>
</main>

<%-- FOOTER & FOOT --%>
<%@ include file="_foot.jsp" %>