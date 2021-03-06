					
					<%-- CONTENU DE LA PAGE --%>
				</main>
			</div>
			
			<%-- BODY FOOTER --%>
			<%@ include file="_footer.jsp" %>
		</div>
		
		<%-- JAVASCRIPT--%>
		<script
			src="https://code.jquery.com/jquery-2.2.3.min.js"
			integrity="sha256-a23g1Nt4dtEYOj7bR+vTu7+T8VP13humZFBJNIYoEJo="
			crossorigin="anonymous">
	    </script>
	    
	    <script>
	    	// ==============================================================
	    	// == AJOUTER AU PANIER - AJAX
	    	// ==============================================================
	    		
	    	$(document).on("click", ".ajouter-panier.ajax", function() {
	    		var params = {
	    			product_id: $(this).data("product-id"),
	    			method: "ajouter"
	    		};
	    		
	    		$.post("<%= request.getContextPath() %>/modifier_panier", $.param(params), function(response) {
	    			$("#panier-amount").text("("+response+")");
	    		});
	    	});
	    	
	    	// ==============================================================
	    	// == SUPPRIMER DU PANIER - AJAX
	    	// ==============================================================
	    	
	    	$(document).on("click", ".supprimer-panier.ajax", function() {
	    		var params = {
	    			product_id: $(this).data("product-id"),
	    			method: "supprimer"
	    		};
	    		
	    		var row = $(this).parent().parent();
	    		
	    		$.post("<%= request.getContextPath() %>/modifier_panier", $.param(params), function(response) {
	    			row.fadeOut("slow", function() {
	    				$("#panier-amount").text("("+response+")");
	    			})
	    		});
	    	});
	    	
	    	// ==============================================================
	    	// == MODIFIER QUANTIT� DU PANIER - AJAX
	    	// ==============================================================
	    	
	    	$(document).on("input", ".quantite-panier.ajax", function() {
	    		console.log("input");
	    		
	    		var params = {
	    			product_id: $(this).data("product-id"),
	    			method: "modifier",
	    			quantite: $(this).val()
	    		};
	    		
	    		$.post("<%= request.getContextPath() %>/modifier_panier", $.param(params), function(response) {
	    			console.log("quantit� modifi�e");
	    		});
	    	});
	    	
	    	// ==============================================================
	    	// == ETAT COMMANDE
	    	// ==============================================================
	    	
	    	<c:if test="${ etats != null }">
		    	$(document).ready(function() {
		    		var etats = [];
		    		
		    		<c:forEach items="${ etats }" var="etat">
		    			etats.push("${ etat.toString() }")
		    		</c:forEach>
	
		    		$(document).on("input", "#etat-commande", function() {
		    			$("#etat-text").text(etats[$(this).val()]);
		    		});
		    	});
		    </c:if>
	    </script>
	    
	</body>
</html>