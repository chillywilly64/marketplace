<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${contextPath}/resources/favicon.ico" />
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
        
        <link href="${contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
        <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <script src="${contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>

        <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel">
                        <div class="panel-heading">
                            <p>Login</p>
                            <hr>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="login-form" action="/login" method="post" role="form" style="display: block;">
                                        <div class="alert alert-danger alert-dismissable fade in" ${(empty error) ? 'style="display:none;"' : ''}>
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            <strong>Error!</strong> ${error}
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="login" id="login" tabindex="1" class="form-control" placeholder="Login" required>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required>
                                        </div>
                                        <%--<div class="form-group text-center">
                                            <input type="checkbox" tabindex="3" name="remember" id="remember">
                                            <label for="remember">Remember Me</label>
                                        </div>--%>
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="login-submit" id="login-submit" tabindex="3" class="form-control btn btn-login" value="Log In">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="text-center">
                                                    <a href="/registration" tabindex="4" class="login-redirect">Register</a>
                                                    or
                                                    <a href="/" tabindex="4" class="login-redirect">Enter as guest</a>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>