/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.servlets;

import java.io.IOException;
import java.sql.SQLException;
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
import khangnh.interfaces.ResultInExamDAO;
import khangnh.interfaces.SubjectDAO;
import khangnh.results.ResultInExamDTO;

/**
 *
 * @author khang nguyen
 */
public class SearchHistoryServlet extends HttpServlet {

    private static final String SHOW_HISTORY = "chooseSubject";
    private static final String NO_RESULT = "defaultUserPage?noResult=true";

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
        String selectedSubject = request.getParameter("selectedSubject");
        HttpSession session = request.getSession(false);
        AccountDTO account = (AccountDTO) session.getAttribute("USER");
        ResultInExamDAO resultDAO = ResultInExamDAO.getInstance();
        boolean isError = false;
        try {
            List<ResultInExamDTO> listResult = resultDAO.viewResult(selectedSubject, account.getEmail());
            request.setAttribute("HISTORY", listResult);
            if (listResult == null) {
                url = NO_RESULT;
            } else {
                SubjectDAO subjectDAO = SubjectDAO.getInstance();
                request.setAttribute("SUBJECT", subjectDAO.getSubject());
                url = SHOW_HISTORY;
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
                } else if (url == NO_RESULT) {
                    response.sendRedirect(url);
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
