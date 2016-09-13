package Model;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private static final Logger LOG = Logger.getLogger(Message.class);

    private User author;
    private String text;

    public Message(User author, String text) {
        this.author = author;
        this.text = text;
        LOG.info("Message created.");

    }

    public User getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString(){
        LOG.info("Message toString used.");

        return "<data><command>5</command><user>"+ author.getUser_Name() +"</user><message>"+text+"</message></data>";
    }
}
