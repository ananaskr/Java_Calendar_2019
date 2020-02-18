package com.ananaskr.day5;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class Request {
    public static String toString(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        String delimiter = req.getParameter("delim");
        Enumeration<String> names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            if (!name.equals("delim")) {
                sb.append("<b>" + name + "</b>:<br>");
                String[] values = req.getParameterValues(name);
                for (String val : values) {
                    sb.append(val);
                    sb.append(delimiter);
                    sb.append("<br>");
                }
            }
        }
        long heapFreeSize = Runtime.getRuntime().freeMemory();
        System.out.println(heapFreeSize);
        return sb.toString();
    }
}