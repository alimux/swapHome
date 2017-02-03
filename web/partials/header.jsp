
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>

        <title>Swap Home, Partez en vacances en échangeant votre maison !</title>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link href="<%= request.getContextPath() %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="<%= request.getContextPath() %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>

    </head>
    <body>

      <nav class="white" role="navigation">
        <div class="nav-wrapper container">
          <a id="logo-container" href="#" class="brand-logo">Logo</a>
          <ul class="right hide-on-med-and-down">
            <li><a href="<%= request.getContextPath() %>/user/register">Inscription </a></li>
            <li><a href="<%= request.getContextPath() %>/user/auth"> connexion</a></li>
          </ul>

          <ul id="nav-mobile" class="side-nav">
            <li><a href="#">Navbar Link</a></li>
          </ul>
          <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
        </div>
      </nav>

      <div id="index-banner" class="parallax-container">
        <div class="section no-pad-bot">
          <div class="container">
            <br><br>
            <h1 class="header center teal-text text-lighten-2"></h1>

            <br><br>

          </div>
        </div>
        <div class="parallax"><img src="<%= request.getContextPath() %>/images/tempBan.jpg" alt="Unsplashed background img 1"></div>
      </div>
