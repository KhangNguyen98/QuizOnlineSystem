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
import javax.servlet.http.HttpSession;
import khangnh.accounts.AccountDTO;
import khangnh.interfaces.QuestionDAO;
import khangnh.interfaces.SubjectDAO;
import khangnh.principles.Constant;
import khangnh.principles.Role;

/**
 *
 * @author khang nguyen
 */
public class DefaultUserPageServlet extends HttpServlet {

    private int currentPage;
    // private static final String SUCCESS_CREATE = "search.jsp?success=true";
    private static final String STUDENT_TAKE_QUIZ = "chooseSubject";
    private static final String TEACHER_SEARCH_QUESTION = "searchQuestion";

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
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        HttpSession session = request.getSession(false);
        AccountDTO user = (AccountDTO) session.getAttribute("USER");
        boolean isError = false;

        try {
            if (user != null) {
                SubjectDAO subjectDAO = SubjectDAO.getInstance();
                if (user.getRoleID() == Role.TEACHER) {
                    QuestionDAO questionDAO = QuestionDAO.getInstance();

                    //
                    String selectedSubjectName = request.getParameter("selectedSubjectName");
                    String txtSearchName = request.getParameter("txtSearchName");
                    String selectedQuestionStatus = request.getParameter("selectedQuestionStatus");
                    //

                    String checkPage = request.getParameter("currentPage");
                    if (checkPage == null || checkPage.isEmpty()) {
                        currentPage = 1;
                    } else {
                        currentPage = Integer.parseInt(checkPage);
                    }

                    int totalRows = questionDAO.countTotalRows(selectedSubjectName, txtSearchName, selectedQuestionStatus);

                    String convertIntoString = String.valueOf(Math.ceil((double) totalRows / Constant.ROWS_EACH_PAGE));
                    int pos = convertIntoString.indexOf(".");
                    int totalPage = Integer.parseInt(convertIntoString.substring(0, pos));
                    if (currentPage <= totalPage) {
                        if ((double) totalRows / ((currentPage + 1) * Constant.ROWS_EACH_PAGE) >= 1) {
                            request.setAttribute("NEXT_PAGE", currentPage + 1);
                        }

                        if (currentPage > 1) {
                            request.setAttribute("PREVIOUS_PAGE", currentPage - 1);
                        }

                        request.setAttribute("QUESTION", questionDAO.getQuestion(selectedSubjectName, txtSearchName, selectedQuestionStatus, currentPage, Constant.ROWS_EACH_PAGE));
                    } else {
                        request.setAttribute("QUESTION", null);
                    }
                    url = TEACHER_SEARCH_QUESTION;
                } else if (user.getRoleID() == Role.STUDENT) {
                    url = STUDENT_TAKE_QUIZ;
                }
                request.setAttribute("SUBJECT", subjectDAO.getSubject());
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
