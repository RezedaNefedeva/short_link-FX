package org.rezeda.diplomfx;

import java.sql.*;

public class DB {
    private String HOST = "localhost";
    private String PORT = "3306";
    private String DB_NAME = "short_links";
    private String LOGIN = "root";
    private String PASS = "root";

    private Connection dbConn = null;

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String str = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(str, LOGIN, PASS);

        return dbConn;
    }

    public void isConnected() throws ClassNotFoundException, SQLException {
        dbConn = getDbConnection();
        System.out.println(dbConn.isValid(1000));
    }

    public void addLink(String text_link, String small_link) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO `short_link` (`link`, `small_link`) VALUES (?, ?)";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, text_link);
        prSt.setString(2, small_link);
        prSt.executeUpdate();
    }

    public boolean isExistSmallLink(String small_link) throws SQLException, ClassNotFoundException {
        String sql = "SELECT `id` FROM `short_link` WHERE `small_link` = ?";
        PreparedStatement prSt = getDbConnection().prepareStatement(sql);
        prSt.setString(1, small_link);
        ResultSet res = prSt.executeQuery();
        return res.next();
    }

    public ResultSet getLink() throws SQLException, ClassNotFoundException {
        String sql = "SELECT `small_link` FROM `short_link`";
        Statement statement = getDbConnection().createStatement();
        return statement.executeQuery(sql);
    }
}