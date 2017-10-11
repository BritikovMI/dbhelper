package ru.rbt.dbhelper.war;

import ru.rbt.dbhelper.rest.OrderRestImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * Created by BritikovMI on 31.07.2017.
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/"})
public class MyServlet extends HttpServlet {
    //For getDateMethod
//    String newDate, newMonth;
//    String dayS, monthS, yearS;
//    int day;
//    int month;
//    int year;
    //For getDateMethod
//    //For dParser
//    String[] value;
//    String[] dCourse;
//    String preFinalSCourse[];
//
//    //For dParser
    //For queue analyze Thread threadRead
    static BigDecimal currentElem;
    static LinkedList<BigDecimal> courseElements = new LinkedList<>();
    volatile static Queue<BigDecimal> queue = new PriorityQueue<>();
    //For queue analyze Thread threadRead

    String courseD[];
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
            BigDecimal finalCourse = BigDecimal.valueOf(0);
            Integer i = Integer.valueOf(0);
            do {
                try {
                    finalCourse = siteParser(sb);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                value = sb.toString().split("<Value>");
//                dCourse = value[11].split("</Value>");
//                preFinalSCourse = dCourse[0].split(",");
//                finalSCourse = preFinalSCourse[0] + "." + preFinalSCourse[1];
//                finalCourse = Double.parseDouble(finalSCourse);
                queue.add(finalCourse);
//                pw.println("Hello from " + Thread.currentThread());

                i++;
            } while (i < 24);
            Thread.interrupted();
        });

        Thread threadRead = new Thread(() -> {
            int compare;
            BigDecimal first, last;
//            Integer i = Integer.valueOf(0);
//            do {
            courseElements.clear();
            try {
                Thread.sleep(120000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            do {
                currentElem = queue.poll();
                if (currentElem != null)
                    courseElements.add(currentElem);
            } while (currentElem != null);
            first = courseElements.getFirst();
            last = courseElements.getLast();
            compare = first.compareTo(last);
            if (compare == -1) {
                pw.println("The rate fell by: " + (first.subtract(last)) + " and amounted to " + last);
            } else if (compare == 1) {
                pw.println("The rate increased by: " + (last.subtract(first)) + " and amounted to " + last);
            } else {
                pw.println("The rate has not changed and is equal to: " + first);
            }
//                i++;
//            } while (i < 1);
            Thread.interrupted();
        });


        try {
            threadWrite.start();
            threadRead.start();
            threadWrite.join();
            threadRead.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadWrite.interrupt();
        threadRead.interrupt();
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
//        getDate();
//        newDate = dayS;
//        if (month < 10)
//            newMonth = "0" + String.valueOf(month);
//        else
//            newMonth = String.valueOf(month);

        URLConnection connection = new URL("https://www.calc.ru/forex-USD-RUB.html").openConnection();

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

    public BigDecimal siteParser(StringBuilder sb) throws IOException {
        String finalSCourse;
        BigDecimal finalCourse;
        String HTMLSTring = siteLoader(sb);

        Document html = Jsoup.parse(HTMLSTring);

        String h1 = html.body().getElementsByClass("t18").text();

        courseD = h1.split(" ");
        finalSCourse = courseD[3];

        finalCourse = BigDecimal.valueOf(Double.parseDouble(finalSCourse));
        return finalCourse;
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
//    public void getDate() {
//        Date date = new Date();
//        String dateS = date.toString();
//        String[] dateSt = dateS.split(" ");
//
//        monthS = dateSt[1];
//        dayS = dateSt[2];
//        yearS = dateSt[5];
//
//        day = Integer.parseInt(dayS);
//        year = Integer.parseInt(yearS);
//        switch (monthS) {
//            case "Jan":
//                month = 1;
//                break;
//            case "Feb":
//                month = 2;
//                break;
//            case "Mar":
//                month = 3;
//                break;
//            case "Apr":
//                month = 4;
//                break;
//            case "May":
//                month = 5;
//                break;
//            case "Jun":
//                month = 6;
//                break;
//            case "Jul":
//                month = 7;
//                break;
//            case "Aug":
//                month = 8;
//                break;
//            case "Sep":
//                month = 9;
//                break;
//            case "Oct":
//                month = 10;
//                break;
//            case "Nov":
//                month = 11;
//                break;
//            case "Dec":
//                month = 12;
//                break;
//            default:
//                month = 0;
//                break;
//        }
////
//    }

}
