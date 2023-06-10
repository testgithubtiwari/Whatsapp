package com.example.whatsapp.Models;

import java.util.Date;

public class Message {

    String uId,message,messageId;
    Date timesatmp;
    public Message() {

    }

    public Message(String uId, String message, String messageId, Date timesatmp) {
        this.uId = uId;
        this.message = message;
        this.messageId = messageId;
        this.timesatmp = timesatmp;
    }

    public Message(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Date getTimesatmp() {
        return timesatmp;
    }

    public void setTimesatmp(Date timesatmp) {
        this.timesatmp = timesatmp;
    }
}
