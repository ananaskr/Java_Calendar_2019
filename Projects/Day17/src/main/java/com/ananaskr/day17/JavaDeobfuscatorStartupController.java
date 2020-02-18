package com.ananaskr.day17;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Pattern;
import javax.servlet.http.*;


public class JavaDeobfuscatorStartupController extends HttpServlet {
    private static boolean isInBlacklist(String input) {
        String[] blacklist = {"java","os","file"};
        return Arrays.asList(blacklist).contains(input);
    }

    private static void setEnv(String key, String value) {
        String[] values = key.split(Pattern.quote("."));
        if (isInBlacklist(values[0])) {
            return;
        }

        List<String> list = new ArrayList<>(Arrays.asList(values));
        list.removeAll(Arrays.asList("", null));
        String property = String.join(".", list);
        System.setProperty(property, value);

    }

    private static void loadEnv(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++)
            if (cookies[i].getName().equals("env")) {
                String[] tmp = cookies[i].getValue().split("@", 2);
                setEnv(tmp[0], tmp[1]);
            }
    }

    private static void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Secure file upload with arbitrary content type and extension in known path /var/myapp/data
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);

        String uploadPath = "/var/myapp/data";
        File uploadDir = new File(uploadPath);

        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        try{
            List<FileItem> items = upload.parseRequest(request);
            if (items != null && items.size() >0){
                for (FileItem item : items){
                    if(!item.isFormField()){
                        String name = item.getName();
                        String file = uploadPath+File.separator+name.replace("/","");
                        System.out.println(file);
                        File storeFile = new File(file);
                        item.write(storeFile);
                    }
                }
            }
        } catch (Exception ex){
            response.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        uploadFile(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loadEnv(request);
        try {
            final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            sysPathsField.set(null, null);
            System.loadLibrary("DEOBFUSCATION_LIB");
        } catch (Exception e) {
            response.sendRedirect("/");
        }
    }
}


