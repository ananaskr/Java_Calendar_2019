package com.ananaskr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.VelocityContext;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class TemplateRenderer {
    private VelocityEngine velocity; // private final VelocityEngine velocity;

    public String renderFragment(String fragment, Map<String,Object> contextParameters) {
        velocity = new VelocityEngine();
        velocity.init();
        VelocityContext context =  new VelocityContext(contextParameters);
        StringWriter tempWriter = new StringWriter(fragment.length());
        velocity.evaluate(context, tempWriter, "renderFragment", fragment);
        return tempWriter.toString();
    }

    public String render(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Map<String, Object> hm = new HashMap<String, Object>();
        hm.put("user", req.getParameter("user"));
        String template = req.getParameter("temp");
        String rendered = renderFragment(template,hm);
        res.getWriter().println(rendered);
        return "test"; // null
    }
}