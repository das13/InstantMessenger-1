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

    public static void sendMessage(View view, Message message, DataInputStream in, DataOutputStream out) throws IOException {

        LOG.info("Send message");

        out.writeUTF(message.toString());

    }

    public static void sendNewUser(View view, User user, DataInputStream in, DataOutputStream out) throws IOException{

        LOG.info("Transfer send message");

        out.writeUTF(user.toString());

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
            System.out.println("Get xml to client: " + xml);

            Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

            NodeList list = doc.getElementsByTagName("values");

            for (int i = 0; i < list.getLength(); i++){

                Element element = (Element)list.item(i);

                int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());

                System.out.println("id - "+ id);


                /*
                *   id:
                *
                *   7 - get online users
                *
                *
                *
                *
                * */

                switch (id) {

                    case 4:





                        break;

                    case 5:

                        //Model.sendMessagsToClients(valueString);


                        break;

                    case 7:

                        System.out.println("casee 7");

                        String name = element.getElementsByTagName("user").item(0).getChildNodes().item(0).getNodeValue();

                        Model.addUserToList(name);

                        break;

                    case 8:


                        break;
                }

                System.out.println("view set");

                view.setUsers( Model.getListOfUsers());
            }
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
