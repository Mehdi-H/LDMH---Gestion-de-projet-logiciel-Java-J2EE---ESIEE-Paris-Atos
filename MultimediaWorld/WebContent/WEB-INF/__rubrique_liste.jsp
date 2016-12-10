<%@ page pageEncoding="UTF-8" %>

<tr class="content-row">
	<form action="" method="POST">
	
		<%-- Rubrique --%>
		<td>${ rub.label }</td>
		
		<%-- Position --%>
		<td>
			<select name="position">
				<option value="-1">Ne pas afficher</option>
				<c:forEach begin="1" end="${ nb_rubriques }" var="i">
					<option value="${ i }" ${ i == rub.place_menu ? "selected" : "" }>
						Position ${ i }
					</option>
				</c:forEach>
			</select>
		</td>
		
		<%-- Renommer --%>
		<td>
			<input type="text" name="nouveau_label_rubrique" placeholder="Renommer" />
		</td>
		
		<%-- Valider --%>
		<td>
			<input type="submit" name="submit" value="Valider" />
		</td>
		
		<%-- Supprimer --%>
		<td>
			<input type="submit" name="submit" value="Supprimer" />
		</td>
	
		<input type="hidden" name="label_rubrique" value="${ rub.label }" />
	</form>
</tr>