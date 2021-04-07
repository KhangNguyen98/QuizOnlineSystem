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
import khangnh.principles.Status;
import khangnh.questions.QuestionDTO;
import khangnh.validators.QuestionValidator;
import khangnh.validators.Validator;

/**
 *
 * @author khang nguyen
 */
public class QuestionCreateServlet extends HttpServlet {

    private static final String CREATE_QUESTION_PAGE = "createQuestion";
    //  private static final String FAIL = "questionCreatePage";
    //private static final String FAIL = "QuestionCreatePageServlet";
    private static final String SHOW_QUESTION_PAGE = "defaultUserPage?create=true";

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
        String subjectID = request.getParameter("selectedSubject");
        String content = request.getParameter("txtContent");
        String option1 = request.getParameter("txtOption1");
        String option2 = request.getParameter("txtOption2");
        String option3 = request.getParameter("txtOption3");
        String correctAnswer = request.getParameter("txtCorrectAnswer");
        QuestionDTO question = new QuestionDTO(null, subjectID, content, option1, option2, option3, correctAnswer);
        Validator validator = new QuestionValidator(question);
        validator.validate();
        boolean isError = false;

        //can than khi dung chuyen qua 2 servlet va  ca 2 deu dung forward voi filter
        try {
            if (validator.hasError()) {

                request.setAttribute("ERROR", validator.getError());

                //chẹp chưa tìm đc cách fix cái filter nhận forward request từ 2 thằng servlet nên chưa thể dùng dc
                SubjectDAO subjectDAO = SubjectDAO.getInstance();
                request.setAttribute("SUBJECT", subjectDAO.getSubject());
                url = CREATE_QUESTION_PAGE;
                // FAIL =questionCreatePage
                request.getRequestDispatcher(url).forward(request, response);
            } else {
                HttpSession session = request.getSession(false);
                AccountDTO user = (AccountDTO) session.getAttribute("USER");
                question.setUser(user.getEmail());
                QuestionDAO questionDAO = QuestionDAO.getInstance();
                questionDAO.createQuestion(question, Status.ACTIVE);
                url = SHOW_QUESTION_PAGE;
                response.sendRedirect(url);
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
