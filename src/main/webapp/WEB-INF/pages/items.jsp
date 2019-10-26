<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>Marketplace</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="./favicon.ico" />
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.templates/beta1/jquery.tmpl.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>

        <link href="${contextPath}/css/bootstrap-theme.css" rel="stylesheet" type="text/css"/>
        <link href="${contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>

        <script src="${contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

        <script src="${contextPath}/js/show-items.js"></script>
        <link href="${contextPath}/css/style.css" rel="stylesheet">
        
        <script src="${contextPath}/js/jquery.sort-elements.js" type="text/javascript"></script>

        <%@include file="templates.html" %>

    </head>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="container">
            <div class="panel panel-default filterable">
                <div class="panel-heading">
                    <c:if test="${not empty user}">
                    <div class="pull-left">
                        <a href="/add-item" class="btn btn-success btn-sm btn-items" id="edit-item">SELL</a>
                        <a href="#" class="btn btn-success btn-sm btn-items" id="show-all-items">SHOW ALL ITEMS</a>
                        <a href="#" class="btn btn-success btn-sm btn-items" id="show-users-items">SHOW MY ITEMS</a>
                    </div>
                    </c:if>
                    <div class="pull-right">
                        <button class="btn btn-default btn-xs btn-filter"><span class="glyphicon glyphicon-filter"></span> Filter</button>
                    </div>
                </div>
                <div class="alert alert-danger alert-dismissable fade in" id="error" style="display:none;">
                    <a href="#" class="close" onClick="$('#error').hide()" aria-label="close">&times;</a>
                    <p></p>
                </div>
                <table class="table-responsive">
                    <thead>
                        <tr class="filters">
                            <th class="col-md-1"><input type="text" class="form-control" placeholder="UID" disabled></th>
                            <th class="col-md-1"><input type="text" class="form-control" placeholder="Title" disabled></th>
                            <th class="col-md-1"><input type="text" class="form-control" placeholder="Desciption" disabled></th>
                            <th><input type="text" class="form-control" placeholder="Seller" disabled></th>
                            <th class="col-md-1">Start price</th>
                            <th class="col-md-1">Bid inc</th>
                            <th class="col-md-2">Best offer</th>
                            <th><input type="text" class="form-control" placeholder="Bidder" disabled></th>
                            <th class="col-md-1">Stop Date</th>
                            <c:if test="${not empty user}">
                                <th class="col-md-2">Bidding</th>
                            </c:if>
                        </tr>
                    </thead>
                    <tbody id="items-body">
                    </tbody>
                </table>
            </div>
            <div class="modal fade" id="bidsModal" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Bids</h4>
                        </div>
                        <div class="modal-body">
                            <table class="table-responsive">
                                <thead>
                                    <tr>
                                        <th class="col-md-1">Bidder</th>
                                        <th class="col-md-1">Bid</th>
                                    </tr>
                                </thead>
                                <tbody id="bids-body">
                                </tbody>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>    
    </body>
</html>
