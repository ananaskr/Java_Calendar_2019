package com.ananaskr.day20;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.*;
import javax.servlet.http.*;
import java.io.*;

public class UserController extends HttpServlet {
    // This token is SHA-256(createTimestamp of admin user)
    private static final String api_token = "1c4e98fc43d0385e67cd6de8c32f969f371eba8ab84053858b5bfd21a2adb471";

    private static void executeCommand(String user_token, String[] cmd) {
        if (user_token.equals(api_token)) {
            // Execute shell command
        }
    }

    /**
    * Current attributes of objectClass "simpleSecurityObject":
    * createtimestamp, creatorsname, dn, entrycsn, entrydn, entryuuid, objectclass, userpassword, uuid
    */

    private static DirContext initLdap() throws NamingException {
        Hashtable<String, Object> env = new Hashtable<String, Object>();
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=admin,dc=example,dc=org");
        env.put(Context.SECURITY_CREDENTIALS, "admin");
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://127.0.0.1:389/");
        return new InitialDirContext(env);
    }

    private static boolean userExists(DirContext ctx, String username) throws Exception {
        String[] security_blacklist = {"uuid", "userpassword", "surname", "mail", "givenName", "name", "cn", "sn", "objectclass", "|", "&"};
        for (String name : security_blacklist) {
            if (username.contains(name)) {
                throw new Exception();
            }
        }

        String searchFilter = "(&(objectClass=simpleSecurityObject)(uid="+username+"))";
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> results = ctx.search("dc=example,dc=org", searchFilter, searchControls);
        if (results.hasMoreElements()) {
            SearchResult searchResult = (SearchResult) results.nextElement();
            return searchResult != null;
        }
        return false;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            DirContext ctx = initLdap();
            if(userExists(ctx, request.getParameter("username"))){
                response.getOutputStream().print("User is found.");
                response.getOutputStream().close();
            }
        } catch (Exception e) {
            response.sendRedirect("/");
        }
    }
}


