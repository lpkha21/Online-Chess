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

    public String getFriends(String username) throws SQLException {
        String sql = String.format("select friends_list from accounts where username = '%s'",username);
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(sql);
        String friends = "";
        if(rs.next()){
            friends = rs.getString("friends_list");
        }
        return friends;
    }

    public String getRequests(String username) throws SQLException {
        String sql = String.format("select requests from accounts where username = '%s'",username);
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(sql);
        String requests = "";
        if(rs.next()){
            requests = rs.getString("requests");
        }
        return requests;
    }

    public int addRequest(String name1,String name2) throws SQLException {
        int ans = -1;
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = String.format("select friends_list from accounts where username = '%s'",name1);
        ResultSet rs = stmt.executeQuery(sql);
        String friends = "";
        if(rs.next()){
            friends = rs.getString("friends_list");
        }
        if(friends != null){
            String[] fr = friends.split(",");
            for(int i = 0; i < fr.length; i++){
                if(name2.equals(fr[i])){
                   return 1;
                }
            }
        }
        sql = String.format("select requests from accounts where username = '%s'",name1);
        rs = stmt.executeQuery(sql);
        String requests = "";
        if(rs.next()){
            requests = rs.getString("requests");
        }
        if(requests != null){
            String[] r = requests.split(",");
            for(int i = 0; i < r.length; i++){
                if(name2.equals(r[i])){
                    return -1;
                }
            }
        }
        if(requests == null){
            requests += name2;
        }else{
            requests += "," + name2;
        }
        sql = String.format("update accounts set requests = '%s' where username = '%s'",requests,name1);
        stmt.executeUpdate(sql);
        return 0;
    }

}
