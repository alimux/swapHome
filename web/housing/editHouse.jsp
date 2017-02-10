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
                  <h4 class="center blue-text text-darken-4">Modification d'une maison</h4>
                  <p class="center red-text text-darken-4">${erreur}</p>
                  <p class="center">Modifier une maison avec le formulaire si dessous</p>

                  <fieldset>
                     <legend>Adresse</legend>
                     <div class="row">
                         <div class="input-field col s12">
                           <i class="material-icons prefix">home</i>
                           <textarea id="address" name="address" class="materialize-textarea" rows="5"
                             required>${housing.address}</textarea>
                           <label for="address">Adresse</label>
                         </div>
                         <div class="input-field col s6">
                           <input id="zipCode" name="zipCode" size="10" type="number" class="validate"
                             value="${housing.zipCode}" required>
                           <label for="zipCode">Code postal</label>
                         </div>
                         <div class="input-field col s6">
                           <input id="city" name="city" size="200" type="text" class="validate"
                             value="${housing.city}" required>
                           <label for="city">Ville</label>
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
                      <div class="input-field col s4">
                        <input id="surface" name="surface" size="10" min="0" type="number" class="validate"
                          value="${housing.surface}" required>
                        <label for="surface">Surface</label>
                      </div>
                      <div class="input-field col s4">
                        <input id="roomNumber" name="roomNumber" size="4" min="0" type="number" class="validate"
                          value="${housing.roomNumber}" required>
                        <label for="roomNumber">Nombre de chambres</label>
                      </div>
                      <div class="input-field col s4">
                        <input id="gardenSurface" name="gardenSurface" size="10" min="0" type="number" class="validate"
                          value="${housing.gardenSurface}">
                        <label for="gardenSurface">Surface du jardin</label>
                      </div>
                    </div>
                </fieldset>

                <fieldset>
                   <legend>Préférences </legend>
                   <div class="row">
                       <div class="input-field col s4">

                              <c:set var="monthIndex" value="${housing.monthPrefered}"/>
                              <fmt:parseNumber var="idx" type="number" value="${monthIndex-1}" />
                             <select name="monthPrefered" class="validate" required>
                              <option value="${housing.monthPrefered}">${months[idx]}</option>
                                <c:set var="count" value="0" scope="page"/>
                                <c:forEach var="item" items="${months}">    
                                  <c:set var="count" value="${count+1}" scope="page"/>             
                                  <option value="${count}">${item.month}</option>
                                </c:forEach>
                              </select>
                            
                         <label for="monthPrefered">Mois préféré </label>
                       </div>
                       <div class="input-field col s4">
                          <select name="countryP1" class="validate" required>
                             
                              <c:set var="selectedCountryP1" value="${housing.countryP1}"/>
                              <c:forEach  var="selected" items="${countries}" varStatus="i">
                                  <c:if test="${selectedCountryP1== selected.name}">
                                    <c:set var="countryP1" value="${selected.country}"/>
                                  </c:if>
                              </c:forEach>

                            <option value="${housing.countryP1}">${countryP1}</option>                           
                                <c:forEach var="item" items="${countries}" varStatus="i">               
                                  <option value="${item.name}">${item.country}</option>
                                </c:forEach>
                            </select>
                         <label for="countryP1">Pays du Logement</label>
                       </div>
                       <div class="input-field col s4">
                        
                         <select name="countryP2" class="validate" required>
                            <c:set var="selectedCountryP2" value="${housing.countryP2}"/>
                              <c:forEach  var="selected" items="${countries}" varStatus="i">
                                  <c:if test="${selectedCountryP2== selected.name}">
                                    <c:set var="countryP2" value="${selected.country}"/>
                                  </c:if>
                              </c:forEach>

                            <option value="${housing.countryP2}">${countryP2}</option>
                                <c:forEach var="item" items="${countries}">                
                                  <option value="${item.name}">${item.country}</option>
                                </c:forEach>
                            </select>
                         <label for="countryP2">Pays de destination</label>
                       </div>
                   </div>
               </fieldset>

               <fieldset>
                  <legend>Images</legend>
                  <jsp:include page="imagesPartial.jsp" />
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
    <input type="hidden" name="type" value="house">
  </form>
<br><br>
<jsp:include page="/partials/footer.jsp" />
