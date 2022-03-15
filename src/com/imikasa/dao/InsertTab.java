package com.imikasa.dao;


import com.imikasa.pojo.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class InsertTab {
    public static void insert(List<Book> bookList) throws ClassNotFoundException, SQLException {
        long start = System.currentTimeMillis();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://imikasa.com:3309/jdbctest?serverTimezone=GMT%2B8&useServerPrepStmts=false&rewriteBatchedStatements=true","root","1234");
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement("insert into book (id,name,price) values (?,?,?),(?,?,?),(?,?,?)");
        for (int i = 0; i < bookList.size()/3 ; i++) {
            for (int j = 0; j < 3; j++) {
                preparedStatement.setInt((3*j+1),bookList.get(3*i+j).getId());
                preparedStatement.setString((3*j+2),bookList.get(3*i+j).getName());
                preparedStatement.setString((3*j+3),String.valueOf(bookList.get(3*i+j).getPrice()));
            }
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();
        preparedStatement.close();
        connection.close();
        System.out.println("数据库插入耗时："+(System.currentTimeMillis()-start)+"ms");
        System.out.println("操作结束...");
    }
}
