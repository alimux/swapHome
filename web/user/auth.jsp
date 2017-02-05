<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="../partials/header.jsp" />
<br><br>
<form method="post" action="auth">
<div class="container">

      <div class="row">
          <div class="col s8 offset-s2">

               <div class="card horizontal">
                     <div class="card-image">
                        <img src="<%= request.getContextPath() %>/images/connexion.jpg">
                      </div>

                    <div class="card-stacked grey lighten-4">
                        <div class="card-content">
                            <h4 class="center blue-text text-darken-4"><i class="material-icons">https</i>connexion</h4> 

                            
                                <div class="row">
                                  <div class="input-field">
                                    <i class="material-icons prefix">account_circle</i>
                                    <input id="emailUser" name="emailUser" type="email" class="validate" required>
                                    <label for="emailUser">Adresse email</label>
                                  </div>
                                </div>
                              
                                <div class="row">
                                  <div class="input-field">
                                    <i class="material-icons prefix">lock_outline</i>
                                    <input id="icon_password" name="passwordUser" type="password" class="validate" required>
                                    <label for="icon_password">Mot de passe</label>
                                  </div>
                                </div>
                                <div class="row center">
                                  <span class="center red-text text-darken-4">${erreur}</span>
                                </div>

                          

                        </div>
                    
                
                      <div class="card-action grey lighten-4">
                          <button class="waves-effect waves-light btn blue darken-4 right " type="submit" name="action"><i class="material-icons left">lock_open</i> Connexion</button>
                      </div>

                  </div>
         
             </div>

        </div>

    <br><br>

    </div>

</div>
</form>

<jsp:include page="../partials/footer.jsp" />
