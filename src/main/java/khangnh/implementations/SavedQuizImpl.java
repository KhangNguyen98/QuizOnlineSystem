/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khangnh.interfaces.SavedQuizDAO;
import khangnh.questions.QuestionDTO;
import khangnh.savedQuizes.SavedQuizDTO;
import khangnh.utils.DBUtil;

/**
 *
 * @author khang nguyen
 */
public class SavedQuizImpl implements SavedQuizDAO {

    @Override
    public void createSavedQuiz(String userID, String subjectID, int quizID, List<QuestionDTO> listQuestion) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
                String sql = "INSERT INTO SavedQuiz(userID, subjectID, quizID, questionID) VALUES(?,?,?,?)";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    for (QuestionDTO question : listQuestion) {
                        statement.setString(1, userID);
                        statement.setString(2, subjectID);
                        statement.setInt(3, quizID);
                        statement.setInt(4, question.getId());
                        statement.executeUpdate();
                    }
                    connection.commit();
                }
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean check(String userID) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "SELECT userID FROM SavedQuiz where userID = ?";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setString(1, userID);
                    resultSet = statement.executeQuery();
                    if (resultSet != null) {
                        result = resultSet.next();
                    }
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            return result;
        }
    }

    @Override
    public List<QuestionDTO> getList() throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<QuestionDTO> list = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "SELECT Question.id, question_content, option1, option2, option3, correctAnswer FROM Question WHERE Question.id IN"
                        + " (SELECT questionID FROM SavedQuiz)";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    resultSet = statement.executeQuery();
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt("id");
                            String content = resultSet.getString("question_content");
                            String option1 = resultSet.getString("option1");
                            String option2 = resultSet.getString("option2");
                            String option3 = resultSet.getString("option3");
                            String correctAnswer = resultSet.getString("correctAnswer");
                            QuestionDTO question = new QuestionDTO(content, option1, option2, option3, correctAnswer, id);
                            if (list == null) {
                                list = new ArrayList<>();
                            }
                            list.add(question);
                        }
                    }
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            return list;
        }
    }

    @Override
    public SavedQuizDTO getNeededThing(String userID) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        SavedQuizDTO quiz = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "SELECT TOP(1) quizID, questionID, subjectID FROM SavedQuiz where userID = ?";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setString(1, userID);
                    resultSet = statement.executeQuery();
                    if (resultSet != null) {
                        if (resultSet.next()) {
                            int quizID = resultSet.getInt("quizID");
                            int questionID = resultSet.getInt("questionID");
                            String subjectID = resultSet.getString("subjectID");
                            quiz = new SavedQuizDTO(userID, subjectID, quizID, questionID);
                        }
                    }
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            return quiz;
        }
    }

}
