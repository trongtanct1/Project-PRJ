<%-- 
    Document   : index
    Created on : Jun 27, 2023, 9:20:37 PM
    Author     : ACER
--%>

<%@page import="DAOs.accountDAO"%>
<%@page import="Models.account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
        <style>
            /* ... (css code) ... */
            /* Thêm luật CSS để định dạng màu chữ cho thông báo lỗi */
            .error-msg {
                color: red;
            }
        </style>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <h1>Login Page</h1>
                <form class="form-horizontal rounded-3 shadow-lg p-5 mb-5 bg-body rounded" method="post">
                    <div class="form-group">
                        <label class="form-label h4">Username</label> 
                        <input type="text" id="usrname" name="username" required>
                    </div>
                    <div class="form-group">
                        <label class="form-label h4">Password</label> 
                        <input type="password" name="userpass" id="psw" required>
                        <%
                            String error = request.getParameter("error");
                            if ("invalid".equals(error)) {
                        %>
                        <p class="error-msg">Username does not exist</p>
                        <%
                        } else if ("password".equals(error)) {
                        %>
                        <p class="error-msg">Invalid Username or Password</p>
                        <%
                            }
                        %>
                    </div>
                    <button class="btn btn-success col-md-offset-2" type="submit" id="submit-btn" value="Submit" name="btnSubmit">Login</button>
                    <button type="reset" class="btn btn-success col-md-offset-2">Cancel</button>
                </form> 
            </div>
            <div class="col-md-3"></div>
        </div>


        <%
            Cookie[] don = request.getCookies();
            if (don != null) {
                for (Cookie po : don) {
                    if (po.getName().equals("admin")) {
                        response.sendRedirect("listProduct.jsp");
                        break;
                    }
                }
            }

        %>

        <script>
            $(document).ready(function () {
                $("#submit-btn").click(function (e) {
                    var username = $("#usrname").val();
                    var password = $("#psw").val();

                    // Remove any old error messages
                    $(".error-msg").remove();

                    // Check if either "Username" or "Password" is empty and display error messages
                    if (username === "" || password === "") {
                        e.preventDefault(); // Prevent form submission
                        if (username === "") {
                            $("#usrname").after('<p class="error-msg">Username is required</p>');
                        }
                        if (password === "") {
                            $("#psw").after('<p class="error-msg">Password is required</p>');
                        }
                    }
                });

                // Existing code for user interactions
            });
        </script>


    </body>
</html>
