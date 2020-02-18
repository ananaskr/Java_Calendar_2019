package com.ananaskr.day6;

import java.io.*;
import java.nio.file.*;
import javax.servlet.http.*;

public class ReadFile extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        try {
            String url = request.getParameter("url");
            String data = new String(Files.readAllBytes(Paths.get(url)));
        } catch (IOException e) {
            PrintWriter out = response.getWriter();
            out.print("File not found");
            out.flush();
        }
        //proceed with code
    }
}