<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${pageContext.request.requestURI=='/swapHome/user/admin/info.jsp'}" >
  <c:set var="info" value="active blue darken-4"/>
</c:if>
<c:if test="${pageContext.request.requestURI=='/swapHome/user/admin/housing.jsp'}" >
  <c:set var="housing" value="active blue darken-4"/>
</c:if>

<div class="collection blue darken-4">
  <ul>
    <li><a href="info" class="collection-item ${info}">Informations</a></li>
    <li><a href="housing" class="collection-item ${housing}">Logements</a></li>
  </ul>
</div>
