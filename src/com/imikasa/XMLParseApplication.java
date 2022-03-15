package com.imikasa;


import com.imikasa.utils.*;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLParseApplication {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        long start = System.currentTimeMillis();
        CreateUtils.create();           //创建XML文档的方法
        ParserUtil.parser();            //解析XML文档并插入数据库的方法
        System.out.println("总耗时："+(System.currentTimeMillis()-start)+"ms");
    }
}
