package water.ustc.useMysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LoginMysql {
    
    Database db = new Database();

    public List<List<String>> select(String username, String password) {

        String sql = "select * from sc where username = ? and password = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<List<String>> listlist = new ArrayList();
        List<String> list = new ArrayList();
        try {
            Class.forName(db.driver);
            conn = DriverManager.getConnection(db.url, db.userName, db.passWord);
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
                list.add(rs.getString(2));
                listlist.add(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listlist;
    }

    public void insert(String username, String password) {

        String sql = "insert into sc(username,password) values(?,?)";

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName(db.driver);
            conn = DriverManager.getConnection(db.url, db.userName, db.passWord);
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}