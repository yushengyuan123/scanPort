package com.web.dao;


import java.sql.*;

public class connectMysql {
    private static Connection con;
    private static String dbUrl ="jdbc:mysql://localhost:3306/portscan?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    private static String dbUserName ="root";
    private static String dbPassword ="ysy2266833ysy??";
    private static String jdbcName ="com.mysql.cj.jdbc.Driver";

    public static Connection connect() throws ClassNotFoundException, SQLException {
        if (con == null) {
            Class.forName(jdbcName);
            return DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        }
        return con;
    }

    public static void create(String sql) {
        try {
            Connection con = connectMysql.connect();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet select(String sql) {
        try {
            Connection con = connectMysql.connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static void insert(String sql) {
        try {
            Connection con = connectMysql.connect();
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(sql);
            if(rs > 0){
                System.out.println("数据录入成功");
            }
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void update(String sql) throws SQLException, ClassNotFoundException {
        Connection con = connectMysql.connect();
        Statement stmt = con.createStatement();
        stmt.executeUpdate(sql);
        stmt.close() ;
    }

    public void closeCon(Connection con)throws Exception{
        if(con!=null) {
            con.close();
        }
    }
}
