<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/partials/header.jsp" />
<section>
    <h1>Inscription</h1>
    <span class="errorMessage">${erreur}</span>
    <p>pour vous inscrire, rien de plus simple ! <br /> Il vous suffit de remplir le formulaire d'inscription ci-dessous.</p>
    
    <form method="post" action="register">
        <div class="input-field">
          <input id="nameUser" name="nameUser" type="text" size="50" class="validate" required>
          <label for="nameUser">Nom</label>
        </div>
        
        <div class="input-field">
          <input id="firstNameUser" name="firstNameUser" size="50" type="text" class="validate" required>
          <label for="firstNameUser">Prénom</label>
        </div>
        
        <div class="input-field">
          <textarea id="Adresse" class="materialize-textarea" rows="5" required></textarea>
          <label for="Adresse">Adresse</label>
        </div>
        
        <div class="input-field">
          <input id="zipCodeUser" name="zipCodeUser" size="10" type="text" class="validate" required>
          <label for="zipCodeUser">Code postal</label>
        </div>
        
        <div class="input-field">
          <input id="cityUser" name="cityUser" size="200" type="text" class="validate" required>
          <label for="cityUser">Ville</label>
        </div>
        
        <div class="input-field">
          <input id="emailUser" name="emailUser" size="150" type="text" class="validate" required>
          <label for="emailUser">Email *sera votre nom d'utilisateur</label>
        </div>
        
        <input class="waves-effect waves-light btn" type="submit" value="validez votre inscription"></input>
    </form>
</section>
<jsp:include page="/partials/footer.jsp" />