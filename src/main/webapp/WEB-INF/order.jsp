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

            <c:forEach items="${sessionScope.orders}" var="item" varStatus="loop">

                <div class="container"  >
                    <div class="card" style="width:400px">
                        <div class="card-body">
                            <c:if test="${sessionScope.user.role == 1 }">
                            <h4 class="card-title">Order number: ${item.orderId} <br>Date: ${item.date} <br> Customer number: ${item.userId} </h4>
                            </c:if>
                            <c:if test="${sessionScope.user.role == 0 }">
                                <h4 class="card-title">Order number: ${item.orderId} <br>Date: ${item.date} <br> </h4>
                            </c:if>

                            <p class="card-text">Total price: ${item.totalPrice}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>

    </jsp:body>

</t:pagetemplate>