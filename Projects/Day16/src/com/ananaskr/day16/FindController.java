package com.ananaskr.day16;

import org.hibernate.*;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class FindController {

    public String escapeQuotes(String in){
        return in.replaceAll("'","''");
    }

    @RequestMapping("/findUsers")
    public void findUsers(@RequestParam(name="name") String name, HttpServletResponse res) throws IOException {
        Configuration config = new Configuration();
        // Create SessionFactory with MySQL driver
        SessionFactory sessionFactory = config.configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<UserEntity> users = session.createQuery("from UserEntity where firstName ='" + escapeQuotes(name) + "'").list();
        res.getWriter().println("Found " + users.size() + " Users with that name");

    }
}
