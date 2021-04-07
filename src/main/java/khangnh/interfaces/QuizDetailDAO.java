/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.interfaces;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import khangnh.implementations.QuizDetailImpl;
import khangnh.questions.QuestionDTO;

/**
 *
 * @author khang nguyen
 */
public interface QuizDetailDAO {
    void saveQuestionToQuiz(int quizID, List<QuestionDTO> listQuestion) throws SQLException, NamingException;
    static QuizDetailImpl getInstance(){
        return new QuizDetailImpl();
    }
}
