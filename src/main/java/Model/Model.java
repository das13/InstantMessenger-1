package Model;

import View.View;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

public class Model  {

    private static final Logger LOG = Logger.getLogger(Model.class);

    View view;

    private ArrayList<String> listOfMessage = new ArrayList();
    private ArrayList<String> listOfUsers = new ArrayList();

    private DataOutputStream out;
    private DataInputStream in;

    String thisUserName;

    public Model(View view) {

        this.view = view;
    }

    private Thread workWithOneUser = new Thread() {

        public void run() {

            LOG.info("Thread workWithOneUser started");

            while (true){

                try {
                    DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
                    f.setValidating(false);
                    DocumentBuilder builder = null;
                    try {
                        builder = f.newDocumentBuilder();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }

                    String xml = in.readUTF();
                    LOG.info("Client get xml: " + xml);

                    Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

                    NodeList list = doc.getElementsByTagName("values");

                    for (int i = 0; i < list.getLength(); i++) {

                        Element element = (Element) list.item(i);

                        int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());

                        switch (id) {

                            case 4:

                                String name = element.getElementsByTagName("user").item(0).getChildNodes().item(0).getNodeValue();

                                addUserToList(name);

                                view.notificateFromNewUser("У нас новый пользователь - " + name +"!");

                                break;

                            case 5:

                                String mesage = element.getElementsByTagName("message").item(0).getChildNodes().item(0).getNodeValue();

                                addMessageToList(mesage);

                                break;

                            case 7:

                                String names = element.getElementsByTagName("user").item(0).getChildNodes().item(0).getNodeValue();

                                listOfUsers.add(names);

                                break;
                        }
                    }
                    view.setUsers(listOfUsers);
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }}};

    public void connectToServer() throws IOException{

        try{

            Socket s = new Socket("localhost",4545);

            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());

            workWithOneUser.start();

        }catch (ConnectException e){
            view.showMessageDialog("Нет подключения, пожалуйста, попробуйте позже.");

        }
    }

    public void sendMessageToServer(Message message,View view) throws IOException{

        Transfer.sendMessage(view,message,in,out);
    }

    public void sendNewUserToServer(String nameOfUser,View view) throws IOException{

        User tempUser = new User(nameOfUser);

        Transfer.sendNewUser(view,tempUser,in,out);
    }

    public Message createMessage(String message, String user){

        User tempUser = new User(user);
        Message tempMessage = new Message(tempUser,message);

        return tempMessage;
    }

    public void addUserToList(String user){

        listOfUsers.add(user);
        view.setUsers(listOfUsers);
    }

    public void addMessageToList(String message) {

        listOfMessage.add(message);
        view.setMessages(listOfMessage);
    }

    public void setThisUserName(String name){
        thisUserName = name;
    }

    public String getThisUserName(){
        return thisUserName;
    }

    public void closeStreams() throws IOException{

        LOG.info("Close the streams.");

        in.close();
        out.close();
    }
}
