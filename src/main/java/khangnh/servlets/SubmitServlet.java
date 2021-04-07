/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khangnh.accounts.AccountDTO;
import khangnh.answers.AnswerByStudentDTO;
import khangnh.interfaces.AnswerByStudentDAO;
import khangnh.interfaces.ResultInExamDAO;
import khangnh.interfaces.SubjectDAO;
import khangnh.principles.Status;
import khangnh.results.ResultInExamDTO;

/**
 *
 * @author khang nguyen
 */
public class SubmitServlet extends HttpServlet {

    private static final String SHOW_RESULT = "chooseSubject";

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
        HttpSession session = request.getSession(false);
        AccountDTO account = (AccountDTO) session.getAttribute("USER");
        int quizID = Integer.parseInt(request.getParameter("quizID"));
        int size = Integer.parseInt(request.getParameter("size"));
        String subjectID = request.getParameter("subjectID");
        List<AnswerByStudentDTO> listAnswer = null;
        float mark = 0;

        int numberOfCorrectAnswer = 0;
        float markForEachQuestion = (float) 10 / size;

        //do list lay chi so tu 0 con var status bat dau tu 1
        for (int i = 1; i <= size; i++) {
            int questionID = Integer.parseInt(request.getParameter("question" + i));
            String answerByStudent = request.getParameter("answer" + i);
            String correctAnswer = request.getParameter("correctAnswer" + i);
            //cai so sanh == nay can than lay parameter hinh nhu no xem nhu new string
            //lay correctAnswer de equal vi co the nguoi dung ko chiu nhap cai radio
            if (correctAnswer.equals(answerByStudent)) {
                mark += markForEachQuestion;
                numberOfCorrectAnswer++;
            }
            AnswerByStudentDTO answer = new AnswerByStudentDTO(account.getEmail(), answerByStudent, correctAnswer, quizID, questionID);
            if (listAnswer == null) {
                listAnswer = new ArrayList<>();
            }
            listAnswer.add(answer);
        }
        AnswerByStudentDAO answerDAO = AnswerByStudentDAO.getInstance();
        try {
            answerDAO.saveAnswerOfStudent(listAnswer);
            Date date = new Date();
            request.setAttribute("SUBMIT_DATE", date);
            long submitDate = date.getTime();
            ResultInExamDAO resultDAO = ResultInExamDAO.getInstance();
            ResultInExamDTO result = new ResultInExamDTO(account.getEmail(), subjectID, quizID, Status.ACTIVE, numberOfCorrectAnswer, size, mark, submitDate);
            resultDAO.createResult(result);

            request.setAttribute("RESULT_IN_EXAM", result);

            SubjectDAO subjectDAO = SubjectDAO.getInstance();
            request.setAttribute("SUBJECT", subjectDAO.getSubject());

            url = SHOW_RESULT;
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
