<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/partials/header.jsp" />
<br><br>
<div class="container">
	<div class="row">
   		<!-- main part -->
   		<div class="col s12">

				 <c:forEach items="${housings}" var="item">
						<div class="col s4">
							<div class="card grey lighten-4">
								<div class="card-content blue-grey-text darken-1">
		              <span class="card-title">Logement</span>
		              <p><c:out value="${item.description}">Description par défaut</c:out></p>
		            </div>
								<div class="card-action">
		              <a href="#">Echanger</a>
		            </div>
							</div>
						</div>
					</c:forEach>

    	</div>
    </div>
</div>
<br><br>
<jsp:include page="/partials/footer.jsp" />
