<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/partials/header.jsp" />
<br><br>
<div class="row">
  <!--side menu -->
  <div class="col s2">
    <jsp:include page="partials/menu.jsp" />
    <a class="waves-effect waves-light btn-flat"
      href="../../housing/create?type=house"><i class="material-icons left">add</i> Maison</a>
    <a class="waves-effect waves-light btn-flat"
      href="../../housing/create?type=apartment"><i class="material-icons left">add</i> Appartement</a>
  </div>
  <div class="col s10 css3-s4">
    <!-- main part -->
    <c:set var="index" value="0"/>
    <c:forEach items="${housings}" var="item">
      <c:set var="isApartment" value="false"/>
      <c:if test="${item.class.simpleName == 'Apartment'}">
        <c:set var="isApartment" value="true"/>
      </c:if>
      <!-- modales -->
      <div id="housingInfo${index}" class="modal housing-info">
        <div class="modal-content">
          <h4>Informations sur le logement</h4>
          <p>${item.address}<br/>${item.zipCode}, ${item.city} (${item.countryP1})</p>
          <div class="row">
            <div class="col l6 s12">
              <ul class="collection">
                <li class="collection-item">
                  Surface
                  <span class="badge" data-badge-caption="m²"><c:out value="${item.surface}"/></span>
                </li>
                <c:if test="${!isApartment}">
                  <li class="collection-item">
                    Surface du jardin
                    <span class="badge" data-badge-caption="m²"><c:out value="${item.gardenSurface}"/></span>
                  </li>
                </c:if>
                <li class="collection-item">
                  Nombre de chambres
                  <span class="badge" data-badge-caption="chambre(s)"><c:out value="${item.roomNumber}"/></span>
                </li>
              </ul>
            </div>
            <div class="col l6 s12">
              <ul class="collection">
                <li class="collection-item">
                  Mois préféré
                  <span class="badge"><c:out value="${item.monthPrefered}"/></span>
                </li>
                <li class="collection-item">
                  Pays de destination
                  <span class="badge"><c:out value="${item.countryP2}"/></span>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <a href="#!" class="modal-action modal-close waves-effect waves-light btn-flat">Fermer</a>
        </div>
      </div>
      <!-- card -->
      <div class="card grey lighten-4">
        <c:if test="${not empty item.images}">
          <c:set var="fistHousing" value="${item.images.iterator().next()}"/>
          <div class="card-image">
            <img src="<%= request.getContextPath() %>/${fistHousing.name}"
            class="materialboxed" data-caption="Image du logement"
            src="<%= request.getContextPath() %>/${fistHousing.name}">
          </div>
        </c:if>
        <div class="card-content blue-grey-text darken-1">
          <span class="card-title">${isApartment ? "Appartement" : "Maison"}</span>
          <p><c:out value="${item.description}">Description par défaut</c:out></p>
        </div>
        <div class="card-action">
          <a href="#housingInfo${index}">Voir</a>
          <a href="../../housing/edit?id=${item.id}">Modifier</a>
          <a href="../../housing/delete?id=${item.id}"
            onclick="return confirm('Voulez-vous supprimer ce logement ?');">Supprimer</a>
        </div>
      </div>
      <c:set var="index" value="${index+1}"/>
    </c:forEach>
  </div>
</div>
<br><br>
<jsp:include page="/partials/footer.jsp" />
