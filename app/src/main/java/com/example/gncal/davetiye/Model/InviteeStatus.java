package com.example.gncal.davetiye.Model;

import java.util.Date;

/**
 * Created by gncal on 8/2/2017.
 */

public class InviteeStatus {

    private boolean sended, viewed, answered;
    private Date sendDate, answerDate;
    private int answer;


    public InviteeStatus(boolean sended, boolean viewed, boolean answered, Date sendDate, Date answerDate, int answer) {
        this.sended = sended;
        this.viewed = viewed;
        this.answered = answered;
        this.sendDate = sendDate;
        this.answerDate = answerDate;
        this.answer = answer;
    }

    public boolean isSended() {
        return sended;
    }

    public void setSended(boolean sended) {
        this.sended = sended;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
