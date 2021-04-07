/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import khangnh.interfaces.AccountDAO;
import khangnh.interfaces.Hashing;

/**
 *
 * @author khang nguyen
 */
public class LoginServlet extends HttpServlet {

    private static final String ERROR_LOGIN = "signIn?error=true";
    private static final String INEXISTENCE_EMAIL = "signIn?inexistence=Email doesn't exist";
    private static final String DEFAULT_PAGE = "defaultUserPage";

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
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        AccountDAO userDAO = AccountDAO.getImplementation();
        boolean checkAccount;
        boolean isError = false;
        try {
            checkAccount = userDAO.checkAccount(email);
            url = ERROR_LOGIN;
            if (checkAccount == true) {
                Hashing hash = Hashing.getInstance();
                String hashPassword = hash.hash(password);
                AccountDTO user = userDAO.getAccount(email, hashPassword);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", user);
                    url = DEFAULT_PAGE;
                }
            } else {
                url = INEXISTENCE_EMAIL;
            }
        } catch (NoSuchAlgorithmException ex) {
            log(ex.getMessage());
            isError = true;
            response.sendError(500);
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
                    response.sendRedirect(url);
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
