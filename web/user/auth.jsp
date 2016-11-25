<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/partials/header.jsp" />
<section>
    <h1>connexion</h1>

    <form method="post" action="auth">
        <div class="input-field">
          <input id="emailUser" name="emailUser" type="text" class="validate" required>
          <label for="emailUser">Adresse email</label>
        </div>
        
        <div class="input-field">
          <input id="passwordUser" name="passwordUser" type="text" class="validate" required>
          <label for="passwordUser">Mot de passe</label>
        </div>
        <span class="errorMessage">${erreur}</span>
        
        <input class="waves-effect waves-light btn" type="submit" value="connexion"></input>
    </form>
</section>
<jsp:include page="/partials/footer.jsp" />
