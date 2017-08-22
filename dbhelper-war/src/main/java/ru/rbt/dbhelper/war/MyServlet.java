package ru.rbt.dbhelper.war;
import ru.rbt.dbhelper.utils.QueryManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Created by BritikovMI on 31.07.2017.
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/"})
public class MyServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Long name1 = Long.parseLong(name);
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<h1>Hello, the name is: </h1>");
        pw.println(name);

        pw.println("<h3>Your table</h3> <pre>");
        pw.println(new QueryManager().runQuery(name1));
        pw.println("</pre>");
        pw.close();
    }
}
