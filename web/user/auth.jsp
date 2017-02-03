<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../partials/header.jsp" />
<div class="container">

    <div class="section">
      <div class="row">
            <div class="col s6 center">
              <div class="card-panel">
                <div class="card-content">
                <h3 class="center brown-text"><i class="material-icons">https</i>connexion</h3>

                <form method="post" action="auth">
                  <div class="row">
                    <div class="input-field">
                      <i class="mdi-action-account-circle prefix"> perm_identity</i>
                      <input id="emailUser" name="emailUser" type="text" class="validate" required>
                      <label for="emailUser">Adresse email</label>
                    </div>
                  </div>
                    
                  <div class="row">
                    <div class="input-field col s4">
                      <i class="mdi-action-lock-outline prefix"></i>
                      <input id="icon_password" name="passwordUser" type="text" class="validate" required>
                      <label for="icon_password">Mot de passe</label>
                    </div>
                  </div>
                  <div class="row">
                    <span class="errorMessage">${erreur}</span>
                  </div>
                  <div class="row"> 
                    <button class="waves-effect waves-light btn" type="submit" name="action"><i class="mdi-action-lock-open"></i> Connexion</button>
                    <!--<input class="waves-effect waves-light btn" type="submit" value="connexion"></input>-->
                  </div>
                </form>
              </div>
              </div>
            </div>
         </div>
    </div>
    <br><br>

</div>

<jsp:include page="../partials/footer.jsp" />
