/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.savedQuizes;

import java.io.Serializable;

/**
 *
 * @author khang nguyen
 */
public class SavedQuizDTO implements Serializable{
    private String accountID, subjectID;
    private int quizID, questionID;

    public SavedQuizDTO(String accountID, String subjectID, int quizID, int questionID) {
        this.accountID = accountID;
        this.subjectID = subjectID;
        this.quizID = quizID;
        this.questionID = questionID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }
    
}
