<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/partials/header.jsp" />
<br><br>
<div class="container">
	<div class="row">
   		<!--side menu -->
   		<div class="col s3">
   			<jsp:include page="../partials/menu.jsp" />
				<a class="waves-effect waves-light btn blue darken-4"
				 	href="../../housing/create?type=house"><i class="material-icons left">add</i> Maison</a>
				<a class="waves-effect waves-light btn blue darken-4"
				 	href="../../housing/create?type=apartment"><i class="material-icons left">add</i> Appartement</a>
   		</div>

			<div class="col s9">

	   	 <!-- main part -->
			 <c:forEach items="${housings}" var="item">
					<div class="col s6">
						<div class="card grey lighten-4">
							<div class="card-content blue-grey-text darken-1">
	              <span class="card-title">Logement</span>
	              <p><c:out value="${item.description}">Description par défaut</c:out></p>
	            </div>
							<div class="card-action">
	              <a href="../../housing/edit?id=${item.id}">Modifier</a>
	              <a href="../../housing/delete?id=${item.id}"
									onclick="return confirm('Voulez-vous supprimer ce logement ?');">Supprimer</a>
	            </div>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
</div>
<br><br>
<jsp:include page="/partials/footer.jsp" />
