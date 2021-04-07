/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.questions;

import java.io.Serializable;

/**
 *
 * @author khang nguyen
 */
public class QuestionDTO implements Serializable{

    private String user, subject, content, option1, option2, option3, correctAnswer;
    private int id, status;

    public QuestionDTO(String subject, String content, String option1, String option2, String option3, String correctAnswer, int id, int status) {
        this.subject = subject;
        this.content = content;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctAnswer = correctAnswer;
        this.id = id;
        this.status = status;
    }

    public QuestionDTO(String content, String option1, String option2, String option3, String correctAnswer, int id) {
        this.content = content;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctAnswer = correctAnswer;
        this.id = id;
    }
    

    public QuestionDTO(String user, String subject, String content, String option1, String option2, String option3, String correctAnswer) {
        this.user = user;
        this.subject = subject;
        this.content = content;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctAnswer = correctAnswer;
    }

    public QuestionDTO(String user, String subject, String content, String option1, String option2, String option3, String correctAnswer, int id, int status) {
        this.user = user;
        this.subject = subject;
        this.content = content;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctAnswer = correctAnswer;
        this.id = id;
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
