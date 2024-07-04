package javaClasses;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataBase dataBase = null;
        try {
            dataBase = new DataBase();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String> minute10 = new ArrayList<String>();
        ArrayList<String> minute5 = new ArrayList<String>();
        ArrayList<String> minute3 = new ArrayList<String>();
        servletContextEvent.getServletContext().setAttribute("minute10",minute10);
        servletContextEvent.getServletContext().setAttribute("minute5",minute5);
        servletContextEvent.getServletContext().setAttribute("minute3",minute3);
        servletContextEvent.getServletContext().setAttribute("dataBase",dataBase);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
