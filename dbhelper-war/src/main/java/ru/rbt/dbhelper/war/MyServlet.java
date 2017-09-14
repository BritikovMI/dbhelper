package ru.rbt.dbhelper.war;

import ru.rbt.dbhelper.other.DaoManager;
import ru.rbt.dbhelper.rest.OrderRestImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Created by BritikovMI on 31.07.2017.
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/"})
public class MyServlet extends HttpServlet {

    @Inject
    private OrderRestImpl orderRestImpl;
    //private DaoManager daoManager;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] myParams = request.getRequestURI().split("/");
//        String name = request.getServletPath();
//        String sNum = request.getRequestURI();
        String name = myParams[4];
        Long num = Long.parseLong(myParams[6]);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<pre>");
        pw.println("<h1>Hello, the name is: </h1>" + name + "<h3>Your table</h3>");

//        List<String> result = daoManager.handleRequest(name, num);

        pw.println(orderRestImpl.findByNameAndId(name, num));

//        for (String s : result) {
//            pw.println(s);
//        }

        pw.println("</pre>");
        pw.close();
    }
}
