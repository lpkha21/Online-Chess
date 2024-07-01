package javaClasses;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.sql.SQLException;

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
        servletContextEvent.getServletContext().setAttribute("dataBase",dataBase);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
