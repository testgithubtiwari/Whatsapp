package com.example.whatsapp.Models;

public class Message {

    String uId,message,messageId;
    Long timesatmp;


    public Message() {

    }



    public Message(String uId, String message, String messageId, Long timesatmp) {
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

    public Long getTimesatmp(long time) {
        return timesatmp;
    }

    public void setTimesatmp(Long timesatmp) {
        this.timesatmp = timesatmp;
    }
}
