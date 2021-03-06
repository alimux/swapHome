<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/partials/header.jsp" />
<br><br>
<div class="container">
  <div class="row">
    <!--side menu -->
    <div class="col s3">
      <jsp:include page="partials/menu.jsp" />
    </div>
    <!-- main part -->
    <div class="col s9">
      <div class="card-panel grey lighten-4">
        <div class="row">
          <h5 class="center blue-text text-darken-4">Votre Espace perso</h5>
        </div>
        <div class="row">
          <p>Bienvenue ${message} dans votre espace d'administration</p>
          <p>Vous pouvez gérer les logements à l'aide du menu sur votre gauche.</p>
        </div>
      </div>
    </div>
  </div>
</div>
<br><br>
<jsp:include page="/partials/footer.jsp" />
