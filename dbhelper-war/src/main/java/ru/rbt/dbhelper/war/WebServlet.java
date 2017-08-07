package ru.rbt.dbhelper.war;

/**
 * Created by BritikovMI on 31.07.2017.
 */
public @interface WebServlet {
    String name();

    String[] urlPatterns();
}
