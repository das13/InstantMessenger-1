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

    public static void sendMessage(View view, Message message, DataInputStream in, DataOutputStream out) throws IOException {

        LOG.info("Send message");

        out.writeUTF(message.toString());
    }

    public static void sendNewUser(View view, User user, DataInputStream in, DataOutputStream out) throws IOException {

        LOG.info("Transfer send user");

        out.writeUTF(user.toString());
    }
}

