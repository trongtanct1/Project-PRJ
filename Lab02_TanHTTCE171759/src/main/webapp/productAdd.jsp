<%-- 
    Document   : productAdd
    Created on : Jun 27, 2023, 9:29:47 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Product</title>
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
        <script>
    $(document).ready(function() {
        $("form").submit(function(event) {
            var isValid = true;

            // Kiểm tra Product ID
            if (!$("input[name='ProID']").val()) {
                $(".errorIDb").show();
                isValid = false;
            } else {
                $(".errorIDb").hide();
            }

            // Kiểm tra Product Name
            if (!$("input[name='ProName']").val()) {
                $(".errorName").show();
                isValid = false;
            } else {
                $(".errorName").hide();
            }

            // Kiểm tra Product Quantity
            var quantity = $("input[name='ProQuantity']").val();
            if (!quantity || parseInt(quantity) < 0) {
                $(".errorQuantity").show();
                isValid = false;
            } else {
                $(".errorQuantity").hide();
            }

            // Kiểm tra Product Price
            var price = $("input[name='ProPrice']").val();
            if (!price || parseFloat(price) < 0) {
                $(".errorPrice").show();
                isValid = false;
            } else {
                $(".errorPrice").hide();
            }

            // Kiểm tra Product Image
            if ($("input[name='ProPic']").get(0).files.length === 0) {
                $(".errorPic").show();
                isValid = false;
            } else {
                $(".errorPic").hide();
            }

            // Nếu có lỗi, ngăn không cho form gửi
            if (!isValid) {
                event.preventDefault();
            }
        });
    });
</script>

    </head>
    <body>        
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h1>Add new Product</h1>
                <form class="form-horizontal rounded-3 shadow-lg p-5 mb-5 bg-body rounded" action="<%= getServletContext().getContextPath()%>/prlist/add" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="form-label h4">Product ID:</label> 
                        <div><input class="form-control" type="text" name="ProID"></div>
                        <div class="errorIDb text-danger" style="display: none;">Please enter Product ID(Maximum 50 characters)</div> <br/>                
                    </div>
                    <div class="form-group">  
                        <label class="form-label h4">Product Name:</label>
                        <div><input class="form-control" type="text" name="ProName"></div>
                        <div class="errorName text-danger" style="display: none;">Please enter Product Name(Maximum 100 characters)</div> <br/>                
                    </div>

                    <div class="form-group">
                        <label class="form-label h4">Product Quantity:</label> 
                        <div><input class="form-control" type="text" name="ProQuantity"></div>
                        <div class="errorQuantity text-danger" style="display: none;">Please enter Product Quantity</div> <br/>                
                    </div>

                    <div class="form-group">
                        <label class="form-label h4">Product Price:</label> 
                        <div><input class="form-control" type="text" name="ProPrice"></div>
                        <div class="errorPrice text-danger" style="display: none;">Please enter Product Price</div> <br/>                
                    </div>

                    <div class="form-group">
                        <label class="form-label h4">Product Image:</label> 
                        <div><input class="form-control" type="file" name="ProPic"></div>
                        <div class="errorPic text-danger" >Please upload an image for the product</div> <br/>                
                    </div>

                    <div class="form-group">
                        <label class="form-label h4">Product Description:</label> 
                        <div><textarea class="form-control" type="text" name="ProDescription"></textarea>
                            <div class="errorDes text-danger" style="display: none;">Please enter Product Description(Maximum 1000 characters)</div> <br/>                
                        </div>
                        <button class="btn btn-success col-md-offset-2" type="submit" name="btnConfirm" value="add">Add New</button>
                    </div>
                </form>
            </div>
            <div class="col-md-3"></div>
        </div>
   

</body>
</html>
