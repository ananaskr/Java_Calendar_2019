package com.ananaskr.day7;

import com.fasterxml.jackson.core.*;
import javax.servlet.http.*;
import java.io.*;

public class ApiCache extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        storeJson(request, "/tmp/getUserInformation.json");
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        loadJson();
    }

    public static void loadJson() throws IOException {
        // Deserialize to an HashMap object with Jackson's JsonParser and read the first 2 entries of the file.
        JsonFactory jsonobject = new JsonFactory();
        JsonParser parser = jsonobject.createParser(new File("/tmp/getUserInformation.json"));
        parser.nextToken();
        parser.nextToken();
        String field1 = parser.getValueAsString();
        parser.nextToken();
        String username = parser.getValueAsString();
        parser.nextToken();
        String field2 = parser.getValueAsString();
        parser.nextToken();
        String permission = parser.getValueAsString();
        System.out.println(field1+": "+username);
        System.out.println(field2+": "+permission);
    }

    public static void storeJson(HttpServletRequest request, String filename) throws IOException {
        JsonFactory jfactory = new JsonFactory();
        JsonGenerator jGenerator = jfactory.createGenerator(new File(filename), JsonEncoding.UTF8);
        jGenerator.writeStartObject();
        jGenerator.writeFieldName("username");
        jGenerator.writeRawValue("\"" + request.getParameter("username") + "\"");
        jGenerator.writeFieldName("permission");
        jGenerator.writeRawValue("\"none\"");
        jGenerator.writeEndObject();
        jGenerator.close();
    }
}
