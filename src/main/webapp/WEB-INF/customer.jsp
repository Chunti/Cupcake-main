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

        <c:forEach items="${sessionScope.customers}" var="item" varStatus="loop">

            <form method="post">
            <div class="container"  >
                <div class="card" style="width:400px">
                    <div class="card-body">
                            <h4 class="card-title">UserID: ${item.userId} <br>Name: ${item.name} <br> Customer Email: ${item.email}
                                <br>Number: ${item.phoneNumber} </h4>
                        <p class="card-text">Balance: ${item.balance}</p>
                        <label for="balance">Give ekstra balance: </label>
                        <br>
                        <input type="text" id="balance" name="balance"/>
                        <button type="submit" name="Button" value="${item.userId}">Give balance</button>                        <br><br>
                    </div>
                </div>
            </div>
            </form>
        </c:forEach>

    </jsp:body>

</t:pagetemplate>