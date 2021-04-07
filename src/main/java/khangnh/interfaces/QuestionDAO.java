/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.interfaces;

import java.sql.SQLException;
import khangnh.questions.QuestionDTO;
import khangnh.implementations.QuestionImpl;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author khang nguyen
 */
public interface QuestionDAO {
    int createQuestion(QuestionDTO question, int status) throws SQLException, NamingException;
    static QuestionImpl getInstance()
    {
        return new QuestionImpl();
    };
    List<QuestionDTO> getQuestion(String selectedSubjectName, String txtSearchName, String selectedQuestionStatus, int currentPage, int rowsEachPage)throws SQLException, NamingException;
    void deleteQuestion(String questionID) throws SQLException, NamingException;
    void updateQuestion(QuestionDTO question) throws SQLException, NamingException;
    int countTotalRows(String selectedSubjectName, String txtSearchName, String selectedQuestionStatus) throws SQLException, NamingException;
    List<QuestionDTO> getRandomQuestionToMakeAQuiz(int numberOfQuestion, String subjectID, int status) throws SQLException, NamingException;
}   
