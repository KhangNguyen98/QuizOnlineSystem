/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khang nguyen
 */
public class DispatcherFilter implements Filter {
    
    private static final boolean debug = true;
    private static final String CHECK_AUTHENTICATED = "IS_AUTHENTICATED";
    private static final String RESOURCE = "AUTHENTICATION_RESOURCES";
    private static final String MAPPING_SOURCE = "MAPPING_RESOURCES";
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public DispatcherFilter() {
    }    
    
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DispatcherFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }    
    
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DispatcherFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            Boolean isAuthenticated = (Boolean) httpRequest.getAttribute(CHECK_AUTHENTICATED);

//            if (isAuthenticated != null && isAuthenticated) {
//                String uri = httpRequest.getRequestURI();
//                int pos = uri.lastIndexOf("/");
//                String resource = uri.substring(pos + 1);
//                Map<String, String> map = (Map<String, String>) httpRequest.getServletContext().getAttribute("MAPPING_RESOURCES");
//                if (map.containsKey(resource)) {
//                    String url = uri.substring(0, pos + 1) + map.get(resource);
//                    httpRequest.getRequestDispatcher(url).forward(request, response);
//                } else {
//                    List<String> listResource = (List<String>) httpRequest.getServletContext().getAttribute(RESOURCE);
//                    if (listResource.contains(resource)) {
//                        String url = resource.substring(0, 1).toUpperCase() + resource.substring(1) + "Servlet";
////                        if (resource.lastIndexOf("html") > 0 || resource.lastIndexOf("jsp") > 0) {
////                            url = resource;
////                        }
//                        httpRequest.getRequestDispatcher(url).forward(request, response);
//                    } else {
//                        httpResponse.sendError(400);
//                    }
//                }
//            } else{
//                chain.doFilter(request, response);
//            }
            String uri = httpRequest.getRequestURI();
            int pos = uri.lastIndexOf("/");
            String resource = uri.substring(pos + 1);
            List<String> listResource = (List<String>) httpRequest.getServletContext().getAttribute(RESOURCE);
            Map<String, String> map = (Map<String, String>) httpRequest.getServletContext().getAttribute(MAPPING_SOURCE);
            if (resource.equals("register")) {
                String url = resource.substring(0, 1).toUpperCase() + resource.substring(1) + "Servlet";
                httpRequest.getRequestDispatcher(url).forward(request, response);
            } else if (map.containsKey(resource)) {
                chain.doFilter(request, response);
            } else if (listResource.contains(resource)) {
                String url = resource.substring(0, 1).toUpperCase() + resource.substring(1) + "Servlet";
                //if(resource.lastIndexOf("html") > 0 || resource.lastIndexOf("jsp") > 0) url = resource;
                httpRequest.getRequestDispatcher(url).forward(request, response);
            } else {
                httpResponse.sendError(400);
            }

//            if(isAuthenticated != null && isAuthenticated){
//                String uri = httpRequest.getRequestURI();
//                int pos = uri.lastIndexOf("/");
//                String resource = uri.substring(pos+1);
//                List<String> listResource = (List<String>) httpRequest.getServletContext().getAttribute(RESOURCE);
//                if(listResource.contains(resource)){
//                    String url = resource.substring(0,1).toUpperCase() + resource.substring(1) + "Servlet";
//                    //if(resource.lastIndexOf("html") > 0 || resource.lastIndexOf("jsp") > 0) url = resource;
//                    httpRequest.getRequestDispatcher(url).forward(request, response);
//                } else{
//                    httpResponse.sendError(400);
//                }
//            } else{
//                chain.doFilter(request, response);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("DispatcherFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("DispatcherFilter()");
        }
        StringBuffer sb = new StringBuffer("DispatcherFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
