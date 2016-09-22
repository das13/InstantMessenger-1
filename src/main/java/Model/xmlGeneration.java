package Model;

import org.apache.log4j.Logger;

public class xmlGeneration {

    private static final Logger LOG = Logger.getLogger(xmlGeneration.class);

    private final static int SEND_NEW_USER = 4;
    private final static int SEND_MESSAGE = 5;
    private final static int DELETE_USER = 8;

    public static String sendNewUserToServer(String user){

        LOG.debug("Generate xml for sending user.");

        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<data>" +
                "<values>" +
                "<id>"+ SEND_NEW_USER +"</id>" +
                "<message></message>" +
                "<user>"+ user +"</user>" +
                "<userId></userId>"+
                "</values>" +
                "</data>";
    }

    public static String sendMessage(Message message, String autor){

        LOG.debug("Generate xml for sending message.");

        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<data>" +
                "<values>" +
                "<id>" + SEND_MESSAGE + "</id>" +
                "<message>" + message.getText() + "</message>" +
                "<user>" + autor + "</user>" +
                "</values>" +
                "</data>";
    }

    public static String deleteUser(String user, int id){

        LOG.debug("Generate xml for delete user.");

        return  "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<data>" +
                "<values>" +
                "<id>" + DELETE_USER + "</id>" +
                "<message></message>" +
                "<user>" + user + "</user>" +
                "<userId>" + id + "</userId>" +
                "</values>" +
                "</data>";
    }
}
