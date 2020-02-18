package com.ananaskr.day8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.*;

public class GetPath extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        try {
            String icons = request.getParameter("icons");
            String filename = request.getParameter("filename");

            File f_icons = new File(icons);
            File f_filename = new File(filename);

            if (!icons.equals(f_icons.getName())) {
                throw new Exception("File not within target directory!");
            }

            if (!filename.equals(f_filename.getName())) {
                throw new Exception("File not within target directory!");
            }

            String toDir = "/var/myapp/data/" + f_icons.getName() + "/";
            File file = new File(toDir, filename);


            // Download file...
            PrintWriter out = response.getWriter();
            out.println("The File is: "+toDir+filename);
        } catch(Exception e) {
            response.sendRedirect("/");
        }
    }
}