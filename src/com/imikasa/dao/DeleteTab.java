package com.imikasa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTab {
    public static void deleteTab() throws ClassNotFoundException, SQLException {
        long start = System.currentTimeMillis();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://imikasa.com:3309/jdbctest?serverTimezone=GMT%2B8","root","1234");
        Statement statement = connection.createStatement();
        String sql1 = "DROP TABLE IF EXISTS `book`";
        String sql2 = "CREATE TABLE book " +
                "(id INT not NULL, " +
                " name VARCHAR(255), " +
                " price VARCHAR(255), " +
                " PRIMARY KEY ( id ))";
        statement.addBatch(sql1);
        statement.addBatch(sql2);

        statement.executeBatch();
        statement.clearBatch();
        statement.close();
        connection.close();
        System.out.println("删表建表耗时："+(System.currentTimeMillis()-start)+"ms");
    }
}
