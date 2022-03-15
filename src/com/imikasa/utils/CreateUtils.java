package com.imikasa.utils;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateUtils {
    public static void create() throws IOException {
        long start = System.currentTimeMillis();
        Document document = new Document();
        Element books = new Element("books");
        document.addContent(books);

        for (int i = 0; i < 100; i++) {
            books.addContent(new Element("book")
                    .setAttribute("id",i+"")
                    .addContent(new Element("name").setText("book"+i))
                    .addContent(new Element("price").setText(String.valueOf(i*10))));
        }

        Format rawFormat = Format.getRawFormat();
        XMLOutputter outputter = new XMLOutputter(rawFormat);
        outputter.output(document, new FileOutputStream("book4.xml"));
        System.out.println("创建XML文档耗时："+(System.currentTimeMillis()-start)+"ms");
    }
}
