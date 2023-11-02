<%-- 
    Document   : listProduct
    Created on : Jun 27, 2023, 12:37:40 AM
    Author     : ACER
--%>

<%@page import="Models.account"%>
<%@page import="DAOs.accountDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@500&display=swap" rel="stylesheet">
        <style>
            * {
                font-family: 'Nunito', sans-serif;
            }
        </style>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>   
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
    </head>
    <body>
        <%
            accountDAO adao = new accountDAO();
        %>
        <div class="container-fluid">            
            <div class="row" style="background-color: #333333">
                <div class="text-end mt-3 mb-2">
                    <div class="text-warning h2">
                        <i class="fa-solid fa-circle-user text-light"></i> Hello, <%= ((account) session.getAttribute("user")).getFullname()%>
                        <a class="h4" href="/logout" style="text-decoration: none;">(Sign out <i class="fa-solid fa-right-from-bracket"></i>)</a>
                        <%
                            ProductDAO dao = new ProductDAO();
                            int totalCount = dao.getTotalProductCount();
                        %>
                        <div>
                            <h3>Total Products: <%= totalCount%></h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                    <div class="container-fluid">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link active" href="/prlist/ds">Product List</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/olist/ds">Order List</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div> 
            <div class="row text-info bg-secondary mb-3"><h1>Product List</h1></div>
            <table id="example">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price (VNÐ)</th>
                        <th>Image</th>
                        <th>Description</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>

                    <%

                        ResultSet rs = dao.getAll();
                        while (rs.next()) {
                            String pro_id = rs.getString("pro_id");
                            String pro_name = rs.getString("pro_name");
                            int pro_quan = rs.getInt("pro_quan");
                            double pro_price = rs.getDouble("pro_price");
                            String pro_pic = rs.getString("pro_pic");
                            String pro_des = rs.getString("pro_des");
                    %>
                    <tr>
                        <td><%= rs.getString("pro_id")%></td>
                        <td><%= rs.getString("pro_name")%></td>
                        <td><%= rs.getInt("pro_quan")%></td>
                        <td><%= rs.getLong("pro_price")%></td>
                        <td><img src="<%= rs.getString("pro_pic")%>" width="200px" /> </td>
                        <td><%= rs.getString("pro_des")%></td>
                        <td><a onclick="return confirm('Delete this product?')" href="/prlist/delete/<%= rs.getString("pro_id")%>"><i class="fa-solid fa-trash-can" style="color: #000000; font-size: 200%;"></i></a></td>
                        <td><a href="/prlist/update/<%= rs.getString("pro_id")%>"><i class="fa-solid fa-file-pen" style="color: #000000; font-size: 200%;"></i></a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

            <form method="post" action="<%= getServletContext().getContextPath()%>/prlist/add">
                <button class="btn btn-warning text-light mb-3 shadow-lg p-3 mb-5 rounded" type="submit" name="btnAddNew" value="add"> <i class="fa-solid fa-circle-plus"></i>Add new Product</button>
            </form>




    </body>
</html>
