/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.account;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class accountDAO {

    private Connection conn = null;

    public accountDAO() throws SQLException, ClassNotFoundException {
        conn = DB.DbConnection.getConnection();
    }

    public account getAccount(String userName) {
        account a = null;
        String sql = "SELECT * FROM account WHERE username=?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                a = new account(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("gender"), rs.getDate("birthday"), rs.getString("department"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }

    public account getFullName(String username) {
        account p = null;
        String sql = "select * from account where username=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new account(rs.getString("username"), rs.getString("password"), rs.getString("fullname"), rs.getString("gender"), rs.getDate("birthday"), rs.getString("department"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public boolean login(account tk) throws SQLException {
        String sql = "Select * from account where username=? and password=?";
        ResultSet rs = null;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tk.getUsername());
            ps.setString(2, getMd5(tk.getPassword()));
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs.next();
    }

    private String getMd5(String password) {

        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // input string converted to byte value array
            byte[] messageDigest = md.digest(password.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean checkUsernameExists(String username) throws SQLException {
    String sql = "Select * from account where username=?";
    ResultSet rs = null;

    try {
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        rs = ps.executeQuery();
    } catch (SQLException ex) {
        Logger.getLogger(accountDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rs.next();
}

}
