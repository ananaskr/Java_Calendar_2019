package com.ananaskr.day13;

import javax.servlet.http.*;
import java.io.*;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class UploadFileController extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {


        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);

        String uploadPath = getServletContext().getRealPath("") + "upload";
        System.out.println(uploadPath);
        File uploadDir = new File(uploadPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            List<FileItem> items = upload.parseRequest(request);
            if (items != null && items.size() > 0) {
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        if (!(item.getContentType().equals("text/plain"))) {
                            throw new Exception("ContentType mismatch");
                        }
                        String file = uploadPath + File.separator + item.getName();
                        File storeFile = new File(file);
                        item.write(storeFile);
                    }
                }
            }
        } catch (Exception ex) {
            response.sendRedirect("/");
        }
    }
}