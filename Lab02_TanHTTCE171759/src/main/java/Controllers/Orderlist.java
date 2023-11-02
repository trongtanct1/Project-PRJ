/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.OrderDAO;
import DAOs.accountDAO;
import Models.Order;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Orderlist extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Orderlist</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Orderlist at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = request.getRequestURI();
        HttpSession session = request.getSession();

        try {
            accountDAO adao = new accountDAO();
            if (((Cookie) session.getAttribute("cookie")).getValue().equals("")) {
                response.sendRedirect("/Login");

            } else {
                if (url.endsWith("/olist/ds")) {
                    session.setAttribute("user", adao.getAccount(((Cookie) session.getAttribute("cookie")).getValue()));
                    request.getRequestDispatcher("/listOrder.jsp").forward(request, response);

                } else if (url.endsWith("/olist/add")) {
                    request.getRequestDispatcher("/orderAdd.jsp").forward(request, response);
                } else if (url.startsWith("/olist/delete")) {
                    String[] datas = url.split("/");
                    String id = datas[datas.length - 1];
                    OrderDAO odao = new OrderDAO();
                    odao.delete(id);
                    response.sendRedirect("/olist/ds");
                } else if (url.startsWith("/olist/update")) {
                    String[] datas = url.split("/");
                    String id = datas[datas.length - 1];
                    OrderDAO odao = new OrderDAO();
                    Order o = odao.getOrder(id);

                    if (o == null) {
                        response.sendRedirect("/olist/ds");
                    } else {
                        session.setAttribute("orderuser", o);

                        request.getRequestDispatcher("/orderUpdate.jsp").forward(request, response);
                    }
                }
            }
        } catch (Exception e) {
            response.sendRedirect("/Login");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btnAddNew") != null && request.getParameter("btnAddNew").equals("AddNew")) {
            response.sendRedirect("/olist/add");
        }
        if (request.getParameter("orderSubmit") != null && request.getParameter("orderSubmit").equals("orderAddNew")) {
            String id = request.getParameter("oID");
            String username = request.getParameter("oUserName");
            long total = Long.parseLong(request.getParameter("oTotal"));
            Date date = Date.valueOf(request.getParameter("oDate"));
            String des = request.getParameter("oDescription");

            Order o = new Order(id, username, total, date, des);
            OrderDAO odao;
            try {
                odao = new OrderDAO();
                int result = odao.add(o);

                if (result == 0) {
                    response.sendRedirect("/olist/add");
                } else {
                    response.sendRedirect("/olist/ds");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Orderlist.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Orderlist.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
 if (request.getParameter("btnUpdate") != null && request.getParameter("btnUpdate").equals("Update")) {
            String id = request.getParameter("oID");
            String username = request.getParameter("oUserName");
            long total = Long.parseLong(request.getParameter("oTotal"));
            Date date = Date.valueOf(request.getParameter("oDate"));
            String des = request.getParameter("oDescription");

            Order o = new Order(id, username, total, date, des);
            OrderDAO odao;
            try {
                odao = new OrderDAO();
                int result = odao.update(o);

                if (result == 0) {
                    response.sendRedirect("/olist/update");
                } else {
                    response.sendRedirect("/olist/ds");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Orderlist.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Orderlist.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
