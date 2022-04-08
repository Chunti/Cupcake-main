<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Welcome to the frontpage
    </jsp:attribute>

  <jsp:attribute name="footer">
        Welcome to the frontpage
    </jsp:attribute>

  <jsp:body>
      <form method="post">

      <c:forEach items="${sessionScope.orderline}" var="item" varStatus="loop">

          <div class="container"  >
              <div class="card" style="width:400px">
                  <div class="card-body">
                      <h4 class="card-title">${item.bottom} <br>${item.topping} <br> </h4>
                      <p class="card-text">${item.price} <br><br></p>
                      <button type="submit" name="delete" value="${loop.index}">Delete</button>
                  </div>
              </div>
          </div>
      </c:forEach>

          <h3>Samlet pris: ${sessionScope.totalPrice}</h3>
      <input type="submit" name="button" id="button" value="Betal" >
      </form>

  </jsp:body>

</t:pagetemplate>