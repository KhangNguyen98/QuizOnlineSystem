/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.interfaces;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import khangnh.answers.AnswerByStudentDTO;
import khangnh.implementations.AnswerByStudentImpl;

/**
 *
 * @author khang nguyen
 */
public interface AnswerByStudentDAO {
    void saveAnswerOfStudent(List<AnswerByStudentDTO> listAnswer) throws SQLException, NamingException;
    static AnswerByStudentImpl getInstance(){
        return new AnswerByStudentImpl();
    }
}
