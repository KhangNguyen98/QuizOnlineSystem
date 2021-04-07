package khangnh.listeners;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Web application lifecycle listener.
 *
 * @author khang nguyen
 */
public class ConfigurationContextListener implements ServletContextListener {

    private static final String AUTHEN = "authentication_resources_location";
    private static final String AUTHEN_ATTRIBUTE = "AUTHENTICATION_RESOURCES";
    private static final String TEACHER = "teacher_resources_location";
    private static final String TEACHER_ATTRIBUTE = "TEACHER_RESOURCES";
    private static final String STUDENT = "student_resources_location";
    private static final String STUDENT_ATTRIBUTE = "STUDENT_RESOURCES";
    private static final String RESOURCE = "mapping_resources_location";
    private static final String RESOURCE_ATTRIBUTE = "MAPPING_RESOURCES";

    private static final String DELIMITER = "=";

    private void loadResource(ServletContext servletContext, String mapping, String attributeName) throws FileNotFoundException, IOException {
        String path = servletContext.getInitParameter(mapping);
        String fileName = servletContext.getRealPath("/" + path);
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String resource = "";
        List<String> list = null;
        while ((resource = bufferedReader.readLine()) != null) {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(resource);
        }
        servletContext.setAttribute(attributeName, list);
    }

    private void hideFileExtension(ServletContext servletContext, String mapping, String attributeName) throws FileNotFoundException, IOException {
        String path = servletContext.getInitParameter(mapping);
        String fileName = servletContext.getRealPath("/" + path);
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String resource = "";
        Map<String, String> map = null;
        StringTokenizer token = null;
        while ((resource = bufferedReader.readLine()) != null) {
            if (map == null) {
                map = new HashMap<>();
            }
            token = new StringTokenizer(resource, DELIMITER);
            if (token.hasMoreTokens()) {
                map.put(token.nextToken(), token.nextToken());
            }
        }
        servletContext.setAttribute(attributeName, map);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        try {
            hideFileExtension(servletContext, RESOURCE, RESOURCE_ATTRIBUTE);
            loadResource(servletContext, AUTHEN, AUTHEN_ATTRIBUTE);
            loadResource(servletContext, TEACHER, TEACHER_ATTRIBUTE);
            loadResource(servletContext, STUDENT, STUDENT_ATTRIBUTE);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationContextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
