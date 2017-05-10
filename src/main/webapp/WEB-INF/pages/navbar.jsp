<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Marketplace</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <c:choose>
                <c:when test="${not empty user}">
                    <li><a>${user}</a></li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log-out</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="/registration"><span class="glyphicon glyphicon-user"></span> Registration</a></li>
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
