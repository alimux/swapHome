<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/partials/header.jsp" />
<br><br>
<form method="post" action="edit" enctype="multipart/form-data">
  <div class="container">
    <div class="row">
      <div class="col s12">
        <div class="card-panel grey lighten-4">
          <div class="row">
            <h3 class="center blue-text text-darken-4"><i class="medium material-icons">assignment_ind</i></h3>
            <h4 class="center blue-text text-darken-4">Modification d'un appartement</h4>
            <p class="center red-text text-darken-4">${erreur}</p>
            <p class="center">Modifier un appartement avec le formulaire si dessous</p>
            <fieldset>
              <legend>Adresse</legend>
              <div class="row">
                <div class="input-field col s12">
                  <i class="material-icons prefix">home</i>
                  <textarea id="address" name="address" class="materialize-textarea" rows="5"
                    required>${housing.address}</textarea>
                  <label for="address">Adresse</label>
                </div>
                <div class="input-field col s4">
                  <input id="zipCode" name="zipCode" size="10" type="text" class="validate"
                    value="${housing.zipCode}" required>
                  <label for="zipCode">Code postal</label>
                </div>
                <div class="input-field col s4">
                  <input id="city" name="city" size="200" type="text" class="validate"
                    value="${housing.city}" required>
                  <label for="city">Ville</label>
                </div>
                <div class="input-field col s4">
                  <select name="countryP1" class="validate" required>
                    <c:forEach var="item" items="${countries}">
                      <c:set var="selected" value=""/>
                      <c:if test="${housing.countryP1 == item.name}">
                        <c:set var="selected" value="selected"/>
                      </c:if>
                      <option value="${item.name}" ${selected}>${item.country}</option>
                    </c:forEach>
                  </select>
                  <label for="countryP1">Pays</label>
                </div>
              </div>
            </fieldset>
            <fieldset>
              <legend>Informations</legend>
              <div class="row">
                <div class="input-field col s12">
                  <i class="material-icons prefix">info outline</i>
                  <textarea id="description" name="description" class="materialize-textarea" rows="5"
                    required>${housing.description}</textarea>
                  <label for="description">Description</label>
                </div>
                <div class="input-field col s6">
                  <input id="surface" name="surface" size="10" min="0" type="number" class="validate"
                    value="${housing.surface}" required>
                  <label for="surface">Surface en m²</label>
                </div>
                <div class="input-field col s6">
                  <input id="roomNumber" name="roomNumber" size="4" min="0" type="number" class="validate"
                    value="${housing.roomNumber}" required>
                  <label for="roomNumber">Nombre de chambres</label>
                </div>
              </div>
            </fieldset>
            <fieldset>
              <legend>Préférences</legend>
              <div class="row">
                <div class="input-field col s6">
                  <select name="monthPrefered" class="validate" required>
                    <c:set var="monthIndex" value="${housing.monthPrefered}"/>
                    <fmt:parseNumber var="idx" type="number" value="${monthIndex-1}" />
                    <c:set var="count" value="0" scope="page"/>
                    <c:forEach var="item" items="${months}">
                      <c:set var="count" value="${count+1}" scope="page"/>
                      <c:set var="selected" value=""/>
                      <c:if test="${count == housing.monthPrefered}">
                        <c:set var="selected" value="selected"/>
                      </c:if>
                      <option value="${count}" ${selected}>${item.month}</option>
                    </c:forEach>
                  </select>
                  <label for="monthPrefered">Mois préféré</label>
                </div>
                <div class="input-field col s6">
                  <select name="countryP2" class="validate" required>
                    <c:forEach var="item" items="${countries}">
                      <c:set var="selected" value=""/>
                      <c:if test="${housing.countryP2 == item.name}">
                        <c:set var="selected" value="selected"/>
                      </c:if>
                      <option value="${item.name}" ${selected}>${item.country}</option>
                    </c:forEach>
                  </select>
                  <label for="countryP2">Pays de destination</label>
                </div>
              </div>
            </fieldset>
            <fieldset class="no-padding">
              <legend>Images</legend>
              <div class="row no-margin multiple-input-image-viewer">
                <input class="files-input" id="files-input" name="file[]" type="file" multiple />
                <label for="files-input" class="files-input-label">
                  <div class="center-container"><div class="center-child">
                    Choisissez une ou plusieurs images
                  </div></div>
                </label>
                <div class="multiple-input-images">
                  <c:if test="${not empty housing.images}">
                    <c:forEach items="${housing.images}" var="image"><div class="col s3">
                      <img src='<%= request.getContextPath() %>/${image.name}' style='width: 100%;'/>
                    </div></c:forEach>
                  </c:if>
                </div>
              </div>
            </fieldset>
            <div class="row center">
              <br/>
              <button class="waves-effect waves-light btn blue darken-4 center"
                type="submit" name="action"><i class="material-icons center">account_box</i> Validez la modification</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <input type="hidden" name="id" value="${housing.id}">
  <input type="hidden" name="type" value="apartment">
</form>
<br><br>
<jsp:include page="/partials/footer.jsp" />
