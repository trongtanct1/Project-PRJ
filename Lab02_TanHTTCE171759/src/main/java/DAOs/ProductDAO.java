package DAOs;

import Models.Product;
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
public class ProductDAO {

    private Connection conn;

    public ProductDAO() throws ClassNotFoundException, SQLException {
        conn = DB.DbConnection.getConnection();
    }

    public ResultSet getAll() {
        ResultSet rs = null;
        String sql = "select * from Product";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public Product getLSV(String id) {
        Product p = null;
        String sql = "select * from Product where pro_id=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Product(rs.getString("pro_id"), rs.getString("pro_name"), rs.getInt("pro_quan"), rs.getLong("pro_price"), rs.getString("pro_pic"), rs.getString("pro_des"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public Product getProduct(String id) {
        Product p = null;
        String sql = "SELECT * FROM Product WHERE pro_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Product(rs.getString("pro_id"), rs.getString("pro_name"), rs.getInt("pro_quan"), rs.getLong("pro_price"), rs.getString("pro_pic"), rs.getString("pro_des"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    public int add(Product p) {
        int result = 0;
        String sql = "INSERT INTO Product VALUES(?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getPro_id());
            ps.setString(2, p.getPro_name());
            ps.setInt(3, p.getPro_quan());
            ps.setLong(4, p.getPro_price());
            ps.setString(5, "/" + p.getPro_pic());
            ps.setString(6, p.getPro_des());

            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int delete(String id) {
        int result = 0;
        String sql = "DELETE FROM Product WHERE pro_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public int update(Product p) {
        int result = 0;
        String sql = "UPDATE Product SET pro_name=?, pro_quan=?, pro_price=?, pro_pic=?, pro_des=? WHERE pro_id=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getPro_name());
            ps.setInt(2, p.getPro_quan());
            ps.setLong(3, p.getPro_price());
            ps.setString(4, "/" + p.getPro_pic());
            ps.setString(5, p.getPro_des());
            ps.setString(6, p.getPro_id());

            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    public int getTotalProductCount() {
        int count = 0;
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM Product");
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
