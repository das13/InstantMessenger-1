package Model;

import org.apache.log4j.Logger;

import java.io.*;

public class Transfer {

    private static final Logger LOG = Logger.getLogger(Transfer.class);

    public static void sendMessage(Message message, String autor, DataOutputStream out) throws IOException {

        LOG.info("Send message");

        out.writeUTF(xmlGeneration.sendMessage(message, autor));
    }

    public static void sendNewUser(User user, DataOutputStream out) throws IOException {

        LOG.info("Transfer send user");

        out.writeUTF(xmlGeneration.sendNewUserToServer(user.getUser_Name()));
    }

    public static void deleteUser(String name, int idOfUser, DataOutputStream out) throws IOException{

        LOG.info("Delete user");

        out.writeUTF(xmlGeneration.deleteUser(name,idOfUser));
    }
}

