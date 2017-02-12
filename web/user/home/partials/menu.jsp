<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${pageContext.request.requestURI=='/swapHome/user/home/info.jsp'}" >
  <c:set var="info" value="active blue darken-4"/>
</c:if>
<c:if test="${pageContext.request.requestURI=='/swapHome/user/home/housing.jsp'}" >
  <c:set var="housing" value="active blue darken-4"/>
</c:if>
<c:if test="${pageContext.request.requestURI=='/swapHome/user/home/offers.jsp'}" >
  <c:set var="offers" value="active blue darken-4"/>
</c:if>

<div class="collection blue darken-4">
  <ul>
    <li><a href="info" class="collection-item ${info}">Informations</a></li>
    <li><a href="housing" class="collection-item ${housing}">Logements</a></li>
    <li><a href="offers" class="collection-item ${offers}">Offres compatibles</a></li>
  </ul>
</div>
