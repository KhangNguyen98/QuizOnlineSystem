/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.validators;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import khangnh.makers.AccountMaker;
import khangnh.interfaces.AccountDAO;


/**
 *
 * @author khang nguyen
 */
public class UserValidator extends Validator<AccountMaker> {

    public UserValidator(AccountMaker object) {
        super(object);
    }

    private void checkEmail() {
        
        if (object.getEmail().trim().isEmpty() || object.getEmail().trim().length() > 320) {
            addError("email", "Email can't be null and max length is 320");
        } else if(!object.getEmail().matches("\\w*@(\\w*[.])+\\w*")){
            addError("email", "Invalid email");
        } else{
            try {
                AccountDAO userDAO = AccountDAO.getImplementation();
                if(userDAO.checkAccount(object.getEmail())){
                    addError("email", "Existed email");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserValidator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(UserValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void checkPassword(){
        if(object.getPassword().trim().isEmpty() || object.getPassword().trim().length() >50){
            addError("password", "Password can't be null and max length is 50");
        }
    }
    private void checkName(){
        if(object.getName().trim().isEmpty() || object.getName().trim().length() > 30){
            addError("name", "Name can't be null and max length is 30");
        }
    }
    private void confirmPassword(){
        if(object.getConfirmPassword().trim().isEmpty() || !object.getConfirmPassword().trim().equals(object.getPassword())){
            addError("confirmPassword", "Not as same as password");
        }
    }
    
    @Override
    public void validate() {
        checkEmail();
        checkName();
        checkPassword();
        confirmPassword();
    }

}
