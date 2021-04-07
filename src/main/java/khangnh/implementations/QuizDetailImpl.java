/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import khangnh.interfaces.QuizDetailDAO;
import khangnh.questions.QuestionDTO;
import khangnh.utils.DBUtil;

/**
 *
 * @author khang nguyen
 */
public class QuizDetailImpl implements QuizDetailDAO {

    @Override
    public void saveQuestionToQuiz(int quizID, List<QuestionDTO> listQuestion) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                String sql = "INSERT INTO QuizDetail(quizID, questionID) VALUES(?,?)";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    for (QuestionDTO  question : listQuestion) {
                        statement.setInt(1, quizID);
                        statement.setInt(2, question.getId());
                        statement.executeUpdate();
                    }
                    connection.commit();
                }
            }
        } finally {
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        }
    }

}
