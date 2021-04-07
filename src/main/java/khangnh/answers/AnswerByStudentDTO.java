/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.answers;

import java.io.Serializable;

/**
 *
 * @author khang nguyen
 */
public class AnswerByStudentDTO implements Serializable{
    private String accountID, answerByStudent, correctAnswer;
    private int quizID, questionID;

    public AnswerByStudentDTO(String accountID, String answerByStudent, String correctAnswer, int quizID, int questionID) {
        this.accountID = accountID;
        this.answerByStudent = answerByStudent;
        this.correctAnswer = correctAnswer;
        this.quizID = quizID;
        this.questionID = questionID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAnswerByStudent() {
        return answerByStudent;
    }

    public void setAnswerByStudent(String answerByStudent) {
        this.answerByStudent = answerByStudent;
    }

    public String getCorrectAnswers() {
        return correctAnswer;
    }

    public void setCorrectAnswers(String correctAnswer) {
        this.correctAnswer = correctAnswer;
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
