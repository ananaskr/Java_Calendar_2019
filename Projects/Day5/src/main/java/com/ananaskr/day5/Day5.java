package com.ananaskr.day5;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Day5 extends HttpServlet {
    public void init() throws ServletException {
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Request request = new Request();
        request.toString(req);
    }
    public void destroy(){

    }

}
