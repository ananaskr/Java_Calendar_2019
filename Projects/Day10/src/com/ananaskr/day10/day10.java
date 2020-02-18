package com.ananaskr.day10;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class day10 {

    @RequestMapping("/webdav")
    public void webdav(HttpServletResponse res, @RequestParam("name") String name) throws IOException {
        res.setContentType("text/xml");
        res.setCharacterEncoding("UTF-8");
        PrintWriter pw = res.getWriter();
        name = name.replace("]]", "");
        pw.print("<person>");
        pw.print("<name><![CDATA[" + name.replace(" ","") + "]]></name>");
        pw.print("</person>");
    }
}
