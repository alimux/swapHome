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
				 	href="../../housing/create"><i class="material-icons left">add</i> Logement</a>
   		</div>

			<div class="col s9">

	   	 <!-- main part -->
			 <c:forEach items="${housings}" var="item">
						<div class="col s6">
							<div class="card-panel grey lighten-4">
								<div class="row">
									<h5 class="center blue-text text-darken-4">Logement</h5>
								</div>
								<div class="row">
									<p><c:out value="${item.description}">Description par défaut</c:out></p>
								</div>
							</div>
						</div>
				</c:forEach>

			</div>
		</div>
</div>
<br><br>
<jsp:include page="/partials/footer.jsp" />
