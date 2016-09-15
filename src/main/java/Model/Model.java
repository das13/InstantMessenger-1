package Model;

import View.View;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Model  {

    private static final Logger LOG = Logger.getLogger(Model.class);

    private ArrayList listOfMessage;
    private ArrayList getListOfMessage;

    private DataOutputStream out;
    private DataInputStream in;

    private ArrayList<Message> tempMessageList = new ArrayList<Message>();
    private static ArrayList<String> tempUserList = new ArrayList<String>();
    private String thisUserName;

    public void connectToServer() throws IOException{

        Socket s = new Socket("localhost",4545);

        out = new DataOutputStream(s.getOutputStream());
        in = new DataInputStream(s.getInputStream());
    }

    public void sendMessageToServer(Message message,View view) throws IOException{

        Transfer.sendMessage(view,message,in,out);
    }

    public void sendNewUserToServer(String nameOfUser,View view) throws IOException{

        User tempUser = new User(nameOfUser);

        Transfer.sendNewUser(view,tempUser,in,out);
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

    public static ArrayList getListOfUsers(){
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

    public void addMessageToList(String message){
        listOfMessage.add(message);
    }

    public static void addUserToList(String user){
        getListOfUsers().add(user);
    }

    public void closeStreams() throws IOException{

        LOG.info("Close the streams.");

        in.close();
        out.close();
    }
}
