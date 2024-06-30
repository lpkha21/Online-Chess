package javaClasses;

import java.sql.*;

public class DataBase {

    private static final String server = MyDbInfo.MYSQL_DATABASE_SERVER;
    private static final String database = MyDbInfo.MYSQL_DATABASE_NAME;
    private static final String username = MyDbInfo.MYSQL_USERNAME;
    private static final String password = MyDbInfo.MYSQL_PASSWORD;

    private Connection conn;

    public DataBase() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn =  DriverManager.getConnection("jdbc:mysql://" + server,username,password);
    }
    public boolean exists(String username) throws SQLException {
        String sql = "SELECT * FROM accounts";
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString("username");
            if(username.equals(name))return true;
        }
        return false;
    }

    public boolean createAccount(String username, String password) throws SQLException {
        if(exists(username)){
            return false;
        }
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        if(!username.isEmpty() && !password.isEmpty()){
            stmt.executeUpdate(String.format("insert into accounts (username, password, friends_list) values ('%s','%s','%s')", username, password, ""));
        }else{
            return false;
        }
        return true;
    }

    public boolean validLogin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM accounts";
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString("username");
            String pw = rs.getString("password");
            if(username.equals(name) && password.equals(pw))return true;
        }
        return false;
    }
}
