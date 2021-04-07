/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnh.validators;

import khangnh.questions.QuestionDTO;

/**
 *
 * @author khang nguyen
 */
public class QuestionValidator extends Validator<QuestionDTO> {

    public QuestionValidator(QuestionDTO object) {
        super(object);
    }
    private void checkSubject()
    {
        if(this.object.getSubject().trim().isEmpty())
        {
            addError("subject", "Error in our server");
        }
    }
    private void checkContent() {
        if (this.object.getContent().trim().isEmpty() || this.object.getContent().trim().length() > 300) {
            addError("content", "Content can't be null and max length is 300");
        }
    }

    private void checkOption1() {
        if (this.object.getOption1().trim().isEmpty() || this.object.getOption1().trim().length() > 300) {
            addError("option1", "Option1 can't be null and max length is 4000");
        }
    }

    private void checkOption3() {
        if (this.object.getOption3().trim().isEmpty() || this.object.getOption3().trim().length() > 300) {
            addError("option3", "Option3 can't be null and max length is 4000");
        }
    }

    private void checkAnswer() {
        if (this.object.getCorrectAnswer().isEmpty() || this.object.getCorrectAnswer().trim().length() > 300) {
            addError("correctAnswer", "Correct answer can't be null and max length is 4000");
        }
    }

    private void checkOption2() {
        if (this.object.getOption2().trim().isEmpty() || this.object.getOption2().trim().length() > 300) {
            addError("option2", "Option2 can't be null and max length is 4000");
        }
    }

    @Override
    public void validate() {
        checkSubject();
        checkContent();
        checkOption1();
        checkOption2();
        checkOption3();
        checkAnswer();
    }

}
