package View;

import Controller.NewUserWindowsListener;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageViewElements extends JFrame {

    private static final Logger LOG = Logger.getLogger(ManageViewElements.class);

    JFrame addNewUserJFrame = new JFrame();
    JFrame notificateFromNewUserJFrame = new JFrame();

    JPanel usersJPanel = new JPanel();
    JPanel messagesJPanel = new JPanel();
    JPanel sendBarJpanel = new JPanel();
    JPanel welcomeJpanel = new JPanel();
    JPanel addNewUserJpanel = new JPanel();

    JButton sendMessageJButton = new JButton("Отправить");
    JButton addNewUserJButton = new JButton("Ок");

    JTextField nameOfUserJTextField = new JTextField(18);
    JTextField textOfMessageJTextField = new JTextField(45);

    JList listOfMasseges = new JList();
    JList listOfUsers = new JList();

    JScrollPane scrollPane = new JScrollPane(listOfMasseges);
    JScrollPane scrollPaneUser = new JScrollPane(listOfUsers);


    DefaultListModel messegesModelOfTask = new DefaultListModel();
    DefaultListModel userModelOfTask = new DefaultListModel();


    ManageViewElements() {

        super();

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listOfMasseges.setModel(messegesModelOfTask);
        listOfUsers.setModel(userModelOfTask);

        sendBarJpanel.setLayout(new FlowLayout());
        usersJPanel.setLayout(new BorderLayout());
        messagesJPanel.setLayout(new BorderLayout());

        sendBarJpanel.add(textOfMessageJTextField);
        sendBarJpanel.add(sendMessageJButton);

        usersJPanel.add(scrollPaneUser, BorderLayout.CENTER);

        messagesJPanel.add(scrollPane, BorderLayout.CENTER);
        messagesJPanel.add(sendBarJpanel, BorderLayout.PAGE_END);

        add(messagesJPanel, BorderLayout.LINE_END);
        add(usersJPanel, BorderLayout.CENTER);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        LOG.info("View created.");
    }

    public void addNewUserWindow() {

        addNewUserJFrame.setSize(300, 100);

        NewUserWindowsListener listener = new NewUserWindowsListener();

        addNewUserJFrame.addWindowListener(listener);

        welcomeJpanel = new JPanel();
        addNewUserJpanel = new JPanel();

        JLabel welcomeJlabel = new JLabel("Привет! Пожалуйста, введи свой никнейм:");

        addNewUserJpanel.setLayout(new FlowLayout());
        addNewUserJpanel.add(nameOfUserJTextField);
        addNewUserJpanel.add(addNewUserJButton);

        welcomeJpanel.setLayout(new FlowLayout());
        welcomeJpanel.add(welcomeJlabel);

        addNewUserJFrame.setLayout(new BorderLayout());

        addNewUserJFrame.add(welcomeJpanel, BorderLayout.PAGE_START);
        addNewUserJFrame.add(addNewUserJpanel, BorderLayout.CENTER);

        addNewUserJFrame.setVisible(true);
        addNewUserJFrame.setLocationRelativeTo(null);
    }

    public void notificateFromNewUser(){

        notificateFromNewUserJFrame.setSize(300, 100);
        notificateFromNewUserJFrame.setLayout(new FlowLayout());

        String newUserName = "";


        JLabel newUserNickname = new JLabel("У нас новый польователь: " + newUserName + "!");

        notificateFromNewUserJFrame.add(newUserNickname);

        notificateFromNewUserJFrame.setVisible(true);
        notificateFromNewUserJFrame.setLocationRelativeTo(null);
    }

    public void setMessageList(ArrayList list) {

        this.messegesModelOfTask.clear();

        if (list.size() == 0) {
            return;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            String tempMessage = list.get(i).toString();
            messegesModelOfTask.addElement(tempMessage);
        }
        String tempMessage = list.get(list.size() - 1).toString();
        messegesModelOfTask.addElement(tempMessage);
    }

    public void setUserList(ArrayList list) {
        this.userModelOfTask.clear();

        if (list.size() == 0) {
            return;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            String tempUser = "* "+ list.get(i).toString() + ";";
            userModelOfTask.addElement(tempUser);
        }
        String tempUser = "* "+ list.get(list.size() - 1).toString() + ".";
        userModelOfTask.addElement(tempUser);
    }

    public String getMessage() {
        return textOfMessageJTextField.getText();
    }

    public void setSendButtonListener(ActionListener event) {
        sendMessageJButton.addActionListener(event);
    }

    public void setAddNewUserButtonListener(ActionListener event) {
        addNewUserJButton.addActionListener(event);
    }

    public String getUserName() {
        return nameOfUserJTextField.getText();

    }
}


