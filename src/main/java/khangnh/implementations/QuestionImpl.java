/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.implementations;

import khangnh.questions.QuestionDTO;
import khangnh.interfaces.QuestionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import khangnh.utils.DBUtil;

/**
 *
 * @author khang nguyen
 */
public class QuestionImpl implements QuestionDAO {

    @Override
    public int createQuestion(QuestionDTO question, int status) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        int result = -1;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "INSERT INTO Question(subjectID, userID, question_content, option1, option2, option3, correctAnswer, status) "
                        + "VALUES(?,?,?,?,?,?,?,?)";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setString(1, question.getSubject());
                    statement.setString(2, question.getUser());
                    statement.setString(3, question.getContent());
                    statement.setString(4, question.getOption1());
                    statement.setString(5, question.getOption2());
                    statement.setString(6, question.getOption3());
                    statement.setString(7, question.getCorrectAnswer());
                    statement.setInt(8, status);
                    result = statement.executeUpdate();
                }
            }
        } finally {
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
    public void deleteQuestion(String questionID) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "UPDATE Question SET status = 0 WHERE id = ?";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setInt(1, Integer.valueOf(questionID));
                    statement.executeUpdate();
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
    public void updateQuestion(QuestionDTO question) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "UPDATE Question SET subjectID =?, question_content =?, option1 =?,option2 =?, option3 =?, correctAnswer =?, status = ? "
                        + "WHERE id = ?";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setString(1, question.getSubject());
                    statement.setString(2, question.getContent());
                    statement.setString(3, question.getOption1());
                    statement.setString(4, question.getOption2());
                    statement.setString(5, question.getOption3());
                    statement.setString(6, question.getCorrectAnswer());
                    statement.setInt(7, question.getStatus());
                    statement.setInt(8, question.getId());
                    statement.executeUpdate();
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

    public int countTotalRows(String selectedSubjectName, String txtSearchName, String selectedQuestionStatus) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int total = 0;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                if (selectedQuestionStatus == null && selectedSubjectName == null && txtSearchName == null) {
                    String sql = "SELECT COUNT(id) AS total_rows FROM Question";
                    statement = connection.prepareStatement(sql);
                    if (statement != null) {
                        resultSet = statement.executeQuery();
                        if (resultSet != null) {
                            if (resultSet.next()) {
                                total = resultSet.getInt("total_rows");
                            }
                        }
                    }
                } else if (selectedQuestionStatus == null || selectedQuestionStatus.isEmpty()) {
                    String sql = "SELECT COUNT(id) AS total_rows FROM Question WHERE subjectID LIKE ? AND question_content LIKE ?";
                    statement = connection.prepareStatement(sql);
                    if (statement != null) {
                        statement.setString(1, "%" + selectedSubjectName + "%");
                        statement.setString(2, "%" + txtSearchName + "%");
                        resultSet = statement.executeQuery();
                        if (resultSet != null) {
                            if (resultSet.next()) {
                                total = resultSet.getInt("total_rows");
                            }
                        }
                    }
                } else if (selectedQuestionStatus != null) {
                    String sql = "SELECT COUNT(id) AS total_rows FROM Question WHERE subjectID LIKE ? AND question_content LIKE ? AND status = ?";
                    statement = connection.prepareStatement(sql);
                    if (statement != null) {
                        statement.setString(1, "%" + selectedSubjectName + "%");
                        statement.setString(2, "%" + txtSearchName + "%");
                        statement.setString(3, selectedQuestionStatus);
                        resultSet = statement.executeQuery();
                        if (resultSet != null) {
                            if (resultSet.next()) {
                                total = resultSet.getInt("total_rows");
                            }
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
            return total;
        }
    }

    @Override
    public List<QuestionDTO> getQuestion(String selectedSubjectName, String txtSearchName, String selectedQuestionStatus, int currentPage, int rowsEachPage) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<QuestionDTO> list = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                //can than cai Question.id no tra ra cai bang voi ten la id
                //can ca cai button radio
                //th dau luc chua co bien nao ca
                if (selectedQuestionStatus == null && selectedSubjectName == null && txtSearchName == null) {
                    String sql = "SELECT Question.id AS question_id, subjectID, Account.name AS user_name , question_content, option1,"
                            + " option2, option3, correctAnswer, Question.status FROM  Question, Account WHERE Account.email = Question.userID "
                            + "ORDER BY question_content OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY";
                    statement = connection.prepareStatement(sql);
                    if (statement != null) {
                        statement.setInt(1, currentPage);
                        statement.setInt(2, rowsEachPage);
                        statement.setInt(3, rowsEachPage);
                        resultSet = statement.executeQuery();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                int id = resultSet.getInt("question_id");
                                String subject = resultSet.getString("subjectID");
                                String user = resultSet.getString("user_name");
                                String content = resultSet.getString("question_content");
                                String option1 = resultSet.getString("option1");
                                String option2 = resultSet.getString("option2");
                                String option3 = resultSet.getString("option3");
                                String correctAnswer = resultSet.getString("correctAnswer");
                                int status = resultSet.getInt("status");
                                QuestionDTO question = new QuestionDTO(user, subject, content, option1, option2, option3, correctAnswer, id, status);
                                if (list == null) {
                                    list = new ArrayList<>();
                                }
                                list.add(question);
                            }
                        }
                    }//truong hop nguoi dung ko nhan va truong hop input hidden mang gia tri null
                } else if (selectedQuestionStatus == null || selectedQuestionStatus == "") {
                    try {
                        String sql = "SELECT Question.id AS question_id, subjectID, Account.name AS user_name , question_content, option1,"
                                + " option2, option3, correctAnswer, Question.status FROM  Question, Account WHERE Account.email = Question.userID "
                                + "AND subjectID LIKE ? AND Question.question_content LIKE ? " + "ORDER BY question_content "
                                + " OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY";
                        statement = connection.prepareStatement(sql);
                        if (statement != null) {
                            statement.setString(1, "%" + selectedSubjectName + "%");
                            statement.setString(2, "%" + txtSearchName + "%");
                            statement.setInt(3, currentPage);
                            statement.setInt(4, rowsEachPage);
                            statement.setInt(5, rowsEachPage);
                            resultSet = statement.executeQuery();
                            if (resultSet != null) {
                                while (resultSet.next()) {
                                    int id = resultSet.getInt("question_id");
                                    String subject = resultSet.getString("subjectID");
                                    String user = resultSet.getString("user_name");
                                    String content = resultSet.getString("question_content");
                                    String option1 = resultSet.getString("option1");
                                    String option2 = resultSet.getString("option2");
                                    String option3 = resultSet.getString("option3");
                                    String correctAnswer = resultSet.getString("correctAnswer");
                                    int status = resultSet.getInt("status");
                                    QuestionDTO question = new QuestionDTO(user, subject, content, option1, option2, option3, correctAnswer, id, status);
                                    if (list == null) {
                                        list = new ArrayList<>();
                                    }
                                    list.add(question);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (selectedQuestionStatus != null) {
                    int statusCondition = Integer.valueOf(selectedQuestionStatus);
                    String sql = "SELECT Question.id AS question_id, subjectID, Account.name AS user_name , question_content, option1,"
                            + " option2, option3, correctAnswer, Question.status FROM  Question, Account WHERE Account.email = Question.userID "
                            + "AND subjectID LIKE ? AND Question.question_content LIKE ? AND Question.status = ? " + "ORDER BY question_content "
                            + " OFFSET (?-1)*? ROWS FETCH NEXT ? ROWS ONLY";
                    statement = connection.prepareStatement(sql);
                    if (statement != null) {
                        statement.setString(1, "%" + selectedSubjectName + "%");
                        statement.setString(2, "%" + txtSearchName + "%");
                        statement.setInt(3, statusCondition);
                        statement.setInt(4, currentPage);
                        statement.setInt(5, rowsEachPage);
                        statement.setInt(6, rowsEachPage);
                        resultSet = statement.executeQuery();
                        if (resultSet != null) {
                            while (resultSet.next()) {
                                int id = resultSet.getInt("question_id");
                                String subject = resultSet.getString("subjectID");
                                String user = resultSet.getString("user_name");
                                String content = resultSet.getString("question_content");
                                String option1 = resultSet.getString("option1");
                                String option2 = resultSet.getString("option2");
                                String option3 = resultSet.getString("option3");
                                String correctAnswer = resultSet.getString("correctAnswer");
                                int status = resultSet.getInt("status");
                                QuestionDTO question = new QuestionDTO(user, subject, content, option1, option2, option3, correctAnswer, id, status);
                                if (list == null) {
                                    list = new ArrayList<>();
                                }
                                list.add(question);
                            }
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
    public List<QuestionDTO> getRandomQuestionToMakeAQuiz(int numberOfQuestion, String subjectID, int status) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<QuestionDTO> list = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "SELECT TOP(?) id, question_content, option1, option2, option3, correctAnswer FROM Question WHERE "
                        + "subjectID = ? AND status = ? ORDER BY NEWID()";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setInt(1, numberOfQuestion);
                    statement.setString(2, subjectID);
                    statement.setInt(3, status);
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
}
