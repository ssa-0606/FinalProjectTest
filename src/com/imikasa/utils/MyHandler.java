package com.imikasa.utils;

import com.imikasa.dao.DeleteTab;
import com.imikasa.dao.InsertTab;
import com.imikasa.pojo.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    List<Book> bookList = null;
    Book book = null;
    boolean isName = false;
    boolean isPrice = false;
    long start = 0;


    @Override
    public void startDocument() throws SAXException {
        System.out.println("开始读取文档...");
        this.start = System.currentTimeMillis();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("读取文档完毕...");
//        System.out.println(bookList);
        System.out.println("解析耗时："+(System.currentTimeMillis()-start)+"ms");
        try {
            DeleteTab.deleteTab();
            InsertTab.insert(bookList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("books")){
            bookList = new ArrayList<>();
        }else if(qName.equals("book")){
            book = new Book();
            String id = attributes.getValue("id");
            book.setId(Integer.parseInt(id));
        }else if(qName.equals("name")){
            this.isName = true;
        }else if(qName.equals("price")){
            this.isPrice = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("book")){
            this.bookList.add(this.book);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(isName){
            String name = new String(ch,start,length);
            book.setName(name);
            this.isName = false;
        }else if(isPrice){
            String price = new String(ch,start,length);
            book.setPrice(Double.parseDouble(price));
            this.isPrice = false;
        }
    }
}
