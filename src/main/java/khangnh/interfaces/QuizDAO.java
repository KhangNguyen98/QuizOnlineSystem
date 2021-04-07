/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.interfaces;

import java.sql.SQLException;
import javax.naming.NamingException;
import khangnh.implementations.QuizImpl;

/**
 *
 * @author khang nguyen
 */
public interface QuizDAO {
    void createQuiz(String accountID, long timeLength, long createDate, int status) throws SQLException, NamingException;
    int getQuizID(String accountID, long createDate) throws SQLException, NamingException;
    static QuizImpl getInstance(){
        return new QuizImpl();
    }
}
