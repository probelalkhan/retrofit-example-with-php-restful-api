package net.simplifiedcoding.retrofitexample.models;

/**
 * Created by Belal on 15/04/17.
 */

public class Message {
    private int id;
    private String from;
    private String to;
    private String title;
    private String message;
    private String sent;

    public Message(int id, String from, String to, String title, String message, String sent) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.title = title;
        this.message = message;
        this.sent = sent;
    }

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getSent() {
        return sent;
    }
}
