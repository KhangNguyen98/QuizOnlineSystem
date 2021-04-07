/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.implementations;

import khangnh.subjects.SubjectDTO;
import khangnh.interfaces.SubjectDAO;
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
public class SubjectImpl implements SubjectDAO {

    @Override
    public List<SubjectDTO> getSubject() throws SQLException, NamingException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<SubjectDTO> list = null;
        try {
            connection = DBUtil.getConnection();
            if (connection != null) {
                String sql = "SELECT id, name FROM Subject";
                statement = connection.prepareStatement(sql);
                if (statement != null) {
                    resultSet = statement.executeQuery();
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            String id = resultSet.getString("id");
                            String name = resultSet.getString("name");
                            SubjectDTO subject = new SubjectDTO(id, name);
                            if (list == null) {
                                list = new ArrayList<>();
                            }
                            list.add(subject);
                        }
                    }
                }
            }
        } finally{
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
            return list;
        }
    }

}
