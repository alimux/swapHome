<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <title>Swap Home, Partez en vacances en échangeant votre maison !</title>
    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="<%= request.getContextPath() %>/css/default.style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="<%= request.getContextPath() %>/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
  </head>
  <body>
    <nav class="white" role="navigation">
      <div class="nav-wrapper container">
        <a id="logo-container" href="<%= request.getContextPath() %>/index.jsp" class="brand-logo"><img src="<%= request.getContextPath() %>/images/logoSwapHome.jpg" height="60"></a>
        <ul class="right hide-on-med-and-down">
          <c:if test="${ !empty sessionScope.emailUser and sessionScope.isAdminUser }">
            <li><a href="<%= request.getContextPath() %>/user/admin"><i class="material-icons left">create</i>Modération</a></li>
          </c:if>
          <c:if test="${ !empty sessionScope.emailUser }">
            <li><a href="<%= request.getContextPath() %>/user/home"><i class="material-icons left">account_box</i>Mon compte</a></li>
            <li><a href="<%= request.getContextPath() %>/user/disconnect"><i class="material-icons left">input</i>Déconnexion</a>
          </c:if>
          <c:if test="${ empty sessionScope.emailUser }">
            <li><a href="<%= request.getContextPath() %>/user/register"><i class="material-icons left">assignment_ind</i>Inscription</a></li>
            <li><a href="<%= request.getContextPath() %>/user/auth"><i class="material-icons left">account_circle</i>Se Connecter</a></li>
          </c:if>
        </ul>
        <ul id="nav-mobile" class="side-nav">
          <c:if test="${ !empty sessionScope.emailUser and sessionScope.isAdminUser }">
            <li><a href="<%= request.getContextPath() %>/user/admin"><i class="material-icons left">create</i>Modération</a></li>
          </c:if>
          <c:if test="${ !empty sessionScope.emailUser }">
            <li><a href="<%= request.getContextPath() %>/user/home"><i class="material-icons left">account_box</i>Mon compte</a></li>
            <li><a href="<%= request.getContextPath() %>/user/disconnect"><i class="material-icons left">input</i>Déconnexion</a>
          </c:if>
          <c:if test="${ empty sessionScope.emailUser }">
            <li><a href="<%= request.getContextPath() %>/user/register"><i class="material-icons left">assignment_ind</i>Inscription</a></li>
            <li><a href="<%= request.getContextPath() %>/user/auth"><i class="material-icons left">account_circle</i>Se Connecter</a></li>
          </c:if>
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
      <div class="parallax"><img src="<%= request.getContextPath() %>/images/swapHomeContext.jpg" alt="Unsplashed background img 1"></div>
    </div>
