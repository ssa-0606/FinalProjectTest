package com.imikasa.utils;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ParserUtil {
    public static void parser() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newDefaultInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(new File("book4.xml"),new MyHandler());
    }
}
