package Model;

import View.View;
import org.apache.log4j.Logger;

import java.io.*;

public class Transfer {

    private static Model model;
    private static View view;

    private static final Logger LOG = Logger.getLogger(Transfer.class);

    public Transfer(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public static void sendMessage(Message message, DataOutputStream out) throws IOException {

        LOG.info("Send message");

        out.writeUTF(message.toString());
    }

    public static void sendNewUser(User user, DataOutputStream out) throws IOException {

        LOG.info("Transfer send user");

        out.writeUTF(user.toString());
    }

    public static void deleteUser(String name, int idOfUser, DataOutputStream out) throws IOException{

        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<data>" +
                "<values>" +
                "<id>" + 8 + "</id>" +
                "<message></message>" +
                "<user>" + name + "</user>" +
                "<userId>" + idOfUser + "</userId>" +
                "</values>" +
                "</data>";

        out.writeUTF(xml);
    }
}

