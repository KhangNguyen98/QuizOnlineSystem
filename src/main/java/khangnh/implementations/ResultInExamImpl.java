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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import khangnh.interfaces.ResultInExamDAO;
import khangnh.results.ResultInExamDTO;
import khangnh.utils.DBUtil;

/**
 *
 * @author khang nguyen
 */
public class ResultInExamImpl implements ResultInExamDAO {

    @Override
    public void createResult(ResultInExamDTO result) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "INSERT INTO ResultInExam(userID, quizID, submitDate, mark, numberOfCorrectAnswer, totalQuestion, status, subjectID) VALUES(?,?,?,?,?,?,?,?)";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setString(1, result.getAccountID());
                    statement.setInt(2, result.getQuizID());
                    statement.setLong(3,  result.getSubmitDate());
                    statement.setFloat(4, result.getMark());
                    statement.setInt(5, result.getNumberOfCorrectAnswer());
                    statement.setInt(6, result.getTotalQuestion());
                    statement.setInt(7, result.getStatus());
                    statement.setString(8, result.getSubjectID());
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
    public List<ResultInExamDTO> viewResult(String subjectID, String userID) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<ResultInExamDTO> listResult = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "SELECT  submitDate, numberOfCorrectAnswer, totalQuestion, mark FROM ResultInExam WHERE subjectID = ? AND userID = ?";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setString(1, subjectID);
                    statement.setString(2, userID);
                    resultSet = statement.executeQuery();
                    if (resultSet != null) {
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        while (resultSet.next()) {
                            long submitDate = resultSet.getLong("submitDate");
                            int numberOfCorrectAnswer = resultSet.getInt("numberOfCorrectAnswer");
                            int totalQuestion = resultSet.getInt("totalQuestion");
                            float mark = resultSet.getFloat("mark");
                            Date date = new Date(submitDate);
                            String historyOfDate = format.format(date);
                            ResultInExamDTO result = new ResultInExamDTO(subjectID, numberOfCorrectAnswer, totalQuestion, mark, historyOfDate);
                            if (listResult == null) {
                                listResult = new ArrayList<>();
                            }
                            listResult.add(result);
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            return listResult;
        }
    }

}
