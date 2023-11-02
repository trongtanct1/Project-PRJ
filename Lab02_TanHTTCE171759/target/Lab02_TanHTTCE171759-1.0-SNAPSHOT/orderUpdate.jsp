<%-- 
    Document   : orderUpdate
    Created on : Jun 27, 2023, 9:30:21 PM
    Author     : ACER
--%>

<%@page import="java.sql.Date"%>
<%@page import="DAOs.OrderDAO"%>
<%@page import="Models.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Order</title>
        <script src="javascripts/jquery-3.7.0.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@500&display=swap" rel="stylesheet">
        <style>
            * {
                font-family: 'Nunito', sans-serif;
            }
        </style>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h1>Update Order</h1>
                    <form class="form-horizontal rounded-3 shadow-lg p-5 mb-5 bg-body rounded" action="<%= getServletContext().getContextPath()%>/olist/update" method="post">
                        <div class="form-group">
                            <label class="form-label h4">Order ID:</label> 
                            <div><input class="form-control" type="text" name="oID" value="<%= ((Order) session.getAttribute("orderuser")).getOrderID()%>"></div> <br/>                    
                        </div>
                        <div class="form-group">
                            <label class="form-label h4">Order Username:</label> 
                            <div><input class="form-control" type="text" name="oUserName" value="<%= ((Order) session.getAttribute("orderuser")).getUserName()%>"></div> <br/>                
                        </div>
                        <div class="form-group">
                            <label class="form-label h4">Order Total:</label> 
                            <div><input class="form-control" type="text" name="oTotal" value="<%= ((Order) session.getAttribute("orderuser")).getOrderTotal()%>"></div>
                            <div class="errorTotal text-danger" style="display: none;">Please enter the total number</div> <br/>
                        </div>
                        <div class="form-group">
                            <label class="form-label h4">Order Date:</label> 
                            <div><input class="form-control" type="date" name="oDate" value="<%= ((Order) session.getAttribute("orderuser")).getOrderDate()%>"></div>
                            <div class="errorDate text-danger" style="display: none;">Please choose order date</div> <br/>
                        </div>
                        <div class="form-group">
                            <label class="form-label h4">Order Description:</label> 
                            <div><textarea class="form-control" type="text" name="oDescription"> <%= ((Order) session.getAttribute("orderuser")).getOrderDes()%></textarea></div>
                            <div class="errorDes text-danger" style="display: none;">Please enter order description(Maximum 1000 characters)</div> <br/>
                        </div>`
                        <button type="submit" class="btn btn-success col-md-offset-2" name="btnUpdate" value="Update">Update</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
