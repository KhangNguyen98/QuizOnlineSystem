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
import javax.naming.NamingException;
import khangnh.interfaces.QuizDAO;
import khangnh.utils.DBUtil;

/**
 *
 * @author khang nguyen
 */
public class QuizImpl implements QuizDAO{
    @Override
    public void createQuiz(String accountID, long timeLength, long createDate, int status) throws SQLException, NamingException{
        Connection connection = null;
        PreparedStatement statement = null;
        try {
           connection = DBUtil.getConnection();
           if(connection != null){
               String sql = "INSERT INTO Quiz(userID, length, createDate, status) VALUES(?,?,?,?)";
               statement = connection.prepareStatement(sql);
               if(statement != null){
                   statement.setString(1, accountID);
                   statement.setLong(2, timeLength);
                   statement.setLong(3, createDate);
                   statement.setInt(4, status);
                   statement.executeUpdate();
               }
           }
        } finally {
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        }
    }

    @Override
    public int getQuizID(String accountID, long createDate) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result = -1;
        try {
            connection = DBUtil.getConnection();
            if(connection != null){
                String sql = "SELECT id FROM Quiz WHERE userID = ? AND createDate = ?";
                statement = connection.prepareStatement(sql);
                if(statement != null){
                    statement.setString(1, accountID);
                    statement.setLong(2, createDate);
                    resultSet = statement.executeQuery();
                    if(resultSet.next()){
                        result = resultSet.getInt("id");
                    }
                }
            }
        } finally {
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
            return result;
        }
    }
    
}
