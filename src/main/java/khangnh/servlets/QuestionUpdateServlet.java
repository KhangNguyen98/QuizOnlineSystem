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
import khangnh.questions.QuestionDTO;
import khangnh.validators.QuestionValidator;
import khangnh.validators.Validator;

/**
 *
 * @author khang nguyen
 */
public class QuestionUpdateServlet extends HttpServlet {

    private int currentPage;
    private static String SHOW_QUESTION_PAGE = "defaultUserPage?update=true";
    private static String SHOW_ERROR_MESSAGE = "searchQuestion?invalidQuestion=";

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
        boolean isError = false;
        String url = "";
        String id = request.getParameter("updatedQuestion");
        String subject = request.getParameter("selectedSubject");
        String content = request.getParameter("selectedContent");
        String option1 = request.getParameter("selectedOption1");
        String option2 = request.getParameter("selectedOption2");
        String option3 = request.getParameter("selectedOption3");
        String correctAnswer = request.getParameter("selectedCorrectAnswer");
        int status = Integer.parseInt(request.getParameter("selectedStatus"));

        //
        String selectedSubjectName = request.getParameter("selectedSubjectName");
        String txtSearchName = request.getParameter("txtSearchName");
        String selectedQuestionStatus = request.getParameter("selectedQuestionStatus");

        //
        String checkPage = request.getParameter("currentPage");
        //
        QuestionDTO question = new QuestionDTO(subject, content, option1, option2, option3, correctAnswer, Integer.parseInt(id), status);
        Validator validator = new QuestionValidator(question);
        validator.validate();

        try {
            if (!validator.hasError()) {
                QuestionDAO questionDAO = QuestionDAO.getInstance();
                questionDAO.updateQuestion(question);
                url = SHOW_QUESTION_PAGE;
                url += "&" + "selectedSubjectName=" + selectedSubjectName + "&txtSearchName=" + txtSearchName + "&selectedQuestionStatus=" + selectedQuestionStatus
                        + "&currentPage=" + checkPage;
                response.sendRedirect(url);
            } else {
                QuestionDAO questionDAO = QuestionDAO.getInstance();
                ////           
//            if (checkPage == null || checkPage.isEmpty()) {
//                currentPage = 1;
//            } else {
//                currentPage = Integer.parseInt(checkPage);
//            }
                currentPage = Integer.parseInt(checkPage);
                int totalRows = questionDAO.countTotalRows(selectedSubjectName, txtSearchName, selectedQuestionStatus);
                if (totalRows / (currentPage * Constant.ROWS_EACH_PAGE) > 0) {
                    request.setAttribute("NEXT_PAGE", currentPage + 1);
                }

                if (currentPage > 1) {
                    request.setAttribute("PREVIOUS_PAGE", currentPage - 1);
                }
                ////

                request.setAttribute("ERROR", validator.getError());

                request.setAttribute("QUESTION", questionDAO.getQuestion(selectedSubjectName, txtSearchName, selectedQuestionStatus, currentPage, Constant.ROWS_EACH_PAGE));
                SubjectDAO subjectDAO = SubjectDAO.getInstance();
                request.setAttribute("SUBJECT", subjectDAO.getSubject());
                url = SHOW_ERROR_MESSAGE + id;
                request.getRequestDispatcher(url).forward(request, response);
            }
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
