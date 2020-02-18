package com.ananaskr.day9;

import java.util.regex.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;

public class Validator extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        long startTime = System.currentTimeMillis();
        if (isInWhiteList(request.getParameter("whitelist"), request.getParameter("value"))) {
            out.print("Value is in whitelist.");
            System.out.println("This takes " + (System.currentTimeMillis() -
                    startTime));
        } else {
            out.print("Value is not in whitelist.");
            System.out.println("This takes " + (System.currentTimeMillis() -
                    startTime));
        }
        out.flush();
    }

    public static boolean isInWhiteList(String whitelist, String value) {
        Pattern pattern = Pattern.compile("^(" + whitelist + ")+");
        Matcher matcher = pattern.matcher(value);
        System.out.println("The length of the value is: "+ value.length());
        return matcher.matches();
    }
}