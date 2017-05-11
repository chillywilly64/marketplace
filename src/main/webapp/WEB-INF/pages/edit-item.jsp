<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit item</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${contextPath}/resources/favicon.ico" />
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
        
        <link href="${contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
        <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        
        <script src="${contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
        
        <script src="${contextPath}/resources/js/edit-item.js"></script>
        <link href="${contextPath}/resources/css/style.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel panel">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-md-6 col-md-offset-3">
                                    <p class="label">Edit item</p>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                   
                                
                                    <form:form id="edit-item-form" method="post" modelAttribute="itemForm" style="display:block;">
                                        
                                        <spring:bind path="title">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:input type="text" path="title" class="form-control" placeholder="Title"
                                                            autofocus="true" required="true"/>
                                                <form:errors path="title"/>
                                            </div>
                                        </spring:bind>
                                        
                                        <spring:bind path="description">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:textarea type="text" path="description" class="form-control" placeholder="Description"/>
                                                <form:errors path="description"/>
                                            </div>
                                        </spring:bind>
                                        
                                        <spring:bind path="startPrice">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:input type="number" min="0" path="startPrice" class="form-control" placeholder="Start price" readonly="${(not empty itemForm.bestOffer && itemForm.bestOffer != 0) ? 'true' : 'false'}" required="true"/>
                                                <form:errors path="startPrice"/>
                                            </div>
                                        </spring:bind>
                                        
                                        <spring:bind path="timeLeft">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:input type="number" min="0" path="timeLeft" id="time_left" class="form-control" placeholder="Time left" readonly="${(not empty itemForm.bestOffer && itemForm.bestOffer != 0) ? 'true' : 'false'}" required="true"></form:input>
                                                <form:errors path="timeLeft"/>
                                            </div>
                                        </spring:bind>

                                        <spring:bind path="bidInc">
                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                <form:input type="number" path="bidInc" id="bid_inc" class="form-control" placeholder="Bid increment" readonly="${(not empty itemForm.bestOffer && itemForm.bestOffer != 0) ? 'true' : 'false'}" required="true"></form:input>
                                                <form:errors path="bidInc"/>
                                            </div>
                                        </spring:bind>
                                              
                                        <spring:bind path="buyItNow">
                                            <form:checkbox path="buyItNow" disable="${(not empty bestOffer && bestOffer != 0) ? 'true' : 'false'}" id="buy_it_now" name="buy_it_now" value="gfkgffk"/>
                                            <label for="buy_it_now">Buy it now</label>                                          
                                        </spring:bind>
                                        
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" name="item-submit" id="item-submit" class="form-control btn btn-submit-item" value="Publish/Save">
                                                </div>
                                            </div>
                                        </div>
                                        
                                    </form:form>
                                </div>
                            </div> 
                        </div>            
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>