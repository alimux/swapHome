<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Swap Home, inscription</title>
</head>
<body>
<section>
    <h1>Inscription</h1>
    <span class="errorMessage">${erreur}</span>
    <p>pour vous inscrire, rien de plus simple ! <br /> Il vous suffit de remplir le formulaire d'inscription ci-dessous.é é à</p>
    <form method="post" action="inscription">
    <fieldset>
      <ol>
        <li>
      <label>Nom</label>
      <input type="text" size="20" name="nameUser" required="votre nom"/>
      <label>Prénom</label>
      <input type="text" size="20" name="firstNameUser" required=""/>
    </li>
    <li>
      <label>Adresse</label>
      <textarea name="adressUser" rows="5" required=""></textarea>
    </li>
    <li>
      <label>Code postal</label>
      <input type="text" size="10" name="zipCodeUser" required=""/>
      <label>Ville</label>
      <input type="text" size="20" name="cityUser" required=""/>
    </li>
    <li>
      <label>Email</label><span id="italiqueCommentaire"> *Cet email sera votre nom d'utilisateur.</span>
      <input type="text" size="20" name="emailUser" required=""/>
    </li>
    <li>
  <input type="submit" value="Validez votre inscription" />
</li>
</ol>
</fieldset>
    </form>
</section>

</body>
</html>
