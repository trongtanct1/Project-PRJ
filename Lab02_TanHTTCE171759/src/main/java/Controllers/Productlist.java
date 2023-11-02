/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.ProductDAO;
import DAOs.accountDAO;
import Models.Product;
import Models.account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Productlist extends HttpServlet {

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
            out.println("<title>Servlet Productlist</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Productlist at " + request.getContextPath() + "</h1>");
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
                if (url.endsWith("/prlist/ds")) {
                    session.setAttribute("user", adao.getAccount(((Cookie) session.getAttribute("cookie")).getValue()));
                    request.getRequestDispatcher("/listProduct.jsp").forward(request, response);

                } else if (url.endsWith("/prlist/add")) {
                    request.getRequestDispatcher("/productAdd.jsp").forward(request, response);
                } else if (url.startsWith("/prlist/delete")) {
                    String[] datas = url.split(("/"));
                    String id = datas[datas.length - 1];
                    ProductDAO pdao = new ProductDAO();
                    pdao.delete(id);

                    response.sendRedirect("/prlist/ds");
                } else if (url.startsWith("/prlist/update")) {
                    String[] datas = url.split(("/"));
                    String id = datas[datas.length - 1];
                    ProductDAO pdao = new ProductDAO();
                    Product p = pdao.getProduct(id);

                    if (p == null) {
                        response.sendRedirect("/prlist/ds");
                    } else {
                        session.setAttribute("prinfo", p);

                        request.getRequestDispatcher("/productUpdate.jsp").forward(request, response);
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
        if (request.getParameter("btnAddNew") != null && request.getParameter("btnAddNew").equals("add")) {
            response.sendRedirect("/prlist/add");
        }
        if (request.getParameter("btnConfirm") != null && request.getParameter("btnConfirm").equals("add")) {
            String id = request.getParameter("ProID");
            String name = request.getParameter("ProName");
            int quan = Integer.parseInt(request.getParameter("ProQuantity"));
            long price = Long.parseLong(request.getParameter("ProPrice"));
            Part part = request.getPart("ProPic");
            String des = request.getParameter("ProDescription");

            String realPath = request.getServletContext().getRealPath("/img");
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }

            part.write(realPath + "/" + fileName);

            Product p = new Product(id, name, quan, price, "img/" + fileName, des);
            ProductDAO pDao;
            try {
                pDao = new ProductDAO();
                int result = pDao.add(p);

                if (result == 0) {
                    response.sendRedirect("/prlist/add");
                } else {
                    response.sendRedirect("/prlist/ds");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Productlist.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Productlist.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (request.getParameter("btnConfirm") != null && request.getParameter("btnConfirm").equals("update")) {
            String id = request.getParameter("ProID");
            String name = request.getParameter("ProName");
            int quan = Integer.parseInt(request.getParameter("ProQuantity"));
            long price = Long.parseLong(request.getParameter("ProPrice"));
            Part part = request.getPart("ProPic");
            String des = request.getParameter("ProDescription");

            String realPath = request.getServletContext().getRealPath("/img");
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }

            part.write(realPath + "/" + fileName);

            Product p = new Product(id, name, quan, price, "img/" + fileName, des);
            ProductDAO pDao;
            try {
                pDao = new ProductDAO();
                int result = pDao.update(p);

                if (result == 0) {
                    response.sendRedirect("/prlist/update");
                } else {
                    response.sendRedirect("/prlist/ds");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Productlist.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Productlist.class.getName()).log(Level.SEVERE, null, ex);
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
