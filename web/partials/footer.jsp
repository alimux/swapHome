<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
  <head>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="css/style.css" />
    <title>Swap Home, connexion...</title>
  </head>
  <body>
    <section>
    <h1>connnexion</h1>

    <form method="post" action="connect">
    <fieldset>
      <ol>
    <li>
      <label>Identifiant : </label>
      <input type="text" size="20" name="emailUser" required=""/>
    </li>
    <li>
      <label>Mot de passe : </label>
      <input type="text" size="20" name="passwordUser" required=""/>
    </li>
    <li>
  <input type="submit" value="connexion..." />
</li>
<li>
    <span class="errorMessage">${erreur}</span>
</li>
</ol>
</fieldset>
    </form>
  </body>
</html>
