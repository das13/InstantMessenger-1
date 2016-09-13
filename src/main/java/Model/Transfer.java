package Model;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class Transfer {

    Model model;

    private static final Logger LOG = Logger.getLogger(Transfer.class);

    public Transfer(Model model) {
        this.model = model;
    }

    public static void getMessageListFromServer(DataInputStream cin, DataOutputStream cout) throws IOException{


    }

    public static void getUserListFromServer (DataInputStream cin, DataOutputStream cout) throws IOException{


    }

    public static void sendMessage(Message message, DataInputStream cin, DataOutputStream cout) throws IOException{

        LOG.info("Transfer send message");

        cout.writeUTF(message.toString());
    }

    public static void sendNewUser(User user, DataInputStream cin, DataOutputStream cout) throws IOException{

        LOG.info("Transfer send message");

        cout.writeUTF(user.toString());
    }
}
