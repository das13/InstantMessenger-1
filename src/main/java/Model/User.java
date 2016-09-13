package Model;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class User {

    private static final Logger LOG = Logger.getLogger(User.class);

    private String user_Name;


    public User(String user_Name) {
        this.user_Name = user_Name;
        LOG.info("User created");
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public String toString(){
        LOG.info("User toString used.");
        return "<data><command>4</command><name>"+user_Name+"name></data>";
    }
}
