package com.ananaskr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class day3 extends HttpServlet {

    public void init() throws ServletException{
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        TemplateRenderer temp = new TemplateRenderer();
        temp.render(req,res);
    }

    public void destroy(){

    }

}
