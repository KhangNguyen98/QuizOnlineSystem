/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.interfaces;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import khangnh.implementations.ResultInExamImpl;
import khangnh.results.ResultInExamDTO;

/**
 *
 * @author khang nguyen
 */
public interface ResultInExamDAO {
    static ResultInExamImpl getInstance(){
        return new ResultInExamImpl();
    }
    void createResult(ResultInExamDTO result) throws SQLException, NamingException;
    List<ResultInExamDTO> viewResult(String subjectID, String userID) throws SQLException, NamingException;
}
