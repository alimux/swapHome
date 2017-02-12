<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="/partials/header.jsp" />
<div class="container">
  <div class="section">
    <!--   Icon Section   -->
    <div class="col s12 m4 center">
      <div class="icon-block">
        <img src="<%= request.getContextPath() %>/images/homeIcon.png">
        <h5 class="center">Allez chez eux ! Ils viennent chez vous !!!</h5>
      </div>
      <div class="row">
        <p class="light">
        <p>Le concept de voyage le plus simple au monde. Plus de X propriétés dans plus de X pays. <br/>
          Il s'agit d'un échange mutuel : vous restez chez moi, je reste chez vous; en même temps. <br />
          Des Vacances inoubliables, vivez intensément votre séjour !!
        </p>
        </p>
      </div>
    </div>
  </div>
</div>
<div class="container">
  <div class="section">
    <div class="col s12 m4 center">
      <div class="row">
        <img src="<%= request.getContextPath() %>//images/winIcon.png">
        <h5>Nombre reccord d'échange depuis la création de notre site : </h5>
      </div>
      <div class="row">
        <p class="light">Le nombre reccord d'échange est de : <br><br>
          Continuez à vivre des vacances incroyables et faîtes nous part de vos souvenirs !
        </p>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/partials/footer.jsp" />
