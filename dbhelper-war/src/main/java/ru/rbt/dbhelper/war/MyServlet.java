package ru.rbt.dbhelper.war;

import ru.rbt.dbhelper.parser.Loader;
import ru.rbt.dbhelper.rest.OrderRestImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


/**
 * Created by BritikovMI on 31.07.2017.
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/"})
public class MyServlet extends HttpServlet {
    //For getDateMethod
    String newDate, newMonth;
    String dayS, monthS, yearS;
    int day;
    int month;
    int year;
    //For getDateMethod
    //For dParser
    String[] value;
    String[] dCourse;
    String preFinalSCourse[];
    String finalSCourse;
    //For dParser
    Queue<Double> queue = new PriorityQueue<>();
    Double finalCourse;
    @Inject
    private OrderRestImpl orderRestImpl;

    //private DaoManager daoManager;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] myParams = request.getRequestURI().split("/");

        StringBuilder sb = new StringBuilder();

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

        Thread threadWrite = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    siteLoader(sb);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                value = sb.toString().split("<Value>");
                dCourse = value[11].split("</Value>");
                preFinalSCourse = dCourse[0].split(",");
                finalSCourse = preFinalSCourse[0] + "." + preFinalSCourse[1];
                finalCourse = Double.parseDouble(finalSCourse);
                queue.add(finalCourse);
                Thread.yield();
            }
        });

        Thread threadRead = new Thread(()->{
            pw.print(queue);
        });


        try {
            threadWrite.start();
            threadRead.start();
            threadWrite.join();
            threadRead.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Runnable task = () -> {
//            try {
//                siteLoader(sb);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            value = sb.toString().split("<Value>");
//            dCourse = value[11].split("</Value>");
//            preFinalSCourse = dCourse[0].split(",");
//            finalSCourse = preFinalSCourse[0] + "." + preFinalSCourse[1];
//            Double finalCourse = Double.parseDouble(finalSCourse);
//
//            pw.print(finalCourse);
//        };
//
//        task.run();
//
//        Thread thread = new Thread(task);
//        thread.start();

        pw.println("</pre>");
        pw.close();
    }

    public String siteLoader(StringBuilder sb) throws IOException {
        getDate();

        if (day < 10)
            newDate = "0" + dayS;
        else
            newDate = dayS;
        if (month < 10)
            newMonth = "0" + String.valueOf(month);
        else
            newMonth = String.valueOf(month);

        URLConnection connection = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + newDate + "/" + newMonth + "/" + year).openConnection();

        InputStream is = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(is);
        char[] buffer = new char[256];
        int rc;


        while ((rc = reader.read(buffer)) != -1)
            sb.append(buffer, 0, rc);

        reader.close();
        String newSb = sb.toString();

        return newSb;
    }

 /*   public void getContent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        PrintWriter out = response.getWriter();

        StringBuilder stringBuilder = new StringBuilder(1000);
        Scanner scanner = new Scanner(request.getInputStream());
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }

        String body = stringBuilder.toString();

        System.out.println(body);
    }
*/
    public void getDate() {
        Date date = new Date();
        String dateS = date.toString();
        String[] dateSt = dateS.split(" ");

        monthS = dateSt[1];
        dayS = dateSt[2];
        yearS = dateSt[5];

        day = Integer.parseInt(dayS);
        year = Integer.parseInt(yearS);
        switch (monthS) {
            case "Jan":
                month = 1;
                break;
            case "Feb":
                month = 2;
                break;
            case "Mar":
                month = 3;
                break;
            case "Apr":
                month = 4;
                break;
            case "May":
                month = 5;
                break;
            case "Jun":
                month = 6;
                break;
            case "Jul":
                month = 7;
                break;
            case "Aug":
                month = 8;
                break;
            case "Sep":
                month = 9;
                break;
            case "Oct":
                month = 10;
                break;
            case "Nov":
                month = 11;
                break;
            case "Dec":
                month = 12;
                break;
            default:
                month = 0;
                break;
        }

    }

}
