/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.interfaces;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import khangnh.implementations.SavedQuizImpl;
import khangnh.questions.QuestionDTO;
import khangnh.savedQuizes.SavedQuizDTO;

/**
 *
 * @author khang nguyen
 */
public interface SavedQuizDAO {

    void createSavedQuiz(String userID, String subjectID, int quizID, List<QuestionDTO> listQuestion) throws SQLException, NamingException;

    static SavedQuizImpl getInstance() {
        return new SavedQuizImpl();
    }
    boolean check(String userID) throws SQLException, NamingException;
    
    List<QuestionDTO> getList() throws SQLException, NamingException;
    SavedQuizDTO getNeededThing(String userID) throws SQLException, NamingException;
}
