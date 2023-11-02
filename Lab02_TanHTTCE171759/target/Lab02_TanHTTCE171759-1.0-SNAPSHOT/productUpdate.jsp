<%-- 
    Document   : productUpdate
    Created on : Jun 27, 2023, 9:29:58 PM
    Author     : ACER
--%>

<%@page import="Models.Product"%>
<%@page import="Models.Product"%>
<%@page import="DAOs.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
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
                    <h1>Update Product</h1>
                    <form class="form-horizontal rounded-3 shadow-lg p-5 mb-5 bg-body rounded" action="servlet" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="form-label h4">Product ID:</label> 
                            <div><input class="form-control" type="text" name="ProID" value="<%= ((Product) session.getAttribute("prinfo")).getPro_id()%>"></div>                            
                        </div>

                        <div class="form-group">
                            <label class="form-label h4">Product Name:</label>
                            <div><input class="form-control" type="text" name="ProName" value="<%= ((Product) session.getAttribute("prinfo")).getPro_name()%>"></div>                
                            <div class="errorName text-danger" style="display: none;">Please enter Product Name</div> <br/>        
                        </div>

                        <div class="form-group">
                            <label class="form-label h4">Product Quantity:</label> 
                            <div><input class="form-control" type="text" name="ProQuantity" value="<%= ((Product) session.getAttribute("prinfo")).getPro_quan()%>"></div>
                            <div class="errorQuantity text-danger" style="display: none;">Please enter Product Quantity</div> <br/>                
                        </div>

                        <div class="form-group">
                            <label class="form-label h4">Product Price:</label> 
                            <div><input class="form-control" type="text" name="ProPrice" value="<%= ((Product) session.getAttribute("prinfo")).getPro_price()%>"></div>
                            <div class="errorPrice text-danger" style="display: none;">Please enter Product Price</div> <br/>                
                        </div>

                        <div class="form-group">
                            <label class="form-label h4">Product Image:</label> 
                            <div><input class="form-control" type="file" name="ProPic" value="<%= ((Product) session.getAttribute("prinfo")).getPro_pic()%>"></div>
                            <div class="errorPic text-danger" style="display: none;">Please upload an image for the product</div> <br/>                
                        </div>

                        <div class="form-group">
                            <label class="form-label h4">Product Description:</label> 
                            <div><textarea class="form-control" type="text" name="ProDescription"><%= ((Product) session.getAttribute("prinfo")).getPro_des()%></textarea></div>
                            <div class="errorDes text-danger" style="display: none;">Please enter Product Description(Maximum 1000 characters)</div> <br/>                
                        </div>
                        <button class="btn btn-success col-md-offset-2" type="submit" name="btnConfirm" value="update">Update</button>
                        
                    </form>
                </div>
            </div>
        </div>
                            
    </body>
</html>
