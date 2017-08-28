package ru.rbt.dbhelper.war;

import ru.rbt.dbhelper.other.DaoManager;

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
    private DaoManager daoManager;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String sNum = request.getParameter("id");
        Long num = Long.parseLong(sNum);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<pre>");
        pw.println("<h1>Hello, the name is: </h1>");
        pw.println(name);

        pw.println("<h3>Your table</h3> <pre>");

        List<String> result = daoManager.handleRequest(name, num);

        for (String s : result) {
            pw.println(s);
        }

        pw.println("</pre>");
        pw.close();
    }
}
