<%-- 
    Document   : listOrder
    Created on : 23-Jun-2023, 13:29:49
    Author     : ACER
--%>

<%@page import="Models.account"%>
<%@page import="DAOs.accountDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order List</title>
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
        <div class="container-fluid">            
            <div class="row" style="background-color: #333333">
                <div class="text-end mt-3 mb-2">
                    <div class="text-warning h2">
                        <i class="fa-solid fa-circle-user text-light"></i> Hello, <%= ((account) session.getAttribute("user")).getFullname()%>
                        <a class="h4" href="/logout" style="text-decoration: none;">(Sign out <i class="fa-solid fa-right-from-bracket"></i>)</a>
                        <%
                            OrderDAO odao = new OrderDAO();
                            ResultSet rs = odao.getAll();
                            int totalCount = odao.getTotalOrderCount();
                        %>
                        <div>
                            <h3>Total Orders: <%= totalCount%></h3>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
                    <div class="container-fluid">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link" href="/prlist/ds">Product List</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link  active" href="/olist/ds">Order List</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div> 
            <div class="row text-info bg-secondary mb-3"><h1>Order List</h1></div>
            <table id="example">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Username</th>
                        <th>Total (VNÐ)</th>
                        <th>Date</th>
                        <th>Description</th>            
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        while (rs.next()) {
                    %>

                    <tr>
                        <td><%= rs.getString("order_id")%></td>
                        <td><%= rs.getString("username")%></td>
                        <td><%= rs.getInt("order_total")%></td>
                        <td><%= rs.getDate("order_date")%></td>
                        <td><%= rs.getString("order_des")%></td>       
                        <td><a onclick="return confirm('Delete this order?')" href="/olist/delete/<%= rs.getString("order_id")%>"><i class="fa-solid fa-trash-can" style="color: #000000; font-size: 200%;"></i></a></td>
                        <td><a href="/olist/update/<%= rs.getString("order_id")%>"><i class="fa-solid fa-file-pen" style="color: #000000; font-size: 200%;"></i></a></td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <form method="post" action="<%= getServletContext().getContextPath()%>/olist/add">
                <button class="btn btn-warning text-light mb-3 shadow-lg p-3 mb-5 rounded" type="submit" name="btnAddNew" value="AddNew"><i class="fa-solid fa-circle-plus"></i>Add new Order</button>
            </form>
        </div>


    </body>
</html>
