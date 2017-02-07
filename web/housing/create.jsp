<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/partials/header.jsp" />
<br><br>
 <form method="post" action="create">
    <div class="container">
      <div class="row">
          <div class="col s12">

                <div class="card-panel grey lighten-4">
                  <div class="row">

                      <h3 class="center blue-text text-darken-4"><i class="medium material-icons">assignment_ind</i></h3>
                      <h4 class="center blue-text text-darken-4">Ajout d'un logement</h4>
                      <p class="center red-text text-darken-4">${erreur}</p>
                      <p class="center">Ajouter un logemement en remplissant le formulaire si dessous</p>

                      <fieldset>
                         <legend>Adresse</legend>
                         <div class="row">
                             <div class="input-field col s12">
                               <i class="material-icons prefix">home</i>
                               <textarea id="address" name="address" class="materialize-textarea" rows="5" required></textarea>
                               <label for="address">Adresse</label>
                             </div>
                             <div class="input-field col s6">
                               <input id="zipCode" name="zipCode" size="10" type="number" class="validate" required>
                               <label for="zipCode">Code postal</label>
                             </div>
                             <div class="input-field col s6">
                               <input id="city" name="city" size="200" type="text" class="validate" required>
                               <label for="city">Ville</label>
                             </div>
                         </div>
                     </fieldset>

                     <fieldset>
                        <legend>Informations</legend>
                        <div class="row">
                          <div class="input-field col s12">
                            <i class="material-icons prefix">info outline</i>
                            <textarea id="description" name="description" class="materialize-textarea" rows="5" required></textarea>
                            <label for="description">Description</label>
                          </div>
                          <div class="input-field col s6">
                            <select id="type" required>
                              <option value="" disabled selected>Choisissez le type</option>
                              <option value="House">Maison</option>
                              <option value="Apartment">Appartement</option>
                            </select>
                            <label for="type">Type de logement</label>
                          </div>
                          <div class="input-field col s6">
                            <input id="surface" name="surface" size="10" min="0" type="number" class="validate" required>
                            <label for="surface">Surface</label>
                          </div>
                          <div class="input-field col s6">
                            <input id="roomNumber" name="roomNumber" size="4" min="0" type="number" class="validate" required>
                            <label for="roomNumber">Nombre de chambres</label>
                          </div>
                          <div class="input-field col s6">
                            <input id="gardenSurface" name="gardenSurface" size="10" min="0" type="number" class="validate">
                            <label for="gardenSurface">Surface du jardin</label>
                          </div>
                        </div>
                    </fieldset>

                    <fieldset>
                       <legend>Préférences</legend>
                       <div class="row">
                           <div class="input-field col s4">
                             <input id="monthPrefered" name="monthPrefered" size="2"
                               min="1" max="12" type="number" class="validate" required>
                             <label for="monthPrefered">Mois préféré</label>
                           </div>
                           <div class="input-field col s4">
                             <input id="countryP1" name="countryP1" size="200" type="text" class="validate" required>
                             <label for="countryP1">Pays 1</label>
                           </div>
                           <div class="input-field col s4">
                             <input id="countryP2" name="countryP2" size="200" type="text" class="validate" required>
                             <label for="countryP2">Pays 2</label>
                           </div>
                       </div>
                   </fieldset>
                    <div class="row center">
                      <br/>
                        <button class="waves-effect waves-light btn blue darken-4 center"
                          type="submit" name="action"><i class="material-icons center">account_box</i> Validez la création</button>
                      </div>
                  </div>
                </div>

         </div>

      </div>
    </div>
  </form>
<br><br>
<jsp:include page="/partials/footer.jsp" />
