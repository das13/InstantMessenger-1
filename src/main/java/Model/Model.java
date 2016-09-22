package Model;

import View.View;
import org.apache.log4j.Logger;
import org.apache.log4j.varia.StringMatchFilter;
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

    private final static int GET_NEW_USER = 4;
    private final static int GET_MESSAGE = 5;
    private final static int GET_USER_LIST = 7;
    private final static int DELETE_USER = 9;
    private final static int GET_USER_ID_USER_NAME = 11;

    private ArrayList<User> listOfUsers = new ArrayList();

    private Socket s;

    private DataOutputStream out;
    private DataInputStream in;

    private String thisUserName;

    private int thisUserId;

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
                        LOG.error("ParserConfigurationException: "+ e);
                    }

                    String xml = in.readUTF();
                    LOG.info("Client get xml: " + xml);

                    Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

                    NodeList list = doc.getElementsByTagName("values");

                    for (int i = 0; i < list.getLength(); i++) {

                        Element element = (Element) list.item(i);

                        int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());

                        switch (id) {

                            case GET_NEW_USER:

                                String name = element.getElementsByTagName("user").item(0).getChildNodes().item(0).getNodeValue();

                                int idOfUser = Integer.parseInt(element.getElementsByTagName("userId").item(0).getChildNodes().item(0).getNodeValue());

                                User tempUser = new User(name, idOfUser);

                                addUserToList(tempUser);

                                view.notificateFromNewUser("У нас новый пользователь - " + name);

                                break;

                            case GET_MESSAGE:

                                String mesage = element.getElementsByTagName("message").item(0).getChildNodes().item(0).getNodeValue();

                                addMessageToList(mesage);

                                break;

                            case GET_USER_LIST:

                                String name7 = element.getElementsByTagName("user").item(0).getChildNodes().item(0).getNodeValue();

                                int idOfUser7 = Integer.parseInt(element.getElementsByTagName("userId").item(0).getChildNodes().item(0).getNodeValue());

                                User user7 = new User(name7, idOfUser7);

                                listOfUsers.add(user7);

                                break;

                            case DELETE_USER:

                                int id9 = Integer.parseInt(element.getElementsByTagName("userId").item(0).getChildNodes().item(0).getNodeValue());

                                for (int temp = 0; i < listOfUsers.size(); temp++) {
                                    System.out.println("for I ="+ temp);

                                    if (listOfUsers.get(temp).getId() == id9) {
                                        System.out.println("if I ="+ temp);

                                        listOfUsers.remove(temp);
                                        break;
                                    }
                                }
                                break;

                            case GET_USER_ID_USER_NAME:

                                int id11 = Integer.parseInt(element.getElementsByTagName("userId").item(0).getChildNodes().item(0).getNodeValue());

                                String name11 = element.getElementsByTagName("user").item(0).getChildNodes().item(0).getNodeValue();

                                setThisUserId(id11);

                                setThisUserName(name11);
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

            s = new Socket(addres,PORT);

            out = new DataOutputStream(s.getOutputStream());
            in = new DataInputStream(s.getInputStream());

            workWithOneUser.start();

        }catch (ConnectException e){
            view.showMessageDialog("Нет подключения, пожалуйста, попробуйте позже.");
        }
    }

    public void sendMessageToServer(Message message, String autor, View view) throws IOException{

        Transfer.sendMessage(message, autor,  out);
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

        view.addMessages(message);
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

        s.close();

        in.close();
        out.close();

       workWithOneUser.stop();
    }
}