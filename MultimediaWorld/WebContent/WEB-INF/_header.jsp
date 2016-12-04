<header id="page-header">

	<%-- === HEADER === --%>
	<nav id="main-nav">
		<ul class="nav-items">

			<%-- LOGO ACCUEIL --%>
			<li class="item logo-wrapper"><a href="<%= _accueil %>"><img class="logo" src="<%= _accueil %>/assets/img/logo-multimedia-world.png"/></a></li>
			
			<%-- BARRE DE RECHERCHE --%>
			<li class="item" id="searchbar-wrapper">
				<form action="<%= _accueil %>/recherche" method="GET">
					<input id="searchbar" type="text" name="search" placeholder="Recherche ..."/>
					<button class="look" type="submit"><i class="fa fa-2x fa-search"></i></button>
				</form>
			</li>
			
			<li class="item" id="icon-pool">
				<%-- CONNEXION --%>
				<article class="icon-wrap"><a href="<%= _accueil %>/connexion"><i class="icon fa fa-3x fa-user"></i><span class="icon-text">compte</span></a></article>
				
				<%-- PANIER --%>
				<article class="icon-wrap"><a href="<%= _accueil %>/panier"><i class="icon fa fa-3x fa-shopping-cart"></i><span class="icon-text">panier</span></a></article>
			</li>
		</ul>
	</nav>
	
	<hr/>
	
	<%-- === MENU === --%>
	<nav id="page-nav">
		<ul class="nav-items">
			
			<%-- NOUVEAUT�S --%>
			<li class="item"><a href="<%= _accueil %>/rubriques/Nouveaut�s"><span>Nouveaut�s</span></a></li>
			
			<%-- RUBRIQUES --%>
			<li class="item dropdown-on-hover">Rubriques
				<ul class="dropdown">
					<c:forEach items="${ _rubriques_menu }" var="rubrique">
						<c:if test='${ rubrique.display() && rubrique.label != "Nouveaut�s" }'>
							<li class="item"><a href="<%= _accueil %>/rubriques/${ rubrique.label }"><span>${ rubrique.label }</span></a></li>
						</c:if>
					</c:forEach>
				</ul>
			</li>
			
			<%-- � PROPOS --%>
			<li class="item"><a href="<%= _accueil %>/presentation"><span>� propos</span></a></li>
		</ul>
	</nav>
</header>