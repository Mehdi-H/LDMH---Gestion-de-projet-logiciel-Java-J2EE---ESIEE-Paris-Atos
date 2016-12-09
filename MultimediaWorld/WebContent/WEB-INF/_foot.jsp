					
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
	    	$(document).on("click", ".ajouter-panier.ajax", function() {
	    		var params = {
	    			product_id: $(this).data("product-id")
	    		};
	    		
	    		$.post("<%= request.getContextPath() %>/ajout_panier", $.param(params), function(response) {
	    			$("#panier-amount").text("("+response+")");
	    		});
	    	});
	    </script>
	    
	</body>
</html>