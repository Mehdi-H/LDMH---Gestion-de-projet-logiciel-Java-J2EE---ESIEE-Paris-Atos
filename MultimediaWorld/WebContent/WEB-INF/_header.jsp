<header id="page-header">

	<%-- === HEADER === --%>
	<nav id="main-nav">
		<ul class="nav-items">

			<%-- LOGO ACCUEIL --%>
			<li class="item logo-wrapper"><a href="<%= request.getContextPath() %>"><img class="logo" src="<%= request.getContextPath() %>/assets/img/logo-multimedia-world.png"/></a></li>
			
			<%-- BARRE DE RECHERCHE --%>
			<li class="item" id="searchbar-wrapper">
				<form action="<%= request.getContextPath() %>/recherche" method="GET">
					<input id="searchbar" type="text" name="search" placeholder="Recherche ..."/>
					<button class="look" type="submit"><i class="fa fa-2x fa-search"></i></button>
				</form>
			</li>
			
			<li class="item" id="icon-pool">
				<%-- CONNEXION --%>
				<article class="icon-wrap">
					<c:choose>
						<c:when test="${ user != null }">
							<a href="<%= request.getContextPath() %>/deconnexion">
								<i class="icon fa fa-3x fa-user"></i>
								<span class="icon-text">
									<c:out value="${ user.prenom } ${ user.nom }"/><br/>
									Déconnexion
								</span>
							</a>
						</c:when>
						<c:otherwise>
							<a href="<%= request.getContextPath() %>/connexion">
								<i class="icon fa fa-3x fa-user"></i>
								<span class="icon-text">
									Connexion
								</span>
							</a>
						</c:otherwise>
					</c:choose>
				</article>
				
				<%-- PANIER --%>
				<article class="icon-wrap">
					<a href="<%= request.getContextPath() %>/panier">
						<i class="icon fa fa-3x fa-shopping-cart"></i>
						<span class="icon-text">Panier</span>
						<span id="panier-amount">
							<c:if test="${ panier_amount != null }">
								(<c:out value="${ panier_amount }"/>)
							</c:if>
						</span>
					</a>
				</article>
			</li>
		</ul>
	</nav>
	
	<hr/>
	
	<%-- === MENU === --%>
	<nav id="page-nav">
		<ul class="nav-items">
			
			<%-- NOUVEAUTÉS --%>
			<li class="item"><a href="<%= request.getContextPath() %>/rubriques/Nouveautés"><span>Nouveautés</span></a></li>
			
			<%-- RUBRIQUES --%>
			<li class="item dropdown-on-hover">Rubriques
				<ul class="dropdown">
					<c:forEach items="${ _rubriques_menu }" var="rubrique">
						<c:if test='${ rubrique.display() && rubrique.label != "Nouveautés" }'>
							<li class="item"><a href="<%= request.getContextPath() %>/rubriques/${ rubrique.label }"><span>${ rubrique.label }</span></a></li>
						</c:if>
					</c:forEach>
				</ul>
			</li>
			
			<%-- À PROPOS --%>
			<li class="item"><a href="<%= request.getContextPath() %>/presentation"><span>À propos</span></a></li>
			
			<%-- ADMINISTRATION --%>
			<c:if test="${ admin }">
				<li class="item">Admin
					<ul class="dropdown">
						<li class="item">
							<a href="<c:url value="/admin/nouveautes"/>">
								<span>Gestion des nouveautés</span>
							</a>
						</li>
						
						<li class="item">
							<a href="<c:url value="/admin/rubriques"/>">
								<span>Gestion des rubriques</span>
							</a>
						</li>
						
						<li class="item">
							<a href="<c:url value="/admin/commandes"/>">
								<span>Suivi des commandes</span>
							</a>
						</li>
						
						<li class="item">
							<a href="<c:url value="/admin/clients"/>">
								<span>Fiches clients</span>
							</a>
						</li>
						
						<li class="item">
							<a href="<c:url value="/admin/catalogue"/>">
								<span>Mise à jour du catalogue</span>
							</a>
						</li>
					</ul>
				</li>
			</c:if>
		</ul>
	</nav>
</header>