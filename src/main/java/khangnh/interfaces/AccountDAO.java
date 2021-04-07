/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.interfaces;

import java.sql.SQLException;
import javax.naming.NamingException;
import khangnh.accounts.AccountDTO;
import khangnh.implementations.AccountImpl;

/**
 *
 * @author khang nguyen
 */
public interface AccountDAO {
    void createAccount(AccountDTO user) throws SQLException, NamingException;
    AccountDTO getAccount(String email, String password) throws SQLException, NamingException;
    boolean checkAccount(String email) throws SQLException, NamingException;
    static AccountImpl getImplementation(){
        return new AccountImpl();
    }
}
