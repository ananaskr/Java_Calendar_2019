package com.ananaskr.day19;

import javax.script.ScriptEngineManager;
import javax.servlet.http.*;
import javax.script.ScriptEngine;
import java.io.IOException;
import java.util.regex.*;

public class RenderExpression extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
            ScriptEngine scriptEngine = scriptEngineManager.getEngineByExtension("js");

            String dynamiceCodeHere = request.getParameter("p");
            if (!dynamiceCodeHere.startsWith("\"")) {
                throw new Exception();
            }


            Pattern p = Pattern.compile("([^\".()'\\/,a-zA-z\\s\\\\])|(processbuilder|file|url|runtime|getclass|forname|loadclass|new\\s)");
            Matcher m = p.matcher(dynamiceCodeHere.toLowerCase());
            if (m.find()) {
                throw new Exception();
            }
            scriptEngine.eval(dynamiceCodeHere);
        } catch(Exception e) {
            response.sendRedirect("/");
        }
    }
}