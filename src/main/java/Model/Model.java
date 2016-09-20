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
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class Model  {

    private static final Logger LOG = Logger.getLogger(Model.class);

    View view;

    private ArrayList<String> listOfMessage = new ArrayList();
    private ArrayList<User> listOfUsers = new ArrayList();

    private DataOutputStream out;
    private DataInputStream in;

    String thisUserName;

    int thisUserId;

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
                                System.out.println("case 4");

                                String name = element.getElementsByTagName("user").item(0).getChildNodes().item(0).getNodeValue();

                                int idOfUser = Integer.parseInt(element.getElementsByTagName("userId").item(0).getChildNodes().item(0).getNodeValue());

                                User tempUser = new User(name, idOfUser);

                                addUserToList(tempUser);

                                view.notificateFromNewUser("У нас новый пользователь - " + name +"!");

                                break;

                            case 5:

                                System.out.println("case 5");

                                String mesage = element.getElementsByTagName("message").item(0).getChildNodes().item(0).getNodeValue();

                                addMessageToList(mesage);

                                break;

                            case 7:

                                System.out.println("case 7");

                                String name7 = element.getElementsByTagName("user").item(0).getChildNodes().item(0).getNodeValue();

                                int idOfUser7 = Integer.parseInt(element.getElementsByTagName("userId").item(0).getChildNodes().item(0).getNodeValue());

                                User user7 = new User(name7,idOfUser7);

                                listOfUsers.add(user7);

                                break;

                            case 9:

                                System.out.println("case 9");

                                String name9 = element.getElementsByTagName("user").item(0).getChildNodes().item(0).getNodeValue();

                                int index = Integer.parseInt(element.getElementsByTagName("userId").item(0).getChildNodes().item(0).getNodeValue());

                                listOfUsers.remove(index);

                                //this.stop();

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

        FileInputStream fin;
        Properties property = new Properties();

        try{
            fin = new FileInputStream("src/main/resources/config.properties");
            property.load(fin);

            int PORT  = Integer.parseInt(property.getProperty("PORT"));
            String addres = property.getProperty("Address");

            Socket s = new Socket(addres,PORT);

            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());

            workWithOneUser.start();

        }catch (ConnectException e){
            view.showMessageDialog("Нет подключения, пожалуйста, попробуйте позже.");

        }
    }

    public void sendMessageToServer(Message message,View view) throws IOException{

        Transfer.sendMessage(message, out);
    }

    public void sendNewUserToServer(String nameOfUser,View view) throws IOException{

        User tempUser = new User(nameOfUser,0);

        Transfer.sendNewUser(tempUser, out);
    }

    public Message createMessage(String message, String user){

        User tempUser = new User(user,0);
        Message tempMessage = new Message(tempUser,message);

        return tempMessage;
    }

    public void addUserToList(User user){

        listOfUsers.add(user);
    }

    public void addMessageToList(String message) {

        listOfMessage.add(message);
        view.setMessages(listOfMessage);
    }

    public void deleteUser(String name, int idOfUser) throws IOException{

        Transfer.deleteUser(name, idOfUser, out);
    }

    public void setThisUserName(String name){
        thisUserName = name;

        FileInputStream fin;
        Properties property = new Properties();
        try {
            fin = new FileInputStream("src/main/resources/config.properties");
            property.load(fin);

            property.setProperty("UserName",name);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setThisUserId(int id){
        thisUserId = id;
    }

    public String getThisUserName(){
        return thisUserName;
    }

    public int getThisUserId(){
        return thisUserId;
    }


    public void closeStreams() throws IOException{

        LOG.info("Close the streams.");

       // in.close();
       // out.close();

        workWithOneUser.stop();
    }
}
