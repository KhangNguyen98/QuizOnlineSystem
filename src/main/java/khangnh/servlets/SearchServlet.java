/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khangnh.interfaces.QuestionDAO;
import khangnh.interfaces.SubjectDAO;
import khangnh.principles.Constant;

/**
 *
 * @author khang nguyen
 */
public class SearchServlet extends HttpServlet {

    private static final String SHOW_QUESTION_PAGE = "searchQuestion";
    private static final int currentPage = 1;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        String subjectName = request.getParameter("selectedSubjectName");
        String questionName = request.getParameter("txtSearchName");
        String selectedQuestionStatus = request.getParameter("selectedQuestionStatus");
        boolean isError = false;

        QuestionDAO questionDAO = QuestionDAO.getInstance();
        try {
            int totalRows = questionDAO.countTotalRows(subjectName, questionName, selectedQuestionStatus);
            if (totalRows / (currentPage * Constant.ROWS_EACH_PAGE) > 0) {
                request.setAttribute("NEXT_PAGE", currentPage + 1);
            }
            request.setAttribute("QUESTION", questionDAO.getQuestion(subjectName, questionName, selectedQuestionStatus, currentPage, Constant.ROWS_EACH_PAGE));
            SubjectDAO subjectDAO = SubjectDAO.getInstance();
            request.setAttribute("SUBJECT", subjectDAO.getSubject());
            url = SHOW_QUESTION_PAGE;
        } catch (SQLException ex) {
            log(ex.getMessage());
            isError = true;
            response.sendError(500);
        } catch (NamingException ex) {
            log(ex.getMessage());
            isError = true;
            response.sendError(500);
        } finally {
            if (!isError) {
                if (url == "") {
                    response.sendError(500);
                } else {
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
