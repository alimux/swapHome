<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/partials/header.jsp" />
<br><br>
 <form method="post" action="register">
    <div class="container">
      <div class="row">
          <div class="col s12">
          
                <div class="card-panel grey lighten-4">
                  <div class="row">
                    
                      <h3 class="center blue-text text-darken-4"><i class="medium material-icons">assignment_ind</i></h3>
                      <h4 class="center blue-text text-darken-4">Inscription</h4>
                      <hr/>
                      <span class="center red-text text-darken-4">${erreur}</span>
                      <p class="center">pour vous inscrire, rien de plus simple ! <br /> Il vous suffit de remplir le formulaire d'inscription ci-dessous.</p>

                      <fieldset>
                        <legend>Identité</legend>
                        <div class="row">
               
                            <div class="input-field col s6">
                              <i class="material-icons prefix">account_circle</i>
                              <input id="nameUser" name="nameUser" type="text" size="50" class="validate" required>
                              <label for="nameUser">Nom</label>
                            </div>

                            <div class="input-field col s6">
                              <input id="firstNameUser" name="firstNameUser" size="50" type="text" class="validate" required>
                              <label for="firstNameUser">Prénom</label>
                            </div>

                      </div>
                    </fieldset>

                     <fieldset>
                        <legend>Adresse</legend>
                      <div class="row">

                            <div class="input-field col s12">
                              <i class="material-icons prefix">home</i>
                              <textarea id="Adresse" class="materialize-textarea" rows="5" required></textarea>
                              <label for="Adresse">Adresse</label>
                            </div>

                      </div>

                      <div class="row">

                            <div class="input-field col s6">
                              <input id="zipCodeUser" name="zipCodeUser" size="10" type="text" class="validate" required>
                              <label for="zipCodeUser">Code postal</label>
                            </div>

                            <div class="input-field col s6">
                              <input id="cityUser" name="cityUser" size="200" type="text" class="validate" required>
                              <label for="cityUser">Ville</label>
                            </div>
               
                      </div>
                    </fieldset>

                     <fieldset>
                        <legend>Informations utilisateur</legend>
                      <div class="row">

                              <div class="input-field col s6">
                              <i class="material-icons prefix">email</i>
                                <input id="emailUser" name="emailUser" size="150" type="text" class="validate" required>
                                <label for="emailUser">Email *sera votre nom d'utilisateur</label>
                              </div>

                              <div class="input-field col s6">
                              <i class="material-icons prefix">lock_outline</i>
                                <input id="passwdUSer" type="password" name="passwdUser" required>
                                <label for="passwdUser">Mot de passe : </label>
                              </div>
               
                      </div> 
                    </fieldset> 
                      <hr/>
                      <div class="row center">
                      <br/>
                            <button class="waves-effect waves-light btn blue darken-4 center" type="submit" name="action"><i class="material-icons center">account_box</i>Validez votre inscription</button>
                      </div>



                  </div>     
                </div>

         </div>

      </div>
    </div>
  </form>
<br><br>
<jsp:include page="/partials/footer.jsp" />