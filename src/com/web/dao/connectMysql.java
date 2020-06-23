package com.web.dao;


import java.sql.*;

public class connectMysql {
    private  static String dbUrl ="jdbc:mysql://localhost:3306/portscan?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    private  static String dbUserName ="root";
    private  static String dbPassword ="ysy2266833ysy??";
    private  static String jdbcName ="com.mysql.cj.jdbc.Driver";


//    //数据库连接池子，假如已经连接那么就不在链接
    public static Connection connect() throws ClassNotFoundException, SQLException {
        Connection con;
        Class.forName(jdbcName);
        con = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
        return con;
    }

    public static void create(String sql, Connection con) {
        try {
//            Connection con = connectMysql.connect();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet select(String sql, Connection con) {
        try {
//            Connection con = connectMysql.connect();
            Statement stmt = con.createStatement();
            return stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int insert(String sql, Connection con) {
        int id = -1;
        try {
//            Connection con = connectMysql.connect();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                System.out.println ("生成记录的key为 ：" + id);
            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void insertWithKey(String sql, Connection con) {
        try {
//            Connection con = connectMysql.connect();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void update(String sql, Connection con) throws SQLException, ClassNotFoundException {
//        Connection con = connectMysql.connect();
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
