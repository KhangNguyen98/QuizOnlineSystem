/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import khangnh.interfaces.QuizDAO;
import khangnh.interfaces.QuizDetailDAO;
import khangnh.interfaces.SavedQuizDAO;
import khangnh.principles.Constant;
import khangnh.principles.Status;
import khangnh.questions.QuestionDTO;
import khangnh.savedQuizes.SavedQuizDTO;

/**
 *
 * @author khang nguyen
 */
public class TakeAQuizServlet extends HttpServlet {

    private static final String TAKE_QUIZ = "takeQuiz";

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
        HttpSession session = request.getSession(false);
        AccountDTO account = (AccountDTO) session.getAttribute("USER");
        boolean isError = false;

        try {
            SavedQuizDAO savedQuizDao = SavedQuizDAO.getInstance();
            boolean check = savedQuizDao.check(account.getEmail());

            if (check) {
                request.setAttribute("QUIZ", savedQuizDao.getList());
                SavedQuizDTO savedQuiz = savedQuizDao.getNeededThing(account.getEmail());
                request.setAttribute("QUIZID", savedQuiz.getQuizID());
                request.setAttribute("SELECTED_SUBJECT", savedQuiz.getSubjectID());
            } else {
                String selectedSubject = request.getParameter("selectedSubject");
                request.setAttribute("SELECTED_SUBJECT", selectedSubject);
                Date today = new Date();
                long createDate = today.getTime();
                QuizDAO quizDAO = QuizDAO.getInstance();
                quizDAO.createQuiz(account.getEmail(), Constant.LENGTH_OF_QUIZ, createDate, Status.ACTIVE);
                int quizID = quizDAO.getQuizID(account.getEmail(), createDate);
                request.setAttribute("QUIZID", quizID);
                //cach nay ko on cai nao cung luu trong session ko on lam

                QuestionDAO questionDAO = QuestionDAO.getInstance();
                Random random = new Random();
                int randomNumberQuestion = random.nextInt(Constant.MAX_LENGTH_OF_QUESTION - Constant.MIN_LENGTH_OF_QUESTION) + Constant.MIN_LENGTH_OF_QUESTION;
                List<QuestionDTO> listQuestion = questionDAO.getRandomQuestionToMakeAQuiz(randomNumberQuestion, selectedSubject, Status.ACTIVE);

                QuizDetailDAO quizDetailDAO = QuizDetailDAO.getInstance();
                quizDetailDAO.saveQuestionToQuiz(quizID, listQuestion);
                savedQuizDao.createSavedQuiz(account.getEmail(), selectedSubject, quizID, listQuestion);
                //mai mot update vo database h tam thoi xai do session

                request.setAttribute("QUIZ", listQuestion);
            }
            request.setAttribute("TIME_LENGTH", Constant.LENGTH_OF_QUIZ);
            url = TAKE_QUIZ;
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
