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
import khangnh.accounts.AccountDTO;
import khangnh.interfaces.AccountDAO;
import khangnh.interfaces.Hashing;
import khangnh.makers.AccountMaker;
import khangnh.principles.Role;
import khangnh.principles.Status;
import khangnh.validators.UserValidator;
import khangnh.validators.Validator;

/**
 *
 * @author khang nguyen
 */
public class RegisterServlet extends HttpServlet {

    private static final String INVALID_REGISTRY = "signUp";
    private static final String VALID_REGISTRY = "signIn?success=true";

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
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirmPassword");
        String name = request.getParameter("txtName");
        AccountMaker createUser = new AccountMaker(email, name, password, confirmPassword);
        Validator valid = new UserValidator(createUser);
        valid.validate();
        if (!valid.hasError()) {
            Hashing hash = Hashing.getInstance();
            try {
                String hashPassword = hash.hash(password);
                AccountDTO user = new AccountDTO(email, name, hashPassword, Role.STUDENT, Status.ACTIVE);
                AccountDAO userDAO = AccountDAO.getImplementation();
                userDAO.createAccount(user);
                url = VALID_REGISTRY;
                response.sendRedirect(url);
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
            }
        } else {
            request.setAttribute("ERROR", valid.getError());
            url = INVALID_REGISTRY;
            request.getRequestDispatcher(url).forward(request, response);
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
