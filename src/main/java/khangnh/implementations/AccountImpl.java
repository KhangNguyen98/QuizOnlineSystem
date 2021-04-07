/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.implementations;

import khangnh.accounts.AccountDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import khangnh.utils.DBUtil;
import khangnh.interfaces.AccountDAO;

/**
 *
 * @author khang nguyen
 */
public class AccountImpl implements Serializable, AccountDAO {

    @Override
    public void createAccount(AccountDTO user) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "INSERT INTO Account(email, name, password, roleID, status) VALUES(?,?,?,?,?)";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setString(1, user.getEmail());
                    statement.setString(2, user.getName());
                    statement.setString(3, user.getPassword());
                    statement.setInt(4, user.getRoleID());
                    statement.setInt(5, user.getStatus());
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
    public AccountDTO getAccount(String email, String password) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        AccountDTO user = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "SELECT name, roleID, status FROM Account WHERE email = ? AND password = ?";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setString(1, email);
                    statement.setString(2, password);
                    resultSet = statement.executeQuery();
                    if (resultSet != null) {
                        if (resultSet.next()) {
                            String name = resultSet.getString("name");
                            int roleID = resultSet.getInt("roleID");
                            int status = resultSet.getInt("status");
                            user = new AccountDTO(email, name, password, roleID, status);
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
            return user;
        }
    }

    @Override
    public boolean checkAccount(String email) throws SQLException, NamingException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean result = false;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "SELECT email FROM Account WHERE email = ?";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    statement.setString(1, email);
                    resultSet = statement.executeQuery();
                    result = resultSet.next();
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

}
