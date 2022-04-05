<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false"%>

<t:pagetemplate>
    <jsp:attribute name="header">
         Welcome to the frontpage
    </jsp:attribute>

    <jsp:attribute name="footer">
        Welcome to the frontpage
    </jsp:attribute>

    <jsp:body>

        <label for="bottom">Vælg bund</label>
        <select name="bottom" id="bottom">
            <c:forEach items="${applicationScope.bottoms}" var="items" varStatus="loop">
                <option value="${loop.index}">${items.name} (${items.price},-)</option>
            </c:forEach>


        </select>

        <label for="topping">Vælg topping</label>
        <select name="topping" id="topping">
            <c:forEach items="${applicationScope.topping}" var="items" varStatus="loop">
                <option value="${loop.index}">${items.name} (${items.price},-)</option>
            </c:forEach>


        </select>

    </jsp:body>

</t:pagetemplate>