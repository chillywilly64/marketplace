<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Registration</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${contextPath}/favicon.ico" />

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>

        <link href="${contextPath}/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
        <link href="${contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

        <link href="${contextPath}/css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel">
                        <div class="panel-heading">
                            <p>Register</p>
                            <hr>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form:form id="register-form" method="post" modelAttribute="userForm" style="display:block;">
                                        
                                        <!--div class="form-group">
                                            <input type="text" name="full_name" id="full_name" tabindex="1" class="form-control" placeholder="Full name" required>
                                            <div class="alert alert-danger alert-dismissable fade in" ${(empty errors.fullName) ? 'style="display:none;"' : ''}>
                                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                                <strong>Error!</strong> ${errors.fullName}
                                            </div>
                                        </div-->
                                        <spring:bind path="name">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:input type="text" path="name" class="form-control" placeholder="Full name"
                                                            autofocus="true" required="true"/>
                                                <form:errors path="name"/>
                                            </div>
                                        </spring:bind>
                                        
                                        <!--div class="form-group">
                                            <input type="text" name="billing_address" id="billing_address" tabindex="2" class="form-control" placeholder="Billing address" required>
                                            <div class="alert alert-danger alert-dismissable fade in" ${(empty errors.billingaddress) ? 'style="display:none;"' : ''}>
                                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                                <strong>Error!</strong> ${errors.billingaddress}
                                            </div>
                                        </div-->
                                        <spring:bind path="address">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:input type="text" path="address" class="form-control" placeholder="Billing address" required="true"/>
                                                <form:errors path="address"/>
                                            </div>
                                        </spring:bind>
                                        
                                        <!--div class="form-group">
                                            <input type="text" name="login" id="login" tabindex="3" class="form-control" placeholder="Login" required>
                                            <div class="alert alert-danger alert-dismissable fade in" ${(empty errors.login) ? 'style="display:none;"' : ''}>
                                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                                <strong>Error!</strong> ${errors.login}
                                            </div>
                                        </div-->
                                        <spring:bind path="login">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:input type="text" path="login" class="form-control" placeholder="Login" pattern="[a-zA-Z0-9]+" required="true"/>
                                                <form:errors path="login"/>
                                            </div>
                                        </spring:bind>
                                        
                                        <!--div class="form-group">
                                            <input type="password" name="password" id="password" tabindex="4" class="form-control" placeholder="Password" required>
                                            <div class="alert alert-danger alert-dismissable fade in" ${(empty errors.password) ? 'style="display:none;"' : ''}>
                                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                                <strong>Error!</strong> ${errors.password}
                                            </div>
                                        </div-->
                                        <spring:bind path="password">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:input type="password" path="password" class="form-control" placeholder="Password" pattern="[a-zA-Z0-9]+" required="true"></form:input>
                                                <form:errors path="password"></form:errors>
                                            </div>
                                        </spring:bind>

                                        <!--div class="form-group">
                                            <input type="password" name="confirm_password" id="confirm_password" tabindex="5" class="form-control" placeholder="Confirm Password" onchange="confirm()" required>
                                            <div class="alert alert-danger alert-dismissable fade in" ${(empty errors.confirmPassword) ? 'style="display:none;"' : ''}>
                                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                                <strong>Error!</strong> ${errors.confirmPassword}
                                            </div>
                                        </div-->
                                        <spring:bind path="passwordConfirm">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:input type="password" path="passwordConfirm" class="form-control" placeholder="Password" pattern="[a-zA-Z0-9]+" required="true"></form:input>
                                                <form:errors path="passwordConfirm"></form:errors>
                                            </div>
                                        </spring:bind>
                                              
                                        <div class="row">
                                            <div class="col-sm-6 col-sm-offset-3">
                                                <button type="submit" name="register-submit" id="register-submit" class="form-control btn btn-register">Register Now</button>
                                            </div>
                                        </div>
                                        
                                    </form:form>
                                        
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-center">
                                                <a href="/login" class="login-redirect">Login</a>
                                                or
                                                <a href="/" class="login-redirect">Enter as guest</a>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
