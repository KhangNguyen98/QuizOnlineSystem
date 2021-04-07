/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.results;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author khang nguyen
 */
public class ResultInExamDTO implements Serializable{
    private String accountID ,subjectID;
    private int quizID, status, numberOfCorrectAnswer, totalQuestion;
    private float mark;
    private long submitDate;
    private String historyOfDate;

    public String getHistoryOfDate() {
        return historyOfDate;
    }

    public void setHistoryOfDate(String historyOfDate) {
        this.historyOfDate = historyOfDate;
    }

    public ResultInExamDTO(String subjectID, int numberOfCorrectAnswer, int totalQuestion, float mark, String historyOfDate) {
        this.subjectID = subjectID;
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
        this.totalQuestion = totalQuestion;
        this.mark = mark;
        this.historyOfDate = historyOfDate;
    }

    

    public ResultInExamDTO(String accountID, String subjectID, int quizID, int status, int numberOfCorrectAnswer, int totalQuestion, float mark, long submitDate) {
        this.accountID = accountID;
        this.subjectID = subjectID;
        this.quizID = quizID;
        this.status = status;
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
        this.totalQuestion = totalQuestion;
        this.mark = mark;
        this.submitDate = submitDate;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumberOfCorrectAnswer() {
        return numberOfCorrectAnswer;
    }

    public void setNumberOfCorrectAnswer(int numberOfCorrectAnswer) {
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
    }

    public int getTotalQuestion() {
        return totalQuestion;
    }

    public void setTotalQuestion(int totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public long getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(long submitDate) {
        this.submitDate = submitDate;
    }
    
}
