package Model;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Model  {

    private static final Logger LOG = Logger.getLogger(Model.class);

    private InputStream in;
    private OutputStream out;

    private DataInputStream cin;
    private DataOutputStream cout;

    private ArrayList<Message> tempMessageList = new ArrayList<Message>();
    private ArrayList<String> tempUserList = new ArrayList<String>();
    private String thisUserName;

    public void connectToServer() throws IOException{

        Socket s = new Socket("localhost",3000);

        this.in = s.getInputStream();
        this.out = s.getOutputStream();

        this.cin = new DataInputStream(in);
        this.cout = new DataOutputStream(out);

    }

    public void sendMessageToServer(Message message) throws IOException{

        Transfer.sendMessage(message,cin,cout);
    }

    public void sendNewUserToServer(String nameOfUser) throws IOException{

        User tempUser = new User(nameOfUser);

        Transfer.sendNewUser(tempUser,cin,cout);
    }



    public void expectMessageFromServer(DataInputStream cin, DataOutputStream cout) throws IOException{
        Transfer.getMessageListFromServer(cin,cout);
    }

    public void expectUserListFromServer(DataInputStream cin, DataOutputStream cout) throws IOException{
        Transfer.getUserListFromServer(cin, cout);
    }



    public ArrayList getListOfMessages(){
        return tempMessageList;
    }

    public ArrayList getListOfUsers(){
        return tempUserList;
    }

    public void setThisUserName(String userName){
        this.thisUserName = userName;
    }

    public String getThisUserName(){
        return thisUserName;
    }

    public Message createMessage(String message, String user){

        User tempUser = new User(user);
        Message tempMessage = new Message(tempUser,message);

        return tempMessage;
    }

    public void closeStreams() throws IOException{

        LOG.info("Close the streams.");

        in.close();
        out.close();
    }
}
