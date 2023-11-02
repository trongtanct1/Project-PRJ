/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class OrderDAO {

    private Connection conn = null;

    public OrderDAO() throws SQLException, ClassNotFoundException {
        conn = DB.DbConnection.getConnection();
    }

    public ResultSet getAll() {
        ResultSet rs = null;
        String sql = "SELECT * FROM [Order]";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public ResultSet getAllFromUserName(String userName) {
        ResultSet rs = null;
        String sql = "SELECT * FROM [Order] WHERE username=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);

            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public Order getOrder(String id) {
        Order o = null;
        String sql = "SELECT * FROM [Order] WHERE order_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                o = new Order(id, rs.getString("username"), rs.getLong("order_total"), rs.getDate("order_date"), rs.getString("order_des"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return o;
    }

    public int add(Order o) {
        int result = 0;
        String sql = "INSERT INTO [Order] VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, o.getOrderID());
            ps.setString(2, o.getUserName());
            ps.setLong(3, o.getOrderTotal());
            ps.setDate(4, o.getOrderDate());
            ps.setString(5, o.getOrderDes());

            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public int delete(String id) {
        int result = 0;
        String sql = "DELETE FROM [Order] WHERE order_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public int update(Order o) {
        int result = 0;
        String sql = "UPDATE [Order] SET order_total=?, order_date=?, order_des=? WHERE order_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, o.getOrderTotal());
            ps.setDate(2, o.getOrderDate());
            ps.setString(3, o.getOrderDes());
            ps.setString(4, o.getOrderID());

            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    public int getTotalOrderCount() {
        int count = 0;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM [Order]");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    } 
}
