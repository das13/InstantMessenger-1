package Model;

import View.View;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class Transfer {

    private static Model model;
    private static View view;

    private static final Logger LOG = Logger.getLogger(Transfer.class);

    public Transfer(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public static void getMessageListFromServer(DataInputStream cin, DataOutputStream cout) throws IOException{


    }

    public static void getUserListFromServer (DataInputStream cin, DataOutputStream cout) throws IOException{


    }

    public static void sendMessage(Message message, DataInputStream in, DataOutputStream out) throws IOException{

        LOG.info("Send message");

        out.writeUTF(message.toString());


        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder = null;
        try {
            builder = f.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        String xml = in.readUTF();



        Document doc = null;

        try {
            doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
        } catch (SAXException e) {
            e.printStackTrace();
        }

        Node data = doc.getChildNodes().item(0);
        Node command = data.getChildNodes().item(0);
        Node value = data.getChildNodes().item(1);

        NodeList list = doc.getElementsByTagName("value");

        for (int temp = 0; temp < list.getLength(); temp++) {

            Node nNode = list.item(temp);

            model.addUserToList(nNode.getTextContent());
            view.setUsers(model.getListOfUsers());
        }


        int idOfCommand = Integer.parseInt(command.getTextContent());

        String valueString = value.getTextContent();

        switch (idOfCommand) {

            case 4:


                break;

            case 5:

                model.addMessageToList(valueString);
                view.setListOfTask(model.getListOfMessages());

                break;

            case 7:


                break;

            case 8:


                break;
        }
    }

    public static void sendNewUser(User user, DataInputStream in, DataOutputStream out) throws IOException{

        LOG.info("Transfer send message");

        out.writeUTF(user.toString());
    }
}
