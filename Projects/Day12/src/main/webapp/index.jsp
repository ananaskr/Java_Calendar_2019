<%@ page import="org.owasp.esapi.ESAPI" %>
<%! String customClass = "default"; %>
<html><body>

<%@ include file="init.jsp" %>

<div class="<%= customClass %>">
    <%! String username; %>
    <% username = request.getParameter("username"); %>
    Welcome citizen, you have been identified as
    <%
        customClass = request.getParameter("customClass");
        customClass = ESAPI.encoder().encodeForHTML(customClass);
    %>
    <div class="<%= customClass %>">
        <%= ESAPI.encoder().encodeForHTML(username) %>.
    </div></div></body></html>
