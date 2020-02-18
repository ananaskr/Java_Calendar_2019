package com.ananaskr.day1;

import org.jdom2.JDOMException;
import java.io.IOException;
public class TestDay1 {
    public static void main(String[] args) throws IOException, JDOMException {
        ImportDocument test = new ImportDocument();
        System.out.println(test.extractString());
    }
}
