<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <form action="User" method="post">
            <h2>Create user</h2><br>
            <h4>Insert your data</h4><br>
            <label for="name">Full name: </label>
            <br>
            <input type="text" id="name" name="name"/>
            <br><br>
            <label for="email">Email: </label>
            <br>
            <input type="text" id="email" name="email"/>
            <br><br>

            <label for="phone">Phone number: </label>
            <br>
            <input type="text" id="phone" name="phone"/>
            <br><br>
            <label for="balance">Balance: </label>
            <br>
            <input type="text" id="balance" name="balance"/>
            <br><br>

            <label for="password">Password: </label>
            <br>
            <input type="password" id="password" name="password"/>
            <br><br>
            <input type="submit"  value="Create user"/>
        </form>

    </jsp:body>
</t:pagetemplate>