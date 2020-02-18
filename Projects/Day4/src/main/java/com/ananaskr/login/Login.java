package com.ananaskr.login;

import javax.servlet.http.*;
import java.io.IOException;

public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String url = request.getParameter("url");
        //only relative urls are allowed!
        if (url.startsWith("/")) {
            response.sendRedirect(url);
        }
    }
}