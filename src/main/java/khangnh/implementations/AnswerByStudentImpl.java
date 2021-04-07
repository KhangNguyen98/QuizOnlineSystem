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
import khangnh.answers.AnswerByStudentDTO;
import khangnh.interfaces.AnswerByStudentDAO;
import khangnh.utils.DBUtil;

/**
 *
 * @author khang nguyen
 */
public class AnswerByStudentImpl implements AnswerByStudentDAO{

    @Override
    public void saveAnswerOfStudent(List<AnswerByStudentDTO> listAnswer) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            if(connection != null){
                connection.setAutoCommit(false);
                String sql = "INSERT INTO AnswerByStudent(userID, quizID, questionID, answerByStudent, correctAnswer) VALUES(?,?,?,?,?)";
                statement = connection.prepareStatement(sql);
                if(statement != null){
                    for (AnswerByStudentDTO answerByStudentDTO : listAnswer) {
                        statement.setString(1, answerByStudentDTO.getAccountID());
                        statement.setInt(2, answerByStudentDTO.getQuizID());
                        statement.setInt(3, answerByStudentDTO.getQuestionID());
                        statement.setString(4, answerByStudentDTO.getAnswerByStudent());
                        statement.setString(5, answerByStudentDTO.getCorrectAnswers());
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
